package com.nc.service;

import com.nc.entity.Location;
import com.nc.entity.User;
import com.nc.entity.UserVote;
import com.nc.repository.UserRepository;
import com.nc.repository.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserVoteServiceImpl implements UserVoteService {


    @Autowired
    private UserVoteRepository userVoteRepository;

    @Override
    public Integer showVotingResult(Location location) {
        Integer countVotesYes = 0;
        Integer countAllVotes = 0;
        List<UserVote> userVotes = userVoteRepository.findAll();
        for (UserVote userVote: userVotes) {
            if(userVote.getUser().getLocation().getId().equals(location.getId()))
            {
                countAllVotes++;
            }
            if (userVote.getVote()){
                countVotesYes++;
            }

        }
        return (countVotesYes*100)/countAllVotes;
    }





}
