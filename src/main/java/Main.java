import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;
import model.Person;
import model.PersonDetails;

public class Main {
    protected static EntityManagerFactory emf;
    public static void main(String[] args) {

        HibernateConfig.addAnnotatedClasses(Hobby.class, Person.class, PersonDetails.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hobbydb");

        

    }
}