package dao;

import dao.interfaces.IHobbyDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;
import org.w3c.dom.html.HTMLMetaElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HobbyDAOImpl implements IHobbyDAO {

    private static EntityManagerFactory emf;
    private static IHobbyDAO instance;

    public static IHobbyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyDAOImpl();
        }
        return instance;
    }

    @Override
    public void createHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        }
    }

    @Override
    public Hobby updateHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        }
        return hobby;
    }


    public void deleteHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        }
    }

    @Override
    public Hobby findById(Integer hobbyId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Hobby> typedQuery = em.createNamedQuery("Hobby.findById", Hobby.class);
            return typedQuery.getSingleResult();
        }
    }

    @Override
    public List<Hobby> findHobby() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Hobby> typedQuery = em.createNamedQuery("Hobby.findAllHobbies", Hobby.class);
            return typedQuery.getResultList();
        }
    }

    @Override
    public List<Hobby> deleteHobby() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Hobby> typedQuery = em.createNamedQuery("Hobby.deleteAllHobbies", Hobby.class);
            return typedQuery.getResultList();
        }
    }

    //US5 pt1
    @Override
    public List<Person> findPersonByHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> typedQuery = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.id = :id", Person.class);
            typedQuery.setParameter("hobby_id", hobby.getId());
            return typedQuery.getResultList();
        }
    }

    @Override
    public Map<String, Integer> findAmountOfUsersForeachHobby() {
        Map<String, Integer> hobbiesAndAmount = new HashMap<>();
        List<Object[]> retValue;
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createNamedQuery("Hobby.findCountForAllHobbies", Hobby.class);
            retValue = query.getResultList();
        }
        for (Object[] elementValues : retValue) {
            hobbiesAndAmount.put((String)elementValues[0], (Integer)elementValues[1]);
        }
        return hobbiesAndAmount;
    }
}
