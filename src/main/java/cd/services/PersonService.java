package cd.services;

import cd.daos.PersonDaoImp;
import cd.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by coskun.deniz on 05.06.2015.
 */
@Service
@Transactional
public class PersonService {
  private PersonDaoImp personDao;

  @Autowired
  PersonService(PersonDaoImp personDao) {
    this.personDao = personDao;
  }


  public List<Person> findAll() {
    return personDao.findAll();
  }


}
