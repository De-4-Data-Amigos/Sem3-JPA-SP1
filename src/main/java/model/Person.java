package model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "person")

@Entity

@NamedQueries({
        // US som vi selv har lavet
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),

        // us 1,
        @NamedQuery(name = "Person.findPersonByPhoneNumber", query = "SELECT p FROM Person p WHERE p.phoneNumber = :phoneNumber"),
        //
        @NamedQuery(name = "Person.findAllPersons", query = "SELECT p FROM Person p"),
        // US 3+4
        @NamedQuery(name = "Person.findPersonByHobby", query = "SELECT p FROM Person p JOIN p.hobbies h WHERE h.id = :id"),

        // US 8
        @NamedQuery(name = "Person.getPersonInfoByPhoneNumber", query = "SELECT p FROM Person p LEFT JOIN FETCH p.personDetails pd LEFT JOIN FETCH pd.address a LEFT JOIN FETCH p.hobbies WHERE p.phoneNumber = :phoneNumber")


})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;


    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private int phoneNumber;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>();

    @OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST)
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
    private void onPrePersist() {
        LocalDate ld = LocalDate.now();
        creationDate = ld;
        modificationDate = ld;
    }

    @PreUpdate
    private void onPreUpdate() {
        modificationDate = LocalDate.now();
    }

    public void setPersonDetails(PersonDetails personDetails) {
        if (personDetails != null) {
            this.personDetails = personDetails;
            personDetails.setPerson(this);
        }
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            hobbies.add(hobby);
            hobby.getPersons().add(this);
        }
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return id == person.id &&
//                age == person.age &&
//                phoneNumber == person.phoneNumber &&
//                Objects.equals(firstName, person.firstName) &&
//                Objects.equals(surname, person.surname) &&
//                Objects.equals(email, person.email) &&
//                Objects.equals(creationDate, person.creationDate) &&
//                Objects.equals(modificationDate, person.modificationDate) &&
//                Objects.equals(hobbies, person.hobbies) &&
//                Objects.equals(personDetails, person.personDetails);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, surname, age, email, phoneNumber, creationDate, modificationDate, hobbies, personDetails);
//    }



}
