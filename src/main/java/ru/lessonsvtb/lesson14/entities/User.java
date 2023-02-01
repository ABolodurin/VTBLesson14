package ru.lessonsvtb.lesson14.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_authorities",
    joinColumns = @JoinColumn(name = "username"),
    inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorityList;

    public User(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorityList = authorities;
    }

    public User() {
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorityList;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorityList = authorities;
    }

}
