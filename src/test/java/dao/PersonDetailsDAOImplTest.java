package dao;

import config.HibernateConfig;
import dao.interfaces.IPersonDAO;
import dao.interfaces.IPersonDetailsDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PersonDetailsDAOImplTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static IPersonDetailsDAO dao;

    @BeforeEach
    void setUp() {
        HibernateConfig.addAnnotatedClasses(Hobby.class, Person.class, PersonDetails.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("-insert-db-name-");
        dao = PersonDetailsDAOImpl.getInstance(emf); //Cast expression fikser, men..?
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPersonDetails() {
        PersonDetails expectedPersonDetails = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(expectedPersonDetails);

        PersonDetails actualPerson = dao.findById(expectedPersonDetails.getId());

        assertEquals(expectedPersonDetails, actualPerson);
    }


    @Test
    void updatePersonDetails() {
        PersonDetails testPersonDetails = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails);

        PersonDetails expectedPerson = dao.findById(testPersonDetails.getId());

        expectedPerson.setZipcode(3070);
        expectedPerson.setCityName("Snekkersten");

        PersonDetails actualPerson = dao.updatePersonDetails(expectedPerson);

        assertEquals(expectedPerson, actualPerson);

    }

    @Test
    void deletePersonDetails() {
        PersonDetails testPersonDetails = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails);

        int id = testPersonDetails.getId();

        dao.deletePersonDetails(testPersonDetails);

        assertThrows(Exception.class, () -> dao.findById(id));

    }

    @Test
    void findById() {
    }

    @Test
    void findCityPersonById() {
        PersonDetails testPersonDetails = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails);

        int id = testPersonDetails.getId();

        String actual = dao.findCityPersonById(id);

        assertEquals("Helsingør", actual);

    }

    @Test
    void findAllUsersInACity() {
        PersonDetails testPersonDetails = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails);


        PersonDetails testPersonDetails2 = PersonDetails.builder()
                .zipcode(3000)
                .address("Bobslædevej 62")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails2);

        List<PersonDetails> actual = dao.findAllUsersInACity("Helsingør");

        assertEquals(2, actual.size());

    }

    @Test
    void findAllZipAndCityNames() {
        PersonDetails testPersonDetails1 = PersonDetails.builder()
                .zipcode(3000)
                .address("Langevej 48")
                .cityName("Helsingør")
                .regionName("Region Hovedstaden")
                .municipalityName("Helsingør Komunne")
                .build();

        dao.createPersonDetails(testPersonDetails1);

        List<Object[]> actual = dao.findAllZipAndCityNames(testPersonDetails1);

        assertEquals(3000,actual.get(0)[0]);
        assertEquals("Helsingør",actual.get(0)[1]);



    }
}