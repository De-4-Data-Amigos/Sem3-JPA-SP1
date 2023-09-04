package dao;

import dao.interfaces.IPersonDetailsDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;
import model.PersonDetails;

import java.util.List;

public class PersonDetailsDAOImpl implements IPersonDetailsDAO {
    private static EntityManagerFactory emf;

    private static IPersonDetailsDAO instance;

    public static IPersonDetailsDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDetailsDAOImpl();
        }
        return instance;
    }


    @Override
    public void createPersonDetails(PersonDetails personDetails) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(personDetails);
            em.getTransaction().commit();
        }
    }

    @Override
    public PersonDetails findById(Integer personDetailsId) {
        return null;
    }

    @Override
    public PersonDetails updatePersonDetails(PersonDetails personDetails) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(personDetails);
            em.getTransaction().commit();
        }
        return personDetails;
    }

    @Override
    public void deletePersonDetails(PersonDetails personDetails) {
try(EntityManager em = emf.createEntityManager()){
    em.getTransaction().begin();
    em.remove(personDetails);
    em.getTransaction().commit();
}
    }
}
