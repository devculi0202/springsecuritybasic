package com.eazybytes.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;
  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @Column(name = "password", nullable = false)
  private String password;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id")
  private Roles role;
  
  public User(String email, String password, Roles role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
