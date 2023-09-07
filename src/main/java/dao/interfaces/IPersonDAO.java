package dao.interfaces;

import model.Person;

import java.util.List;

public interface IPersonDAO {


    void createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);

    Person findById(Integer personId);

    List<Person> findCityPersonById(Integer personId);

    List<Person> findAllPersons();

    List<Person> findPersonByHobby(Integer hobby);



    int findAllPersonsSize();

    Person findPersonByPhoneNumber(Integer phoneNumber);

    List<Person> getPersonInfoByPhoneNumber(String phoneNumber);

}
