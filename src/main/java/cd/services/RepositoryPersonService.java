package cd.services;

import cd.dto.SearchDTO;
import cd.dto.SearchType;
import cd.model.Person;
import cd.repositories.PersonRepository;
import cd.services.api.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by coskun.deniz on 09.06.2015.
 */
@Service
public class RepositoryPersonService implements IPersonService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryPersonService.class);

  @Resource
  private PersonRepository personRepository;


  @Transactional(readOnly = true)
  @Override
  public List<Person> search(SearchDTO searchCriteria) {
    LOGGER.debug("Searching persons with search criteria: " + searchCriteria);

    String searchTerm = searchCriteria.getSearchTerm();
    SearchType searchType = searchCriteria.getSearchType();

    if (searchType == null) {
      throw new IllegalArgumentException();
    }

    return findPersonsBySearchType(searchTerm, searchType);
  }

  private List<Person> findPersonsBySearchType(String searchTerm, SearchType searchType) {
    List<Person> persons;
    if (searchType == SearchType.METHOD_NAME) {
      LOGGER.debug("Searching persons by using method name query creation.");
      persons = personRepository.findByLastName(searchTerm);
    }
    else if (searchType == SearchType.NAMED_QUERY) {
      LOGGER.debug("Searching persons by using named query");
      persons = personRepository.findByName(searchTerm);
    }
    else {
      LOGGER.debug("Searching persons by using query annotation");
      persons = personRepository.find(searchTerm);
    }

    return persons;
  }

  public void setPersonRepository(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

}
