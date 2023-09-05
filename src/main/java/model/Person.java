package model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
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

        @NamedQuery(name = "Person.findAllPersons", query = "SELECT p FROM Person p"),
        // US 3+4
        @NamedQuery(name = "Person.findPersonByHobby", query = "SELECT p FROM Person p WHERE p.hobby = :id")

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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private int phoneNumber;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @ManyToMany(mappedBy = "person")
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

    @PrePersist
    private void onPrePersist(){
        LocalDate ld = LocalDate.now();
        creationDate = ld;
        modificationDate = ld;
    }

    @PreUpdate
    private void onPreUpdate(){
        modificationDate = LocalDate.now();
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  
}
