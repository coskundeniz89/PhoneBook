package cd.interfaces;

import cd.entities.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface TodoRepository extends Repository<Person, Long> {
  void delete(Person deleted);

  List<Person> findAll();

  Optional<Person> findOne(Long id);

  Person save(Person persisted);
}