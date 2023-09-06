package dao;

import config.HibernateConfig;
import dao.interfaces.IHobbyDAO;
import dao.interfaces.IPersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {
private static EntityManagerFactory emf;
private static EntityManager em;
private static IPersonDAO dao;
private static IHobbyDAO hobbyDao;


@BeforeAll
static void setUpAll() {
        HibernateConfig.addAnnotatedClasses(Hobby.class, Person.class, PersonDetails.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hobbydb");
        dao = PersonDAOImpl.getInstance(emf);
        hobbyDao = HobbyDAOImpl.getInstance(emf);
        }

@AfterEach
    void tearDown() {
            emf.close();
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
    Person actualPerson = dao.findById(expectedPerson.getId());
    Hibernate.initialize(actualPerson.getPersonDetails());
}


@Test
    void testUpdatePerson() {
    Person testPerson = Person.builder()
            .firstName("Dorthe")
            .surname("Lynnet")
            .age(25)
            .email("test5@test.dk")
            .phoneNumber(52524040)
            .build();

    dao.createPerson(testPerson);

    Person expectedPerson = dao.findById(testPerson.getId());

    expectedPerson.setEmail("Dao@dao.dk");
    expectedPerson.setFirstName("Pomfrit");

    Person actualPerson = dao.updatePerson(expectedPerson);

    assertEquals(expectedPerson, actualPerson);

}


@Test
        void removePerson(){
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
    Person deletePerson = dao.findById(id);
    assertNull(deletePerson);
}



@Test
    void findById() {
    Person expectedPerson = Person.builder()
            .firstName("Jens")
            .surname("Lyn")
            .age(25)
            .email("test@test.dk")
            .phoneNumber(40404040)
            .build();

    dao.createPerson(expectedPerson);
    int id = expectedPerson.getId();
    Person actualPerson = dao.findById(id);

    assertNotNull(actualPerson, "Person with ID " + id + " should not be null.");

    assertEquals(expectedPerson.getId(), actualPerson.getId());
    assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
    assertEquals(expectedPerson.getSurname(), actualPerson.getSurname());
    assertEquals(expectedPerson.getAge(), actualPerson.getAge());
    assertEquals(expectedPerson.getEmail(), actualPerson.getEmail());
    assertEquals(expectedPerson.getPhoneNumber(), actualPerson.getPhoneNumber());

    assertEquals(expectedPerson, actualPerson);
}



@Test
    void findAllPersons() {
    Person person1 = Person.builder()
            .firstName("Jens")
            .surname("Lyn")
            .age(25)
            .email("test1@test.dk")
            .phoneNumber(40404040)
            .build();

    Person person2 = Person.builder()
            .firstName("Mikkel")
            .surname("Jensen")
            .age(30)
            .email("test2@test.dk")
            .phoneNumber(50505050)
            .build();

    dao.createPerson(person1);
    dao.createPerson(person2);

    List<Person> allPersons = dao.findAllPersons();

    assertNotNull(allPersons, "List of persons should not be null.");
    assertFalse(allPersons.isEmpty(), "List of persons should not be empty.");
    assertTrue(allPersons.contains(person1), "List should contain person1.");
    assertTrue(allPersons.contains(person2), "List should contain person2.");
}



@Test
    void findAllPersonsSize() {
    Person person1 = Person.builder()
            .firstName("Jens")
            .surname("Lyn")
            .age(25)
            .email("test1@test.dk")
            .phoneNumber(40404040)
            .build();

    Person person2 = Person.builder()
            .firstName("Mikkel")
            .surname("Jensen")
            .age(30)
            .email("test2@test.dk")
            .phoneNumber(50505050)
            .build();

    dao.createPerson(person1);
    dao.createPerson(person2);
    int actualSize = dao.findAllPersonsSize();

    int expectedSize = 2;

    assertEquals(expectedSize, actualSize);
}


@Test
    void findPersonByHobby() {
    Hobby expectedHobby = Hobby.builder()
            .name("Hotdog-spisning")
            .wikiLink("www.wikipedia.dk/hotdogspisning")
            .category("Indendørs")
            .type("Pas..")
            .build();

    hobbyDao.createHobby(expectedHobby);


    Person person1 = Person.builder()
            .firstName("Jens")
            .surname("Lyn")
            .age(25)
            .email("test1@test.dk")
            .phoneNumber(40404040)
            .build();

    Person person2 = Person.builder()
            .firstName("Marcus")
            .surname("Jensen")
            .age(280)
            .email("test2@test.dk")
            .phoneNumber(50505050)
            .build();

    person1.addHobby(expectedHobby);
    person2.addHobby(expectedHobby);

    dao.createPerson(person1);
    dao.createPerson(person2);

    List<Person> personsWithHobby = dao.findPersonByHobby(expectedHobby.getId());

    int expectedSize = 2;

    assertEquals(expectedSize, personsWithHobby.size());

    for (Person person : personsWithHobby) {
        assertTrue(person.getHobbies().contains(expectedHobby));
    }
            }

@Test
    void findPersonByPhoneNumber() {

    Person expectedPerson = Person.builder()
            .firstName("Jens")
            .surname("Lyn")
            .age(25)
            .email("test@test.dk")
            .phoneNumber(40404040)
            .build();

    dao.createPerson(expectedPerson);

    int phoneNumber = 40404040;
    Person actualPerson = dao.findPersonByPhoneNumber(phoneNumber);

    assertNotNull(actualPerson, "Person with phoneNumber " + phoneNumber + " should not be null.");

    assertEquals(expectedPerson.getId(), actualPerson.getId());
    assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
    assertEquals(expectedPerson.getSurname(), actualPerson.getSurname());
    assertEquals(expectedPerson.getAge(), actualPerson.getAge());
    assertEquals(expectedPerson.getEmail(), actualPerson.getEmail());
    assertEquals(expectedPerson.getPhoneNumber(), actualPerson.getPhoneNumber());

    assertEquals(expectedPerson, actualPerson);
}


@Test
    void getPersonInfoByPhoneNumber() {
            }
            }





