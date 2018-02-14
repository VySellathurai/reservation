package fr.esipe.zookeeper.tp.reservation.model;

/**
 * Created by Vyach on 14/02/2018.
 */
public class Ticket {

    private String reference;
    private String libelle;

    public Ticket(String reference, String libelle) {
        this.reference = reference;
        this.libelle = libelle;
    }

    public Ticket() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
