package dao;

import dao.interfaces.IPersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hobby;
import model.Person;

import java.util.List;

public class PersonDAOImpl implements IPersonDAO {

    private static EntityManagerFactory emf;
    private static IPersonDAO instance;

    public static IPersonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDAOImpl();

            }
   return instance;
            }


                @Override
                public void createPerson(Person person) {
                 try(EntityManager em = emf.createEntityManager()){
                     em.getTransaction().begin();
                     em.persist(person);
                     em.getTransaction().commit();
                 }
                }

                @Override
                public Person updatePerson(Person person) {
                    try(EntityManager em = emf.createEntityManager()){
                        em.getTransaction().begin();
                        em.merge(person);
                        em.getTransaction().commit();
                    }
                    return person;                }

                @Override
                public void deletePerson(Person person) {
                    try(EntityManager em = emf.createEntityManager()){
                        em.getTransaction().begin();
                        em.remove(person);
                        em.getTransaction().commit();
                    }
                }

                @Override
                public Person findById(Integer personId) {
                    return null;
                }

                @Override
                public Person findCityPersonById(Integer personId) {
                    try (EntityManager em = emf.createEntityManager()) {
                        return em.find(Person.class,personId);

                    }
                }

                @Override
                public List<Person> findPerson() {

                    try (EntityManager em = emf.createEntityManager()) {
                    TypedQuery<Person> typedQuery = em.createNamedQuery("", Person.class);
                        return  typedQuery.getResultList();

                    }

                }

                @Override
                public List<Person> findPersonByHobby(Hobby hobby) {
                    try (EntityManager em = emf.createEntityManager()) {
                        TypedQuery<Person> typedQuery = em.createNamedQuery("", Person.class);
                       typedQuery.setParameter("id", hobby);
                        return typedQuery.getResultList();
                }


    }


}
