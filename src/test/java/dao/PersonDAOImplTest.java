package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static PersonDAOImpl dao;

    @BeforeAll
    void setUpAll() {

        emf = HibernateConfig.getEntityManagerFactoryConfig("-insert-db-name-");
        dao = PersonDAOImpl.getInstance(emf); //Cast expression fikser, men..?
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPerson() {
        Person expectedPerson = Person.builder()
                .firstName("Jens")
                .surname("Lyn")
                .age(25)
                .email("test@test.dk")
                .phoneNumber(40404040)
                .build();


    dao.createPerson(expectedPerson);

    Person actualPerson = dao.findPerson(expectedPerson.getId());

    assertEquals(expectedPerson, actualPerson);

}

    @Test
    void updatePerson() {
        Person testPerson = Person.builder()
                .firstName("Jens")
                .surname("Lyn")
                .age(25)
                .email("test@test.dk")
                .phoneNumber(40404040)
                .build();

    dao.createPerson(testPerson);

    Person expectedPerson = dao.findPerson(testPerson.getId());

    expectedPerson.setEmail("Dao@dao.dk");
    expectedPerson.setFirstName("Pomfrit");

    Person actualPerson = dao.updatePerson(expectedPerson);

    assertEquals(expectedPerson, actualPerson);

    }

    @Test
    void deletePerson() {
        Person testPerson = Person.builder()
                .firstName("Jens")
                .surname("Lyn")
                .age(25)
                .email("test@test.dk")
                .phoneNumber(40404040)
                .build();

        dao.createPerson(testPerson);

        int id = testPerson.getId();

        dao.deletePerson(testPerson);

        assertThrows(Exception.class, () -> dao.findPerson(id));

    }

    @Test
    void findById() {
    }

    @Test
    void findCityPersonById() {
    }

    @Test
    void findPerson() {
    }

    @Test
    void findPersonByHobby() {
    }
}