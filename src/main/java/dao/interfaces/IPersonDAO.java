package dao.interfaces;

import model.Hobby;
import model.Person;
import org.hibernate.dialect.lock.PessimisticEntityLockException;

import java.util.List;

public interface IPersonDAO {


    void createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);

    Person findById(Integer personId);

    List<Person> findCityPersonById(Integer personId);

    List<Person> findAllPersons();

    List<Person> findPersonByHobby(Hobby hobby);

    Person findPersonByPhoneNumber(String phoneNumber);

    int findAllPersonsSize();

}
