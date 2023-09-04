package model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person_details")
public class PersonDetails {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String Id;


    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "commune_name")
    private String communeName;

    public PersonDetails(int zipcode, String cityName, String regionName, String communeName) {
        this.zipcode = zipcode;
        this.cityName = cityName;
        this.regionName = regionName;
        this.communeName = communeName;
    }
}
