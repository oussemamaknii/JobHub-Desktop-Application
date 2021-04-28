/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class evenement {
    private int id;
    private String nom;
    private LocalDate date;
    private String description;
    private int prix;
    private String adresse,file;
    private int nbre_place;

    public evenement(int id, String nom, LocalDate date, String description, int prix, String adresse, String file, int nbre_place) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.nom = description;
        this.prix = prix;
        this.adresse = adresse;
        this.file = file;
        this.nbre_place = nbre_place;
    }

    public evenement(String nom, LocalDate date, String description, int prix, String adresse, String file, int nbre_place) {

        this.nom = nom;
        this.date = date;
        this.nom = description;
        this.prix = prix;
        this.adresse = adresse;
        this.file = file;
        this.nbre_place = nbre_place;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrix() {
        return prix;
    }

    public String getAdresse() {
        return adresse;
    }


    public String getFile() {
        return file
                ;
    }


    public Integer getNbre_place() {
        return nbre_place;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public void setNbre_place(int nbre_place) {
        this.nbre_place = nbre_place;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", description=" + description + ", prix=" + prix + ", adresse=" + adresse + ", file=" + file + ", nbre_place=" + nbre_place + '}';
    }


}
