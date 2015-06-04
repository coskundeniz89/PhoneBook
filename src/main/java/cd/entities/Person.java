package cd.entities;


import javax.persistence.*;

@Entity
@Table(name = "PEOPLE")
public final class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "SURNAME")
  private String surname;

  public Person(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }
}