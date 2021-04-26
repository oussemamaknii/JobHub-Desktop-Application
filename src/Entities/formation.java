//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Entities;

import java.sql.Date;
import java.time.LocalDate;

public class formation {
    private int id;
    private int category_id;
    private String nom;
    private String formateur;
    private String description;
    private String adresse;
    private double tel;
    private String email;
    private Date date_debut;
    private Date date_fin;
    private double prix;

    public formation() {

    }


    public formation(int categId, String text, String tformateurText, String tfdescriptionText, LocalDate value, LocalDate date_finValue, String tfmailText, String tftelText, String tfprixText) {
    }

    public formation(int category_id, String nom, String formateur, String description, String adresse, double tel, String email, Date date_debut, Date date_fin, double prix) {
        this.category_id = category_id;
        this.nom = nom;
        this.formateur = formateur;
        this.description = description;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitre() {
        return this.nom;
    }

    public void setTitre(String titre) {
        this.nom = titre;
    }

    public String getFormateur() {
        return this.formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getTel() {
        return this.tel;
    }

    public void setTel(double tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String toString() {
        return "formation{id=" + this.id + ", category_id=" + this.category_id + ", titre=" + this.nom + ", formateur=" + this.formateur + ", description=" + this.description + ", adresse=" + this.adresse + ", tel=" + this.tel + ", email=" + this.email + ", date_debut=" + this.date_debut + ", date_fin=" + this.date_fin + ", prix=" + this.prix + '}';
    }



    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            formation other = (formation)obj;
            if (this.id != other.id) {
                return false;
            } else {
                if (this.nom == null) {
                    if (other.nom != null) {
                        return false;
                    }
                } else if (!this.nom.equals(other.nom)) {
                    return false;
                }

                return true;
            }
        }
    }

    public int getCategory() {
        return this.category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
    }
}
