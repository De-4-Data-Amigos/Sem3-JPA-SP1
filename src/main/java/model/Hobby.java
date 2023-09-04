package model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_id", nullable = false, unique = true)
    private Integer hobbyId;

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
