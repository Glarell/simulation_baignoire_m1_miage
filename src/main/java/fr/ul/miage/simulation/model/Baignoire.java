package fr.ul.miage.simulation.model;

/**
 * The type Baignoire.
 */
public class Baignoire {

    private int quantite;

    private int capacite = 100;

    private int capacite_utilisee = 0;

    private int debordement = 0;

    private int fuite_total = 0;

    /**
     * Instantiates a new Baignoire.
     *
     * @param qte the qte
     */
    public Baignoire(int qte) {
        this.quantite = qte;
    }

    /**
     * Instantiates a new Baignoire.
     *
     * @param qte      the qte
     * @param capacite the capacite
     */
    public Baignoire(int qte, int capacite) {
        this.quantite = qte;
        this.capacite = capacite;
    }

    /**
     * Remove quantite int.
     *
     * @param debit the quantite
     * @return the int
     */
    public int removeQuantite(int debit) {
        int res = this.quantite - debit;
        this.quantite = Math.max(res, 0);
        this.fuite_total+=res < 0 ? res+debit : debit;
        return this.quantite;
    }

    /**
     * Add quantite int.
     *
     * @param quantite the quantite
     * @return the int
     */
    public int addQuantite(int quantite) {
        if (this.quantite + quantite > 100) {
            this.debordement += (this.quantite + quantite) % 100;
            this.quantite = 100;
        } else {
            this.quantite += quantite;
        }
        this.capacite_utilisee += quantite;
        return this.quantite;
    }

    /**
     * Gets quantite.
     *
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Sets quantite.
     *
     * @param quantite the quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Gets capacite.
     *
     * @return the capacite
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Sets capacite.
     *
     * @param capacite the capacite
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * Gets capacite utilisee.
     *
     * @return the capacite utilisee
     */
    public int getCapacite_utilisee() {
        return capacite_utilisee;
    }

    /**
     * Sets capacite utilisee.
     *
     * @param capacite_utilisee the capacite utilisee
     */
    public void setCapacite_utilisee(int capacite_utilisee) {
        this.capacite_utilisee = capacite_utilisee;
    }

    /**
     * Gets debordement.
     *
     * @return the debordement
     */
    public int getDebordement() {
        return debordement;
    }

    /**
     * Sets debordement.
     *
     * @param debordement the debordement
     */
    public void setDebordement(int debordement) {
        this.debordement = debordement;
    }

    public int getFuite_total() {
        return fuite_total;
    }
}
