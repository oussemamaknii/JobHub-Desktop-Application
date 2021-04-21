package Entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class user {
    private int id;
    private String email;
    private String password;
    private int isActive;
    private String roles;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Date createdAt;
    private Date activatedAt;
    private Date updatedAt;
    private String adresse;
    private String professionalTitle;
    private String imageName;
    private int phone;

public user (String email, String password, String firstName, String lastName, LocalDate dateOfBirth, String adresse, int phone ){
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.adresse = adresse;
    this.phone = phone;
}

    public user() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getCreatedAt(LocalDate now) {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return getId() == user.getId() && getIsActive() == user.getIsActive() && getPhone() == user.getPhone() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getDateOfBirth(), user.getDateOfBirth()) && Objects.equals(createdAt, user.createdAt) && Objects.equals(getActivatedAt(), user.getActivatedAt()) && Objects.equals(getUpdatedAt(), user.getUpdatedAt()) && Objects.equals(getAdresse(), user.getAdresse()) && Objects.equals(getProfessionalTitle(), user.getProfessionalTitle()) && Objects.equals(getImageName(), user.getImageName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getIsActive(), getRoles(), getFirstName(), getLastName(), getDateOfBirth(), createdAt, getActivatedAt(), getUpdatedAt(), getAdresse(), getProfessionalTitle(), getImageName(), getPhone());
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", roles='" + roles + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", activatedAt=" + activatedAt +
                ", updatedAt=" + updatedAt +
                ", adresse='" + adresse + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", imageName='" + imageName + '\'' +
                ", phone=" + phone +
                '}';
    }
}
