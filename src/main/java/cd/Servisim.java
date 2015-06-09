package cd;

import cd.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by coskun.deniz on 09.06.2015.
 */
@Service
public class Servisim {
  @Autowired
  PersonRepository personRepository;

  public void testt(){
    personRepository.find("");
  }
}
