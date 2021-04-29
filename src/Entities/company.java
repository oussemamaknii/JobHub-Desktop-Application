package Entities;

import java.time.LocalDate;
import java.util.Objects;

public class company {
    private int id;
    private String website;
    private LocalDate foundedDate;
    private String category;
    private String country;
    private String description;
    private String companyName;
    private String contactEmail;
    private int contactPhone;
    private String companyAdress;
    private String facebookLink;
    private String twitterLink;
    private double stars;
    private int userId;
    private String companyImageName;




    public company() {
    }

    public company(String companyName, String contactEmail, String companyAdress, LocalDate foundedDate, String website, int contactPhone, String category, String facebookLink) {
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.companyAdress = companyAdress;
        this.foundedDate = foundedDate;
        this.website = website;
        this.contactPhone = contactPhone;
        this.category = category;
        this.facebookLink = facebookLink;
    }


    public String getCompanyImageName() {
        return companyImageName;
    }

    public void setCompanyImageName(String companyImageName) {
        this.companyImageName = companyImageName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDate getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(LocalDate foundedDate) {
        this.foundedDate = foundedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(int contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public void setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        company company = (company) o;
        return getId() == company.getId() && getContactPhone() == company.getContactPhone() && Double.compare(company.getStars(), getStars()) == 0 && Objects.equals(getWebsite(), company.getWebsite()) && Objects.equals(getFoundedDate(), company.getFoundedDate()) && Objects.equals(getCategory(), company.getCategory()) && Objects.equals(getCountry(), company.getCountry()) && Objects.equals(getDescription(), company.getDescription()) && Objects.equals(getCompanyName(), company.getCompanyName()) && Objects.equals(getContactEmail(), company.getContactEmail()) && Objects.equals(getCompanyAdress(), company.getCompanyAdress()) && Objects.equals(getFacebookLink(), company.getFacebookLink()) && Objects.equals(getTwitterLink(), company.getTwitterLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWebsite(), getFoundedDate(), getCategory(), getCountry(), getDescription(), getCompanyName(), getContactEmail(), getContactPhone(), getCompanyAdress(), getFacebookLink(), getTwitterLink(), getStars());
    }

    @Override
    public String toString() {
        return "company{" +
                "id=" + id +
                ", website='" + website + '\'' +
                ", foundedDate=" + foundedDate +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone=" + contactPhone +
                ", companyAdress='" + companyAdress + '\'' +
                ", facebookLink='" + facebookLink + '\'' +
                ", twitterLink='" + twitterLink + '\'' +
                ", stars=" + stars +
                '}';
    }
}
