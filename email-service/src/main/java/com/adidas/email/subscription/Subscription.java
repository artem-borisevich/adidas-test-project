package com.adidas.email.subscription;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {

    private Long id;

    private String email;

    private String firstName;

    private String gender;

    private LocalDate dateOfBirth;

    private Boolean consent;

    private Long newsletterId;

    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public Long getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", consent=" + consent +
                ", newsletterId=" + newsletterId +
                ", createdDate=" + createdDate +
                '}';
    }
}
