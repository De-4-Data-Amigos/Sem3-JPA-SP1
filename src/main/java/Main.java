import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    protected static EntityManagerFactory emf;
    public static void main(String[] args) {
        HibernateConfig.addAnnotatedClasses();
        emf = HibernateConfig.getEntityManagerFactoryConfig("hobbydb");

    }
}