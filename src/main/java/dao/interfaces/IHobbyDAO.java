package dao.interfaces;

import model.Hobby;
import model.Person;

import java.util.List;

public interface IHobbyDAO {

    void createHobby(Hobby hobby);

    Hobby updateHobby(Hobby hobby);

    void deleteHobby(Hobby hobby);

    Hobby findById(Integer hobbyId);

    List<Hobby> findHobby();

    List<Person> findPersonByHobby(Hobby hobby);

    

}
