package com.nc.service;

import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.entity.UserVote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserVoteService {

    Integer showVotingResult(Location location);

}
