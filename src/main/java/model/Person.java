package model;

<<<<<<< Updated upstream

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString

@Table(name = "person")
@Entity
=======
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

>>>>>>> Stashed changes
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
<<<<<<< Updated upstream
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstname;
=======
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
>>>>>>> Stashed changes

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;

<<<<<<< Updated upstream
    @Column(name = "phone_number", nullable = false)
    private int phoneNumber;

    public Person(String firstname, String surname, int age, int phoneNumber) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
=======
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Builder
    public Person(String name, String surname, int age, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
>>>>>>> Stashed changes
        this.phoneNumber = phoneNumber;
    }
}
