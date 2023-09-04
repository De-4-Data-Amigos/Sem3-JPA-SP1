package dao;

import dao.interfaces.IHobbyDAO;
import dao.interfaces.IPersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;

import java.util.List;

public class HobbyDAOImpl implements IHobbyDAO {

    private static EntityManagerFactory emf;
    private static IHobbyDAO instance;

    public static IHobbyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyDAOImpl() {

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
                    return null;
                }

                @Override
                public List<Hobby> findHobby() {
                    try (EntityManager em = emf.createEntityManager()) {
                        TypedQuery<Hobby> typedQuery = em.createNamedQuery("", Hobby.class);
                        return typedQuery.getResultList();
                    }
                }

                @Override
                public List<Person> findPersonByHobby(Hobby hobby) {
                    return null;
                }
            }
        }
    }
