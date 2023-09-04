package dao;

import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOImplTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static HobbyDAOImpl dao;

    @BeforeAll
    void setUpAll() {

        emf = HibernateConfig.getEntityManagerFactoryConfig("-insert-db-name-");
        dao = HobbyDAOImpl.getInstance(emf); //Cast expression fikser, men..?
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

        Hobby actualHobby = dao.findHobby(expectedHobby.getId());

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

        Hobby expectedHobby = dao.findHobby(testHobby.getId());

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

        assertThrows(Exception.class, () -> dao.findHobby(id));
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