package Entities;

import java.time.LocalDate;
import java.util.Objects;

public class Demande_Recrutement {
    private int id, offre_id, candidat_id;
    LocalDate date_debut, date_expiration;
    String offtit,username;

    public String getOfftit() {
        return offtit;
    }

    public void setOfftit(String offtit) {
        this.offtit = offtit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public Demande_Recrutement(){

    }

    @Override
    public String toString() {
        return "Demande_Recrutement{" +
                "id=" + id +
                ", offre_id=" + offre_id +
                ", candidat_id=" + candidat_id +
                ", date_debut=" + date_debut +
                ", date_expiration=" + date_expiration +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Demande_Recrutement)) return false;
        Demande_Recrutement that = (Demande_Recrutement) o;
        return getId() == that.getId() && getOffre_id() == that.getOffre_id() && getCandidat_id() == that.getCandidat_id();
    }

    public Demande_Recrutement(int id, int offre_id, int candidat_id, LocalDate date_debut, LocalDate date_expiration, boolean status) {
        this.id = id;
        this.offre_id = offre_id;
        this.candidat_id = candidat_id;
        this.date_debut = date_debut;
        this.date_expiration = date_expiration;
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOffre_id(), getCandidat_id());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    boolean status;
}
