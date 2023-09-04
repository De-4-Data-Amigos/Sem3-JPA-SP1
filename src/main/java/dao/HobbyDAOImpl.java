package dao;

import dao.interfaces.IHobbyDAO;
import dao.interfaces.IPersonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hobby;

public class HobbyDAOImpl implements IHobbyDAO {

    private static EntityManagerFactory emf;
    private static IHobbyDAO instance;

    public static IHobbyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyDAOImpl();


            @Override
            public void createHobby (Hobby hobby){
                try (EntityManager em = emf.createEntityManager()) {
                    em.getTransaction().begin();
                    em.persist(hobby);
                    em.getTransaction().commit();
                }
            }

            @Override
            public Hobby updateHobby (Hobby hobby){
                try (EntityManager em = emf.createEntityManager()) {
                    em.getTransaction().begin();
                    em.merge(hobby);
                    em.getTransaction().commit();
                }
                return hobby;
            }
            @Override
            public Hobby findById (Integer hobbyId){
                return null;
            }
        }
        public void deleteHobby (Hobby hobby){
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.remove(hobby);
                em.getTransaction().commit();
            }
        }
    }
}