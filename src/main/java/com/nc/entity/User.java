package com.nc.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_id_sequence",
            sequenceName="user_id_sequence", initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Integer id;
    @Column(name="name")
    private String username;

    @Column(name="email")
    private String email;


    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_usr",
            joinColumns = {@JoinColumn(name = "usr_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "user")
    private List<Location> locations;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<UserVote> userVotes;

    public User() {
    }

    public User(Integer id, String username, String email, String password, Location location,
                Set<Role> roles, List<Location> locations, List<UserVote> userVotes) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.location = location;
        this.roles = roles;
        this.locations = locations;
        this.userVotes = userVotes;
    }

    public List<UserVote> getUserVotes() {
        return userVotes;
    }

    public void setUserVotes(List<UserVote> userVotes) {
        this.userVotes = userVotes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

}