import config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;

public class Main {
    protected static EntityManagerFactory emf;
    public static void main(String[] args) {
        HibernateConfig.addAnnotatedClasses(Person.class, PersonDetails.class, Hobby.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hobbydb");
        Person p1 = Person.builder()
                .age(21)
                .email("email.mail@mail.com")
                .firstName("test")
                .surname("testsurname")
                .phoneNumber(12345678)
                .build();
        PersonDetails pd1 = new PersonDetails(123, "cityname", "hovedstaten", "Kommune" );

        Person p2 = Person.builder()
                .age(22)
                .email("email1.mail@mail.com")
                .firstName("test1")
                .surname("testsurname1")
                .phoneNumber(22345678)
                .build();
        PersonDetails pd2 = new PersonDetails(124, "cityname1", "hovedstaten", "Kommune" );

        Person p3 = Person.builder()
                .age(23)
                .email("email2.mail@mail.com")
                .firstName("test2")
                .surname("testsurname2")
                .phoneNumber(32345678)
                .build();
        PersonDetails pd3 = new PersonDetails(125, "cityname2", "hovedstaten", "Kommune" );

        Hobby h1 = new Hobby("HobbyName1", "wikilink", "cat1", "type1");
        Hobby h2 = new Hobby("HobbyName2", "wikilink", "cat2", "type2");
        p1.addHobby(h1);
        p1.addHobby(h2);
        p2.addHobby(h1);
        p3.addHobby(h1);
        p3.addHobby(h2);

        p1.addPersonDetails(pd1);
        p2.addPersonDetails(pd2);
        p3.addPersonDetails(pd3);

        h1.addPerson(p1);
        h1.addPerson(p2);
        h1.addPerson(p3);
        h2.addPerson(p1);
        h2.addPerson(p3);


        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(pd1);
            em.persist(p2);
            em.persist(pd2);
            em.persist(p3);
            em.persist(pd3);
            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();
        }
    }
}