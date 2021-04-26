package Entities;

import java.util.Objects;

public class candidateResume {
    private int id;
    private int user_id;
    private String resumeHeadline;
    private String skills;
    private String experience;

    public candidateResume(String resumeHeadline, String skills, String experience) {
        this.resumeHeadline = resumeHeadline;
        this.skills = skills;
        this.experience = experience;
    }

    public candidateResume(int id, int user_id, String resume_headline, String skills, String experience) {

    }

    public candidateResume() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getResumeHeadline() {
        return resumeHeadline;
    }

    public void setResumeHeadline(String resumeHeadline) {
        this.resumeHeadline = resumeHeadline;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        candidateResume that = (candidateResume) o;
        return getId() == that.getId() && getUser_id() == that.getUser_id() && Objects.equals(getResumeHeadline(), that.getResumeHeadline()) && Objects.equals(getSkills(), that.getSkills()) && Objects.equals(getExperience(), that.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getResumeHeadline(), getSkills(), getExperience());
    }

    @Override
    public String toString() {
        return "candidateResume{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", resumeHeadline='" + resumeHeadline + '\'' +
                ", skills='" + skills + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
