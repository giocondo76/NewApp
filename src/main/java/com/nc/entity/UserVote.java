package com.nc.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity
@Table(name="usr_vote")
public class UserVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_vote_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usr")
    private User user;

    private Boolean vote;

    public UserVote(Integer id, User user, Boolean vote) {
        this.id = id;
        this.user = user;
        this.vote = vote;
    }

    public UserVote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
