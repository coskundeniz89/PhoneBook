package cd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "employee103")
@Inheritance(strategy= InheritanceType.JOINED)

public class Employee {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

//setters and getters
}
