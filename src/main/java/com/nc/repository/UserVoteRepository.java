package com.nc.repository;

import com.nc.entity.UserVote;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoteRepository  extends JpaRepository<UserVote, Integer> {

    UserVote findByUserId(Integer id);

}
