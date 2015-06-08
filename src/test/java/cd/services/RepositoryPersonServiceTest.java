package cd.services;

import cd.dto.SearchDTO;
import cd.enm.SearchType;
import cd.entities.Person;
import cd.interfaces.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;

import static org.mockito.Mockito.*;

/**
 * Created by coskun.deniz on 08.06.2015.
 */
public class RepositoryPersonServiceTest {
  private static final String LAST_NAME = "deniz1";

  private RepositoryPersonService personService;

  private PersonRepository personRepositoryMock;

  @Before
  public void setUp() {
    personService = new RepositoryPersonService();

    personRepositoryMock = mock(PersonRepository.class);
    personService.setPersonRepository(personRepositoryMock);
  }

  @Test
  public void searchWhenSearchTypeIsMethodName() {
    SearchDTO searchCriteria = createSearchDTO(LAST_NAME, SearchType.METHOD_NAME);
    List<Person> expected = new ArrayList<Person>();
    when(personRepositoryMock.findByLastName(searchCriteria.getSearchTerm())).thenReturn(expected);

    List<Person> actual = personService.search(searchCriteria);

    verify(personRepositoryMock, times(1)).findByLastName(searchCriteria.getSearchTerm());
    verifyNoMoreInteractions(personRepositoryMock);

    assertEquals(expected, actual);
  }

  @Test
  public void searchWhenSearchTypeIsNamedQuery() {
    SearchDTO searchCriteria = createSearchDTO(LAST_NAME, SearchType.NAMED_QUERY);
    List<Person> expected = new ArrayList<Person>();
    when(personRepositoryMock.findByName(searchCriteria.getSearchTerm())).thenReturn(expected);

    List<Person> actual = personService.search(searchCriteria);

    verify(personRepositoryMock, times(1)).findByName(searchCriteria.getSearchTerm());
    verifyNoMoreInteractions(personRepositoryMock);

    assertEquals(expected, actual);
  }

  @Test
  public void searchWhenSearchTypeIsQueryAnnotation() {
    SearchDTO searchCriteria = createSearchDTO(LAST_NAME, SearchType.QUERY_ANNOTATION);
    List<Person> expected = new ArrayList<Person>();
    when(personRepositoryMock.find(searchCriteria.getSearchTerm())).thenReturn(expected);

    List<Person> actual = personService.search(searchCriteria);

    verify(personRepositoryMock, times(1)).find(searchCriteria.getSearchTerm());
    verifyNoMoreInteractions(personRepositoryMock);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void searchWhenSearchTypeIsNull() {
    SearchDTO searchCriteria = createSearchDTO(LAST_NAME, null);

    personService.search(searchCriteria);

    verifyZeroInteractions(personRepositoryMock);
  }

  private SearchDTO createSearchDTO(String searchTerm, SearchType searchType) {
    SearchDTO searchCriteria = new SearchDTO();
    searchCriteria.setSearchTerm(searchTerm);
    searchCriteria.setSearchType(searchType);
    return searchCriteria;
  }
}
