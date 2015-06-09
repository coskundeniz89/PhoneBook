package cd.controller;

import cd.dto.SearchDTO;
import cd.dto.SearchType;
import cd.model.Person;
import cd.services.api.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  private IPersonService repositoryPersonService;

//  @Autowired
//  private PersonRepository personRepository;

  @RequestMapping("/")
  public String loadHomePage(Model m) {

    SearchDTO searchDTO = new SearchDTO();
    searchDTO.setSearchTerm("deniz");
    searchDTO.setSearchType(SearchType.NAMED_QUERY);

    List<Person> persons = repositoryPersonService.search(searchDTO);

//    Servisim servisim = new Servisim();
    //repositoryPersonService.testt(1, 1, "");
    //List<Person> persons = personRepository.find("deniz");


//    List<Person> result = new ArrayList<Person>();
//
//    result.add(new Person("coşkun", "deniz"));
//    result.add(new Person("hüseyin", "bacanak"));
//    result.add(new Person("serin", "must"));
//
//
//    m.addAttribute("contacts", result);
    return "hello";
  }
}