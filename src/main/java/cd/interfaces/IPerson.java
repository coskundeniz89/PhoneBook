package cd.interfaces;

import cd.entities.Person;

import java.util.List;

public interface IPerson  {

  void delete(Person deleted);

  List<Person> findAll();

  Person findOne(Long id);

  Person save(Person persisted);

}