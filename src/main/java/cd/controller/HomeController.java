package cd.controller;

import cd.entities.Person;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
  @RequestMapping("/")
  public String loadHomePage(Model m) {

    List<Person> result = new ArrayList<Person>();

    result.add(new Person("coşkun", "deniz"));
    result.add(new Person("hüseyin", "bacanak"));
    result.add(new Person("serin", "must"));


    m.addAttribute("contacts", result);
    return "hello";
  }
}