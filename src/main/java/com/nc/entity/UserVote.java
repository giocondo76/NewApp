package com.nc.entity;

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

    private boolean vote;

    public UserVote(Integer id, User user, boolean vote) {
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

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
