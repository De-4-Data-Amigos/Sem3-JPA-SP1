package dao;

import config.HibernateConfig;
import dao.interfaces.IHobbyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOImplTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static IHobbyDAO dao;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.addAnnotatedClasses(Hobby.class, Person.class, PersonDetails.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("-insert-db-name-");
        dao = HobbyDAOImpl.getInstance(emf);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createHobby() {
        Hobby expectedHobby = Hobby.builder()
                .name("Hotdog-spisning")
                .wikiLink("www.wikipedia.dk/hotdogspisning")
                .category("Indendørs")
                .type("Pas..")
                .build();

        dao.createHobby(expectedHobby);

        Hobby actualHobby = dao.findById(expectedHobby.getId());

        assertEquals(expectedHobby, actualHobby);
    }

    @Test
    void updateHobby() {
        Hobby testHobby = Hobby.builder()
                .name("Hotdog-spisning")
                .wikiLink("www.wikipedia.dk/hotdogspisning")
                .category("Indendørs")
                .type("Pas..")
                .build();

        dao.createHobby(testHobby);

        Hobby expectedHobby = dao.findById(testHobby.getId());

        expectedHobby.setCategory("Udendørs");

        Hobby actualHobby = dao.updateHobby(expectedHobby);

        assertEquals(expectedHobby, actualHobby);

    }

    @Test
    void deleteHobby() {
        Hobby testHobby = Hobby.builder()
                .name("Hotdog-spisning")
                .wikiLink("www.wikipedia.dk/hotdogspisning")
                .category("Indendørs")
                .type("Pas..")
                .build();

        dao.createHobby(testHobby);

        int id = testHobby.getId();

        dao.deleteHobby(testHobby);

        assertThrows(Exception.class, () -> dao.findById(id));
    }

    @Test
    void findById() {
    }

    @Test
    void findHobby() {
    }

    @Test
    void testDeleteHobby() {
    }

    @Test
    void findPersonByHobby() {
    }
}