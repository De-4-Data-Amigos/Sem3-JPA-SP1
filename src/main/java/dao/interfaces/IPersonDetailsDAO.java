package dao.interfaces;


import model.Hobby;
import model.Person;
import model.PersonDetails;

import java.util.List;

public interface IPersonDetailsDAO {

    void createPersonDetails(PersonDetails personDetails);

    PersonDetails findById(Integer personDetailsId);

    PersonDetails updatePersonDetails(PersonDetails personDetails);

    void deletePersonDetails(PersonDetails personDetails);

    String findCityPersonById(Integer personId);

    List<PersonDetails> findAllUsersInACity(String cityName);

    List<Object[]> findAllZipAndCityNames(PersonDetails personDetails);

}
