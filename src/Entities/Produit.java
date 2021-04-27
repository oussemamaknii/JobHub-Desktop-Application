package Entities;

/**
 * @author Mintoua
 */

public class Produit {

    private int id;
    private String name;
    private String ref;
    private String description;
    private float price;
    private int quantity;
    private String image;

    //contructors begin
    public Produit(){

    }

    public Produit(String name,String ref, String description,float price, int quantity, String image){
        this.name = name;
        this.ref =  ref;
        this.description = description;
        this.price = price;
        this.quantity= quantity;
        this.image = image;
    }
    public Produit(int id, String name,String ref, String description,float price, int quantity, String image){
        this.id = id;
        this.name = name;
        this.ref =  ref;
        this.description = description;
        this.price = price;
        this.quantity= quantity;
        this.image = image;

    } //contructors end

    //getters and setter begin
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
