package controller;

import javafx.animation.RotateTransition;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Baignoire;
import model.Robinet;
import model.Trou;
import service.FuiteService;
import service.RemplissageService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Controller.
 */
public class Controller {
    private final double LAYOUT_Y = 230;
    private final Logger LOG = Logger.getLogger(Controller.class.getName());
    private LocalTime local_time_start;
    /**
     * The Baignoire.
     */
    Baignoire baignoire;
    /**
     * The Robinet.
     */
    Robinet robinet;
    /**
     * The Remplissage.
     */
    RemplissageService remplissage = new RemplissageService(robinet);
    /**
     * The Fuites.
     */
    ArrayList<FuiteService> fuites = new ArrayList<>();
    /**
     * Booléen pour lancer la simulation
     */
    boolean isReady = false;
    @FXML
    private Button demarrer;
    @FXML
    private Label baignoire_qte;
    @FXML
    private Polyline dessin_baignoire;
    @FXML
    private Button arreter;
    @FXML
    private Rectangle goutte;
    @FXML
    private Rectangle goutte_trou;
    @FXML
    private Rectangle dessin_robinet;
    @FXML
    private Pane panel;
    @FXML
    private Spinner<Integer> spinner_baignore;
    @FXML
    private Spinner<Integer> spinner_robinet;
    @FXML
    private Spinner<Integer> spinner_trous;
    @FXML
    private GridPane grid_fuite;
    @FXML
    private TextArea textarea;

    /**
     * Initialize.
     */
    public void initialize() {
        spinner_trous.valueProperty().addListener(
                (obs, oldValue, newValue) -> generateGridText(newValue)
        );
    }

    /**
     * Lancer simulation.
     */
    public void lancer_simulation() {
        init_options();
        if (!isReady) { return; }
        LOG.info("Lancement de la simulation !");
        init_listener();
        remplissage.start();
        lancer_animation_goutte(this.goutte);
        lancer_animation_goutte(this.goutte_trou);
        for (FuiteService fuite : fuites) {
            synchronized (fuite) {
                fuite.start();
            }
        }
        local_time_start=LocalTime.now();
        textarea.setText("Démarrage de la simulation à " + local_time_start + '\n');
    }

    /**
     * Init options.
     */
    public void init_options() {
        int nb_cap_baignore = spinner_baignore.getValue();
        int nb_debit_robinet = spinner_robinet.getValue();
        int nb_trous = spinner_trous.getValue();
        baignoire = new Baignoire(0, nb_cap_baignore);
        robinet = new Robinet(baignoire, nb_debit_robinet);
        remplissage = new RemplissageService(robinet);
        for (int i = 0; i < nb_trous; i++) {
            int nb_debit_fuite = Integer.parseInt(((TextField) this.grid_fuite.getChildren().get(i)).getText());
            Trou trou = new Trou(baignoire, nb_debit_fuite);
            FuiteService fuite = new FuiteService(trou);
            this.fuites.add(fuite);
        }
        isReady=nb_cap_baignore!=0 && nb_debit_robinet!=0;
    }

    /**
     * Init listener.
     */
    public void init_listener() {
        remplissage.setOnSucceeded((WorkerStateEvent event) -> {
            System.out.println(baignoire.getQuantite());
            this.baignoire_qte.setText(String.valueOf(baignoire.getQuantite()));
            int layout = Math.min(baignoire.getQuantite(), 100);
            panel.setLayoutY(LAYOUT_Y - layout);
            panel.setPrefHeight(layout);
        });
        for (FuiteService fuite : fuites) {
            fuite.setOnSucceeded((WorkerStateEvent event) -> {
                this.baignoire_qte.setText(String.valueOf(baignoire.getQuantite()));
                panel.setLayoutY(LAYOUT_Y - baignoire.getQuantite());
                panel.setPrefHeight(baignoire.getQuantite());
            });
        }
    }

    /**
     * Arreter simulation.
     */
    public void arreter_simulation() {
        if (!remplissage.isRunning()) {return;}
        LOG.info("Arrêt de la simulation !");
        LocalTime local_time_end=LocalTime.now();
        remplissage.cancel();
        remplissage.reset();
        arreter_animation_goutte(this.goutte);
        for (FuiteService fuite : fuites) {
            fuite.cancel();
            fuite.reset();
        }
        arreter_animation_goutte(this.goutte_trou);
        textarea.setText(textarea.getText() + "Arrêt de la simulation à " + local_time_end + '\n'
                + "Durée totale de la simulation : " + local_time_end.compareTo(local_time_start) + '\n'
                + "Quantité d'eau utilisée : " + this.baignoire.getCapacite_utilisee() + '\n'
                + "Quantité d'eau dans la baignoire : " + this.baignoire.getQuantite() + '\n'
                + "Quantité d'eau total ayant fuit de la baignoire : " + this.baignoire.getFuiteTotale() + '\n'
                + "Quantité d'eau débordée de la baignoire : " + this.baignoire.getDebordement());
    }

    /**
     * Lancer animation goutte.
     *
     * @param goutte the goutte
     */
    public void lancer_animation_goutte(Rectangle goutte) {
        RotateTransition rotate = getRotateTransition();
        goutte.setVisible(true);
        rotate.setNode(goutte);
        rotate.play();
    }

    /**
     * Arreter animation goutte.
     *
     * @param goutte the goutte
     */
    public void arreter_animation_goutte(Rectangle goutte) {
        goutte.setVisible(false);
        RotateTransition rotate = getRotateTransition();
        rotate.setNode(goutte);
        rotate.stop();
    }

    /**
     * Gets rotate transition.
     *
     * @return the rotate transition
     */
    public RotateTransition getRotateTransition() {
        RotateTransition rotate = new RotateTransition();
        rotate.setDuration(Duration.millis(1000));
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(180);
        rotate.setCycleCount(500);
        return rotate;
    }

    /**
     * Generate grid text.
     *
     * @param nb_trous the nb trous
     */
    public void generateGridText(int nb_trous) {
        grid_fuite.getChildren().clear();
        grid_fuite.setVisible(true);
        for (int i = 0; i < nb_trous; i++) {
            TextField textField = new TextField("0");
            grid_fuite.add(textField, 0, i);
        }
    }
}
