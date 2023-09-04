package model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "hobby")
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Hobby.findAllHobbies", query = "select h from Hobby h"),
                @NamedQuery(name = "Hobby.deleteAllHobbies", query = "delete from Hobby h"),
                @NamedQuery(name = "Hobby.deleteAllHobbies", query = "delete from Hobby h where h.id = :id")
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

    @Builder
    public Hobby(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }
}
