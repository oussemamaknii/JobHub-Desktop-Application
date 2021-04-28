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
public class comment {
    private int id;
    private String name;
    private String email;
    private long phone;
    private String message;
    private LocalDate created_at;
    private int idEvent;

    public comment(int id, String name, String email, long phone, String message, LocalDate created_at, int idEvent) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.created_at = created_at;
        this.idEvent = idEvent;
    }

     public comment(String name, String email, long phone, String message, LocalDate created_at, int idEvent) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.created_at = created_at;
        this.idEvent = idEvent;
    }

    public comment() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override


    public String toString() {
        return "comment{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", message=" + message + ", created_at=" + created_at + ", idEvent=" + idEvent + '}';
    }


}
