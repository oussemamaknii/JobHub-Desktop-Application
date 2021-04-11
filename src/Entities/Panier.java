package Entities;


/**
 * @author Mintoua
 */
public class Panier {

    private int id;
    private int quantity;
    private int idProduit; //clé étrangère de la table produit
    private int idCommande; //clé étrangère de la table produit


    public Panier() {
    }

    public Panier(int quantity, int idProduit, int idCommande) {
        this.quantity = quantity;
        this.idProduit = idProduit;
        this.idCommande = idCommande;
    }
    public Panier(int id, int quantity, int idProduit, int idCommande) {
        this.id = id;
        this.quantity = quantity;
        this.idProduit = idProduit;
        this.idCommande = idCommande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

}
