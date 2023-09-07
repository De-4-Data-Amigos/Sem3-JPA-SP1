package dao;

import config.HibernateConfig;
import dao.interfaces.IHobbyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;
import model.Hobby.HobbyCategory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOImplTest {

    private static EntityManagerFactory emf;

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
                .hobbyCategory(Hobby.HobbyCategory.GENEREL)
                .hobbyType(Hobby.HobbyType.INDENDØRS)
                .build();

        dao.createHobby(expectedHobby);
        Hobby actualHobby = dao.findById(expectedHobby.getId());
        assertNotNull(actualHobby, "Hobby with ID " + expectedHobby.getId() + " should not be null.");


        assertEquals(expectedHobby.getId(), actualHobby.getId());
        assertEquals(expectedHobby.getName(), actualHobby.getName());
        assertEquals(expectedHobby.getWikiLink(), actualHobby.getWikiLink());
        assertEquals(expectedHobby.getHobbyCategory(), actualHobby.getHobbyCategory());
        assertEquals(expectedHobby.getHobbyType(), actualHobby.getHobbyType());
        assertEquals(expectedHobby, actualHobby);

    }

    @Test
    void updateHobby() {
        Hobby testHobby = Hobby.builder()
                .name("Hotdog-spisning")
                .wikiLink("www.wikipedia.dk/hotdogspisning")
                .hobbyCategory(Hobby.HobbyCategory.GENEREL)
                .hobbyType(Hobby.HobbyType.KONKURRENCE)
                .build();

        dao.createHobby(testHobby);

        Hobby expectedHobby = dao.findById(testHobby.getId());

        expectedHobby.setCategory(HobbyCategory.KONKURRENCE);

        Hobby actualHobby = dao.updateHobby(expectedHobby);

        assertEquals(expectedHobby, actualHobby);

    }

    @Test
    void deleteHobby() {
        Hobby expectedHobby = Hobby.builder()
                .name("Test Hobby")
                .wikiLink("www.testhobby.com")
                .hobbyCategory(Hobby.HobbyCategory.GENEREL)
                .hobbyType(Hobby.HobbyType.INDENDØRS)
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
                .hobbyCategory(Hobby.HobbyCategory.GENEREL)
                .hobbyType(Hobby.HobbyType.INDENDØRS)
                .build();

        Hobby hobby2 = Hobby.builder()
                .name("Hobby2")
                .wikiLink("www.hobby2.com")
                .hobbyCategory(Hobby.HobbyCategory.GENEREL)
                .hobbyType(Hobby.HobbyType.UDENDØRS)
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