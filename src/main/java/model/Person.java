package model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "person")
@Entity

@NamedQueries({
        // US1
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.findPersonByPhoneNumber", query = "SELECT p FROM Person p WHERE p.phoneNumber = :phoneNumber"),

        @NamedQuery(name = "Person.findAllPersons", query = "SELECT p FROM Person p"),
        // US 3+4
        @NamedQuery(name = "Person.findPersonByHobby", query = "SELECT p FROM Person p WHERE p.hobby  = :id")

        //Mangler us 2,

        // Mangler US 8


})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;

    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private int phoneNumber;

    @OneToMany(mappedBy = "person")
    private Set<Hobby> hobby;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private PersonDetails personDetails;

    @Builder
    public Person(String firstName, String surname, int age, String email, int phoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
