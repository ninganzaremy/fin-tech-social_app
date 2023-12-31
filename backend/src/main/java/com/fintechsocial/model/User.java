package com.fintechsocial.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;
  private String role; // Example role: USER, ADMIN, EDITOR
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastLogin;


  // Default constructor
  public User() {
    this.dateCreated = new Date(); // Sets the date of creation to the current date/time.

  }

  // Parameterized constructor
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    setPassword(password); //hash the password before saving.
    this.dateCreated = new Date();

  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(password);
  }

  public boolean verifyPassword(String inputPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(inputPassword, this.password);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(username, user.username) &&
        Objects.equals(email, user.email) &&
        Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", role='" + role + '\'' +
        ", dateCreated=" + dateCreated +
        ", lastLogin=" + lastLogin +
        ", password='" + "*****" + '\'' +
        '}';
  }
}