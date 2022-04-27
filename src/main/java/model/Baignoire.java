package model;

/**
 * The type Baignoire.
 */
public class Baignoire {

    private int quantite;

    private int capacite = 100;

    private int capacite_utilisee = 0;

    private int debordement = 0;

    private int fuiteTotale = 0;

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
     * @param quantite the quantite
     * @return the int
     */
    public int removeQuantite(int quantite) {
        int res = this.quantite - quantite;
        this.quantite = Math.max(res, 0);
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
     * Add fuite int.
     *
     * @param debit the debit
     * @return the int
     */
    public int addFuite(int debit) {
        return this.fuiteTotale+=debit;
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

    /**
     * Gets fuite totale.
     *
     * @return the fuite totale
     */
    public int getFuiteTotale() {
        return fuiteTotale;
    }

    /**
     * Sets fuite totale.
     *
     * @param fuite_totale the fuite totale
     */
    public void setFuiteTotale(int fuite_totale) {
        this.fuiteTotale = fuite_totale;
    }
}
