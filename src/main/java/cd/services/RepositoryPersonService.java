package cd.services;

import cd.dto.SearchDTO;
import cd.enm.SearchType;
import cd.entities.Person;
import cd.interfaces.IPersonService;
import cd.interfaces.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by coskun.deniz on 08.06.2015.
 */
@Service
public class RepositoryPersonService implements IPersonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryPersonService.class);

  @Resource
  private PersonRepository personRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Person> search(SearchDTO searchDTO) {
    LOGGER.debug("searching persons with search criteria:" + searchDTO);

    String searchTerm = searchDTO.getSearchTerm();
    SearchType searchType = searchDTO.getSearchType();

    if (searchType == null) {
      throw new IllegalArgumentException();
    }
    return findPersonsBySearchType(searchTerm, searchType);
  }

  private List<Person> findPersonsBySearchType(String searchTerm, SearchType searchType) {
    List<Person> persons ;

    if (searchType == SearchType.METHOD_NAME) {
      persons = personRepository.findByLastName(searchTerm);
    }else if (searchType == SearchType.NAMED_QUERY) {
      persons = personRepository.findByName(searchTerm);
    } else {
      persons = personRepository.find(searchTerm);
    }


    return persons;
  }

  protected void setPersonRepository(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }
}
