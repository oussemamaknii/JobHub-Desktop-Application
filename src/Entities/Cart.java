package Entities;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author Mintoua
 */
public class Cart {
    private int id;
    private Spinner<Integer> spinner;
    private int idProduit;// clé étrangère de la table produit
    private String nomProduit;
    private int idCommande;
    private float prix;
    private ImageView imageView;
    private int quantite;
    private String image;
    private Button remove;
    private SpinnerValueFactory<Integer> svf;

    public Cart(ImageView img,String name,float prix){
        this.imageView = img;
        this.nomProduit = name;
        this.prix = prix;

    }

    public Cart( int idProduit, String nomProduit, float prix,int quantite,String image,int idCommande) {
        this.image=image;
        this.spinner=new Spinner();
        this.quantite = quantite;
        this.svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, this.quantite);
        this.spinner.setValueFactory(this.svf);
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.imageView=new ImageView();
        this.imageView.setFitHeight(50);
        this.imageView.setFitWidth(50);
        /*this.imageView.setImage(new Image("Gui/Images/"+this.image));
        this.remove=new Button("");
        ImageView imageButton=new ImageView("Gui/Images/Icon-awesome-trash-alt.png");
        imageButton.setFitHeight(20);
        imageButton.setFitWidth(20);
        this.remove.setGraphic(imageButton);*/
        this.idCommande=idCommande;

    }

    public int getId() {
        return id;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setSvf(SpinnerValueFactory<Integer> svf) {
        this.svf = svf;
    }

    public void setSpinner(Spinner<Integer> spinner) {
        this.spinner = spinner;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        this.svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, this.quantite);
        this.spinner.setValueFactory(this.svf);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Spinner<Integer> getSpinner() {
        return spinner;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Button getRemove() {
        return remove;
    }

    public SpinnerValueFactory<Integer> getSvf() {
        return svf;
    }

    public String getImage() {
        return image;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }
}
