package com.nc.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @SequenceGenerator(name = "role_id_sequence_gen",
            sequenceName="role_id_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence_gen")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(unique = true)
    private String authority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}