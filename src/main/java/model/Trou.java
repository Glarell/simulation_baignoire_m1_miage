package model;

/**
 * The type Trou.
 */
public class Trou {

    private final Baignoire baignoire;
    private int debit;

    /**
     * Instantiates a new Trou.
     *
     * @param baignoire the baignoire
     * @param debit     the debit
     */
    public Trou(Baignoire baignoire, int debit) {
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
     * Fuiter int.
     *
     * @return the int
     */
    public int fuiter() {
        synchronized (baignoire) {
            baignoire.removeQuantite(this.debit);
        }
        return this.debit;
    }
}
