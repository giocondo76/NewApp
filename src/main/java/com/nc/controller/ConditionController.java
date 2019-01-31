package com.nc.controller;

import com.nc.entity.*;
import com.nc.repository.*;
import com.nc.service.UserService;
import com.nc.service.UserVoteService;
import com.nc.service.UserVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ConditionController {

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    private Location location;

   @Autowired
   private LocationRepository locationRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private StandartRepository standartRepository;

   @Autowired
   private ChangeRepository changeRepository;

   @Autowired
   private UserVoteRepository userVoteRepository;

   @Autowired
   private UserVoteService userVoteService;

    @GetMapping("/condition/{id}")
    public String condition(Map<String, Object> model, @PathVariable Integer id) {

        Location location = locationRepository.findById(id);
        model.put("location", location);
        LocationFlag locationFlag = createNewFlag(location);
        model.put("locationFlag",locationFlag);

        List<Condition> conditions = conditionRepository.findByLocationId(id);
        List<ConditionStatus> conditionStatuses = getListOfConditionStatuses(conditions, location);
        model.put("conditionStatuses", conditionStatuses);
        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
        model.put("suggestions", suggestions);
        model.put("suggestion", new Suggestion());

        User user = userService.getCurrentUser();
        model.put("user", user);


        Change change = changeRepository.findByLocationId(id);
        model.put("change", change);

        UserVote userVote = userVoteRepository.findByUserId(user.getId());

        if(userVote == null){

            model.put("userVote", new UserVote());


        }else if(userVote.getVote() != null && user.getLocation().getId().equals(location.getId()))
        {
            model.put("userVote", userVote);
            Integer result = userVoteService.showVotingResult(location);
            model.put("votingResult", result);
        }
        return "condition";
    }


    @PostMapping("/condition/{id}")
    public String addSuggestionAndVote(@Valid Suggestion suggestion, @PathVariable Integer id , Map<String, Object> model,
                                BindingResult result,@Valid UserVote userVote ) {
        if (result.hasErrors()) {
            return "condition/{id}";
        }

        Location location = locationRepository.findById(id);
        LocationFlag locationFlag = createNewFlag(location);
        model.put("locationFlag",locationFlag);
        model.put("location", location);

        List<Condition> conditions = conditionRepository.findByLocationId(id);
        List<ConditionStatus> conditionStatuses = getListOfConditionStatuses(conditions, location);
        model.put("conditionStatuses", conditionStatuses);

        if(suggestion.getText() != null)
        {
            suggestion.setLocation(locationRepository.findById(id));
            suggestionRepository.save(suggestion);
            model.put("suggestion", new Suggestion());
        }

        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
        model.put("suggestions", suggestions);

        User user = userService.getCurrentUser();
        model.put("user", user);

        Change change = changeRepository.findByLocationId(id);
        model.put("change", change);


        Integer votingResult;
        if(userVote.getVote() != null){
                userVote.setUser(user);
                userVoteRepository.save(userVote);
                votingResult = userVoteService.showVotingResult(location);
                model.put("userVote", userVote);
                model.put("votingResult", votingResult);
            }
        else
        {
            userVote = userVoteRepository.findByUserId(user.getId());
            model.put("userVote",userVote);

        }
        return "condition";
    }

    public LocationFlag createNewFlag(Location location){
        List<String> flags = new ArrayList<>();
        flags.add("Room manager");
        flags.add("User");
        LocationFlag locationFlag;
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
            locationFlag = new LocationFlag(location,flags.get(0));
        }
        else {
            locationFlag = new LocationFlag(location, flags.get(1));
        }
        return  locationFlag;
    }

    public List<ConditionStatus> getListOfConditionStatuses(List<Condition> conditions, Location location) {

        List<ConditionStatus> conditionStatuses = new ArrayList<>();
        for (Condition condition: conditions) {
            if (condition.getTemp() < location.getStandart().getTemp_min() ||
                    condition.getTemp() > location.getStandart().getTemp_max()) {

                conditionStatuses.add(new ConditionStatus(condition, "Temperature out of range"));
            } else if (condition.getHum() > location.getStandart().getHum_max()) {

                conditionStatuses.add(new ConditionStatus(condition, "Humidity out of range"));

            } else if (condition.getCo2() < location.getStandart().getCo2_min() ||
                    condition.getCo2() > location.getStandart().getCo2_max()) {
                conditionStatuses.add(new ConditionStatus(condition, "CO2 out of range"));
            } else if (condition.getTemp().equals(location.getStandart().getTemp_min())) {
                conditionStatuses.add(new ConditionStatus(condition, "Temperature has reached the lower limit"));
            } else if (condition.getTemp().equals(location.getStandart().getTemp_max())) {
                conditionStatuses.add(new ConditionStatus(condition, "Temperature has reached the upper limit"));
            } else if (condition.getHum().equals(location.getStandart().getHum_max())) {
                conditionStatuses.add(new ConditionStatus(condition, "Humidity has reached the upper limit"));
            } else if (condition.getCo2().equals(location.getStandart().getCo2_min())) {
                conditionStatuses.add(new ConditionStatus(condition, "CO2 has reached the lower limit"));
            } else if (condition.getCo2().equals(location.getStandart().getCo2_max())) {
                conditionStatuses.add(new ConditionStatus(condition, "CO2 has reached the upper limit"));
            } else
                conditionStatuses.add(new ConditionStatus(condition, "Indicators are normal"));

        }
        return  conditionStatuses;
    }

}
