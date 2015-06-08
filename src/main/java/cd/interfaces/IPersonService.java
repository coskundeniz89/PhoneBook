package cd.interfaces;

import cd.dto.SearchDTO;
import cd.entities.Person;

import java.util.List;

/**
 * Created by coskun.deniz on 08.06.2015.
 */
public interface IPersonService {

  /**
   * Searches persons by using the search criteria given as a parameter.
   * @param searchDTO
   * @return  A list of persons matching with the search criteria. If no persons is found, this method
   *          returns an empty list.
   * @throws IllegalArgumentException if search type is not given.
   */
  public List<Person> search(SearchDTO searchDTO);


}
