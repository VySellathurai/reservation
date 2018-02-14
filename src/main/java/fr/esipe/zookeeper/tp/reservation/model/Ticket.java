package fr.esipe.zookeeper.tp.reservation.model;

/**
 * Created by Vyach on 14/02/2018.
 */
public class Ticket {

    private String reference;
    private String libelle;
    private String date;

    public Ticket(String reference, String libelle, String date) {
        this.reference = reference;
        this.libelle = libelle;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
