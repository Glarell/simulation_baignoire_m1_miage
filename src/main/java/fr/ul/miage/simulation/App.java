package fr.ul.miage.simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The type fr.ul.miage.simulation.App.
 */
public class App extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Simulation baignoire");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));
        AnchorPane mainpage = loader.load();
        Scene scene = new Scene(mainpage);
        stage.setScene(scene);
        stage.show();
    }
}
