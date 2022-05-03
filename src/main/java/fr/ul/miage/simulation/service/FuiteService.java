package fr.ul.miage.simulation.service;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import fr.ul.miage.simulation.model.Trou;

import java.util.logging.Logger;

/**
 * The type Fuite fr.ul.miage.simulation.service.
 */
public class FuiteService extends ScheduledService<Integer> {

    private final Logger LOG = Logger.getLogger(FuiteService.class.getName());

    private final Trou trou;

    /**
     * Instantiates a new Fuite fr.ul.miage.simulation.service.
     *
     * @param trou the trou
     */
    public FuiteService(Trou trou) {
        super();
        this.trou = trou;
        setPeriod(Duration.seconds(1));
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            @Override
            protected Integer call() {
                int res = trou.fuiter();
                LOG.info("Fuite de : " + res + " L.");
                return res;
            }
        };
    }

    /**
     * Gets trou.
     *
     * @return the trou
     */
    public Trou getTrou() {
        return trou;
    }
}
