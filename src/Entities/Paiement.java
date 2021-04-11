package Entities;


import java.util.Objects;

/**
 * @author Mintoua
 */
public class Paiement {

    private int id;
    private String cardHolderName;
    private int cardNumber;
    private int securityCode;
    private int moiExpiration;
    private int anneeExpiration;
    private int idCommande;

    public Paiement(int id, String cardHolderName, int cardNumber, int securityCode, int moiExpiration, int anneeExpiration, int idCommande) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.moiExpiration = moiExpiration;
        this.anneeExpiration = anneeExpiration;
        this.idCommande = idCommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public int getMoiExpiration() {
        return moiExpiration;
    }

    public void setMoiExpiration(int moiExpiration) {
        this.moiExpiration = moiExpiration;
    }

    public int getAnneeExpiration() {
        return anneeExpiration;
    }

    public void setAnneeExpiration(int anneeExpiration) {
        this.anneeExpiration = anneeExpiration;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber=" + cardNumber +
                ", securityCode=" + securityCode +
                ", moiExpiration=" + moiExpiration +
                ", anneeExpiration=" + anneeExpiration +
                ", idCommande=" + idCommande +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (getClass() != obj.getClass()){
            return false;
        }
        final Paiement other = (Paiement) obj;
        if (this.id != other.id){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
