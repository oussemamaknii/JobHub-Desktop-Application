package Entities;

import java.sql.Date;
import java.util.Objects;

public class education {
    private int id;
    private int resumeId;
    private String course;
    private String institute;
    private Date dateFrom;
    private Date dateTo;

    public education(int id, int resumeId, String course, String institute, Date dateFrom, Date dateTo) {
        this.id = id;
        this.resumeId = resumeId;
        this.course = course;
        this.institute = institute;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        education education = (education) o;
        return getId() == education.getId() && getResumeId() == education.getResumeId() && Objects.equals(getCourse(), education.getCourse()) && Objects.equals(getInstitute(), education.getInstitute()) && Objects.equals(getDateFrom(), education.getDateFrom()) && Objects.equals(getDateTo(), education.getDateTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getResumeId(), getCourse(), getInstitute(), getDateFrom(), getDateTo());
    }

    @Override
    public String toString() {
        return "education{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", course='" + course + '\'' +
                ", institute='" + institute + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

}
