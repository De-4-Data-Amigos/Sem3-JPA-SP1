package model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "hobby")
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Hobby.findAllHobbies", query = "select h from Hobby h"),
                //US 5, mangler count
                @NamedQuery(name = "Hobby.deleteAllHobbies", query = "delete from Hobby h"),

                @NamedQuery(name = "Hobby.findById", query = "select h from Hobby h where h.id = :id"),
                @NamedQuery(name = "Hobby.findCountForAllHobbies", query = "SELECT h.name, COUNT(p) FROM Hobby h JOIN h.persons p GROUP BY h.name"),
                @NamedQuery(name = "Hobby.deleteFromId", query = "delete from Hobby h where h.id = :id")
        }
)
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "wikiLink", nullable = false)
    private String wikiLink;

    //@Column(name = "category", nullable = false)
    //private String category;

    //@Column(name = "type", nullable = false)
    //private String type;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Set<Person> persons = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private HobbyType hobbyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private HobbyCategory hobbyCategory;

    @Builder
    public Hobby(String name, String wikiLink, HobbyCategory hobbyCategory, HobbyType hobbyType) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.hobbyCategory = hobbyCategory;
        this.hobbyType = hobbyType;

    }

    public void setCategory(HobbyCategory category) {
        this.hobbyCategory = category;
    }


    public enum HobbyType {

        INDENDØRS,
        UDENDØRS,
        ---,
        KONKURRENCE,
        INDENDØRS-UDENDØRS
    }

    public enum HobbyCategory {

        GENEREL,
        KONKURRENCE,
        OBSERVATION,
        EDUCATIONAL_HOBBIES
    }

    public void addPerson(Person person) {
        if (person != null) {
            persons.add(person);
        }
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return id == hobby.id &&
                Objects.equals(name, hobby.name) &&
                Objects.equals(wikiLink, hobby.wikiLink) &&
                Objects.equals(hobbyCategory, hobby.hobbyCategory) &&
                Objects.equals(hobbyType, hobby.hobbyType) &&
                Objects.equals(creationDate, hobby.creationDate) &&
                Objects.equals(modificationDate, hobby.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, wikiLink, hobbyCategory, hobbyType, creationDate, modificationDate);
    }

}
