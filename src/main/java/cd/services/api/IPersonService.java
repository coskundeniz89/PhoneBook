package cd.services.api;

import cd.dto.SearchDTO;
import cd.model.Person;

import java.util.List;

/**
 * Created by coskun.deniz on 09.06.2015.
 */
public interface IPersonService {

//  public Person findById(Long id);

  public List<Person> search(SearchDTO searchCriteria);
}
