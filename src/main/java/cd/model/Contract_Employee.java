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
@Table(name="contractemployee103")
@PrimaryKeyJoinColumn(name="ID")
public class Contract_Employee extends Employee{

  @Column(name="pay_per_hour")
  private float pay_per_hour;

  @Column(name="contract_duration")
  private String contract_duration;

  //setters and getters
}