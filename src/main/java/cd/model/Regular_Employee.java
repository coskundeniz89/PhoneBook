package cd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * PhoneBook
 * Created by coskun.deniz on 23.11.2015.
 */

@Entity
@Table(name="regularemployee103")
@PrimaryKeyJoinColumn(name="ID")
public class Regular_Employee extends Employee{

  @Column(name="salary")
  private float salary;

  @Column(name="bonus")
  private int bonus;

//setters and getters
}
