package cd.daos;

import cd.entities.Person;
import cd.interfaces.IPerson;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by coskun.deniz on 05.06.2015.
 */
@Repository
@Transactional
public class PersonDaoImp implements IPerson {

  @PersistenceContext
  private EntityManager em;

  public void delete(Person deleted) {

  }

  @Override
  @Transactional(readOnly = true)
  public List<Person> findAll() {
    return em.createQuery("select u from Person u", Person.class).getResultList();
  }

  public Person findOne(Long id) {
    return null;
  }

  public Person save(Person persisted) {
    return em.merge(persisted);
  }
}
