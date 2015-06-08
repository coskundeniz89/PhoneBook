package cd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String loadHomePage(Model m) {

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