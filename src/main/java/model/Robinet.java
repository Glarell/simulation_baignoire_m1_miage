package model;

/**
 * The type Robinet.
 */
public class Robinet {

    private final Baignoire baignoire;
    private int debit;

    /**
     * Instantiates a new Robinet.
     *
     * @param baignoire the baignoire
     * @param debit     the debit
     */
    public Robinet(Baignoire baignoire, int debit) {
        this.debit = debit;
        this.baignoire = baignoire;
    }

    /**
     * Gets debit.
     *
     * @return the debit
     */
    public int getDebit() {
        return debit;
    }

    /**
     * Sets debit.
     *
     * @param debit the debit
     */
    public void setDebit(int debit) {
        this.debit = debit;
    }

    /**
     * Gets baignore.
     *
     * @return the baignore
     */
    public Baignoire getBaignore() {
        return baignoire;
    }

    /**
     * Remplir int.
     *
     * @return the int
     */
    public int remplir() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        synchronized (baignoire) {
            baignoire.addQuantite(this.debit);
        }
        return this.debit;
    }
}
