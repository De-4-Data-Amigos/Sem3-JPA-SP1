package model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
                @NamedQuery(name = "Hobby.deleteAllHobbies", query = "delete from Hobby h where h.id = :id"),
                @NamedQuery(name = "Hobby.findCount", query = "SELECT h.name, COUNT(p) FROM Hobby h JOIN h.person p GROUP BY h.name")

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

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    private Person person;

    @Builder
    public Hobby(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }
}
