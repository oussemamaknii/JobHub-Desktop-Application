package Entities;

import java.util.Objects;

public class categorie {

    private int id;
    private String nom, couleur;

    public categorie() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitref() {
        return nom;
    }

    public void setTitref(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        categorie categorie = (categorie) o;
        return id == categorie.id && Objects.equals(nom, categorie.nom) && Objects.equals(couleur, categorie.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, couleur);
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return "categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                '}';
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }


}
