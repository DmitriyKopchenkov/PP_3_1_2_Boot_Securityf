package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

//Часть кода взята с https://www.codejava.net/frameworks/spring-boot/spring-security-add-roles-to-user-examples
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    //заменил строку index на email, так и понятней и больше примеров с email

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "password")
    private String password;

    //так же эта часть от

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //до этой взята с //Часть кода взята с https://www.codejava.net/frameworks/spring-boot/spring-security-add-roles-to-user-examples

    public User() {
    }

    public User(String name, String lastName, int age, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public long getId() {

        return id;
    }
    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getLastName() {

        return lastName;
    }
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int getAge() {

        return age;
    }
    public void setAge(int age) {

        this.age = age;
    }
    //заменил строку index на email, так и понятней и больше примеров с email
    public String getEmail() {

        return email;
    }

    //заменил строку index на email, так и понятней и больше примеров с email
    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return getEmail();
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Set<Role> getRoles() {

        return roles;
    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword(), user.getRoles());
    }
}
