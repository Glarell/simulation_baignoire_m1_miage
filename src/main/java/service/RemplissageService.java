package service;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import model.Robinet;

import java.util.logging.Logger;

/**
 * The type Remplissage service.
 */
public class RemplissageService extends ScheduledService<Integer> {

    private final Robinet robinet;

    private final Logger LOG = Logger.getLogger(RemplissageService.class.getName());

    /**
     * Instantiates a new Remplissage service.
     *
     * @param robinet the robinet
     */
    public RemplissageService(Robinet robinet) {
        super();
        this.robinet = robinet;
        this.setPeriod(Duration.seconds(1));
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            @Override
            protected Integer call() {
                int remplissage = robinet.remplir();
                LOG.info("Ajout d'eau : " + remplissage + " L.");
                return remplissage;
            }
        };
    }

}
