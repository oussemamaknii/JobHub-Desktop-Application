package Entities;


import java.util.Objects;

/**
 * @author Mintoua
 */
public class Commande {

    private int id;
    private float totalPayment;
    private boolean state;
    private String date;
    private int idUser;

    //begin constructors
    public Commande(){

    }
    public Commande(int id, float totalPayment, boolean state, String date, int idUser) {
        this.id = id;
        this.totalPayment = totalPayment;
        this.state = state;
        this.date = date;
        this.idUser = idUser;
    }
    public Commande(float totalPayment, boolean state, String date, int idUser) {

        this.totalPayment = totalPayment;
        this.state = state;
        this.date = date;
        this.idUser = idUser;
    }//end constructors

    //begin getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    //end getters and setters

    //Overrides

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", totalPayment=" + totalPayment +
                ", state=" + state +
                ", date='" + date + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (getClass() != obj.getClass()){
            return false;
        }
        final Commande other = (Commande) obj;
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
