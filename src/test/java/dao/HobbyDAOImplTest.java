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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOImplTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static IHobbyDAO dao;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.addAnnotatedClasses(Hobby.class, Person.class, PersonDetails.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hobbydb");
        dao = HobbyDAOImpl.getInstance(emf);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createHobby() {

        Hobby expectedHobby = Hobby.builder()
                .name("Test Hobby")
                .wikiLink("www.testhobby.com")
                .category("Indendørs")
                .type("Test")
                .build();

        dao.createHobby(expectedHobby);
        Hobby actualHobby = dao.findById(expectedHobby.getId());
        assertNotNull(actualHobby, "Hobby with ID " + expectedHobby.getId() + " should not be null.");


        assertEquals(expectedHobby.getId(), actualHobby.getId());
        assertEquals(expectedHobby.getName(), actualHobby.getName());
        assertEquals(expectedHobby.getWikiLink(), actualHobby.getWikiLink());
        assertEquals(expectedHobby.getCategory(), actualHobby.getCategory());
        assertEquals(expectedHobby.getType(), actualHobby.getType());
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
        Hobby expectedHobby = Hobby.builder()
                .name("Test Hobby")
                .wikiLink("www.testhobby.com")
                .category("Indendørs")
                .type("Test")
                .build();

        dao.createHobby(expectedHobby);
        dao.deleteHobby(expectedHobby);

        Hobby deletedHobby = dao.findById(expectedHobby.getId());

        assertNull(deletedHobby, "Deleted hobby should be null.");
    }

    @Test

    void findHobby() {

        Hobby hobby1 = Hobby.builder()
                .name("Hobby1")
                .wikiLink("www.hobby1.com")
                .category("Indendørs")
                .type("Type1")
                .build();

        Hobby hobby2 = Hobby.builder()
                .name("Hobby2")
                .wikiLink("www.hobby2.com")
                .category("Udendørs")
                .type("Type2")
                .build();

        dao.createHobby(hobby1);
        dao.createHobby(hobby2);


        List<Hobby> allHobbies = dao.findHobby();

        assertNotNull(allHobbies, "List of hobbies should not be null.");
        assertFalse(allHobbies.isEmpty(), "List of hobbies should not be empty.");
        assertTrue(allHobbies.contains(hobby1), "List should contain hobby1.");
        assertTrue(allHobbies.contains(hobby2), "List should contain hobby2.");
    }

        }