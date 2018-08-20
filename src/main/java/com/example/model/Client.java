package com.example.model;

import com.example.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY
//            , optional = false
            , cascade = CascadeType.MERGE)
    @MapsId
    private User user;

    @Size(min=3, max=30)
    @NotNull
    private String firstName;

    @Size(min=3, max=30)
    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Size(min=10, max=10)
    @NotNull
    private String inn;

    public Client(){}

    public Client(User user, @Size(min = 3, max = 30) @NotNull String firstName, @Size(min = 3, max = 30) @NotNull String lastName, @NotNull LocalDate birthday, @NotNull Gender gender, @Size(min = 10, max = 10) @NotNull String inn) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.inn = inn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(user, client.user) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(birthday, client.birthday) &&
                gender == client.gender &&
                Objects.equals(inn, client.inn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, firstName, lastName, birthday, gender, inn);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", inn='" + inn + '\'' +
                '}';
    }
}
