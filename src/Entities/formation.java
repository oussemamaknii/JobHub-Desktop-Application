//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Entities;

import java.time.LocalDate;

public class formation {
    private int id;
    private int category_id;
    private String titre;
    private String formateur;
    private String description;
    private String adresse;
    private String tel;
    private String email;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private int prix;

    public static void add(formation offre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public formation() {
    }

    public formation(int category_id, String titre, String formateur, String description, String adresse, String tel, String email, LocalDate date_debut, LocalDate fin, int prix) {
        this.category_id = category_id;
        this.titre = titre;
        this.formateur = formateur;
        this.description = description;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.date_debut = date_debut;
        this.date_fin = this.date_fin;
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
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String toString() {
        return "formation{id=" + this.id + ", category_id=" + this.category_id + ", titre=" + this.titre + ", formateur=" + this.formateur + ", description=" + this.description + ", adresse=" + this.adresse + ", tel=" + this.tel + ", email=" + this.email + ", date_debut=" + this.date_debut + ", date_fin=" + this.date_fin + ", prix=" + this.prix + '}';
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
                if (this.titre == null) {
                    if (other.titre != null) {
                        return false;
                    }
                } else if (!this.titre.equals(other.titre)) {
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
