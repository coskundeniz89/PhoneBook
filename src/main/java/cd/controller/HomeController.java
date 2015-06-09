package cd.controller;

import cd.Servisim;
import cd.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @Autowired
  PersonRepository personRepository;

  @RequestMapping("/")
  public String loadHomePage(Model m) {

    Servisim servisim = new Servisim();
    servisim.testt();
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