package dao;

import dao.interfaces.IPersonDetailsDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(personDetails);
            em.getTransaction().commit();
        }
    }

    @Override
    public PersonDetails findById(Integer personDetailsId) {
        try (EntityManager em = emf.createEntityManager()) {
            return  em.find(PersonDetails.class, personDetailsId);
        }
    }

    @Override
    public PersonDetails updatePersonDetails(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(personDetails);
            em.getTransaction().commit();
        }
        return personDetails;
    }

    @Override
    public void deletePersonDetails(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(personDetails);
            em.getTransaction().commit();
        }
    }


    @Override
    public String findCityPersonById(Integer personId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<String> typedQuery = em.createNamedQuery("Person.findCityPersonById", String.class);
            typedQuery.setParameter("id", personId);
            return typedQuery.getSingleResult();
        }
    }

    @Override
    public List<PersonDetails> findAllUsersInACity(String cityName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<PersonDetails> typedQuery = em.createNamedQuery("Person.findAllUsersInACity", PersonDetails.class);
            typedQuery.setParameter("cityName", cityName);
            return typedQuery.getResultList();
        }

    }

    @Override
    public List<Object[]> findAllZipAndCityNames(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Object[]> query = em.createNamedQuery("Person.findAllZipAndCityNames", Object[].class);
           // query.setParameter("cityName", personDetails.getCityName());
            return query.getResultList();
        }
    }
}
