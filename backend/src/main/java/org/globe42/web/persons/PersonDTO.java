package org.globe42.web.persons;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.globe42.domain.Gender;
import org.globe42.domain.Person;

/**
 * DTO for Person
 * @author JB Nizet
 */
public final class PersonDTO {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String surName;
    private final LocalDate birthDate;
    private final String mediationCode;
    private final String address;
    private final CityDTO city;
    private final String email;

    @JsonProperty("isAdherent")
    private final boolean adherent;
    private final LocalDate entryDate;
    private final Gender gender;
    private final String phoneNumber;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.surName = person.getSurName();
        this.birthDate = person.getBirthDate();
        this.mediationCode = person.getMediationCode();
        this.address = person.getAddress();
        this.city = person.getCity() == null ? null : new CityDTO(person.getCity());
        this.email = person.getEmail();
        this.adherent = person.isAdherent();
        this.entryDate = person.getEntryDate();
        this.gender = person.getGender();
        this.phoneNumber = person.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMediationCode() {
        return mediationCode;
    }

    public String getAddress() {
        return address;
    }

    public CityDTO getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdherent() {
        return adherent;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
