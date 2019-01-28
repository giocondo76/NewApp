package com.nc.controller;

import com.nc.entity.Condition;
import com.nc.entity.Location;
import com.nc.entity.LocationFlag;
import com.nc.entity.Suggestion;
import com.nc.repository.ConditionRepository;
import com.nc.repository.LocationRepository;
import com.nc.repository.SuggestionRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ConditionController {

    @Autowired
    private ConditionRepository conditionRepository;


    @Autowired
    private SuggestionRepository suggestionRepository;

    private Location location;

//    @Autowired
//    private ChangeRepository changeRepository;

   @Autowired
   private LocationRepository locationRepository;

   @Autowired
   private UserService userService;

    @RequestMapping(value = "/condition/{id}", method = RequestMethod.GET)
    public String condition(Map<String, Object> model, @PathVariable Integer id) {

        Location location = locationRepository.findById(id);
        LocationFlag locationFlag;
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
             locationFlag = new LocationFlag(locationRepository.findById(id),"Admin");
        }
        else {
            locationFlag = new LocationFlag(locationRepository.findById(id), "User");
        }
        model.put("locationFlag",locationFlag);
        List<Condition> conditions = conditionRepository.findByLocationId(id);
        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
        model.put("suggestions", suggestions);
        model.put("suggestion", new Suggestion());
        model.put("conditions", conditions);
        return "condition";
    }


    @PostMapping(value = "/condition/{id}")
    public String addSuggestion(@Valid Suggestion suggestion, @PathVariable Integer id , Map<String, Object> model, BindingResult result) {

        if (result.hasErrors()) {
            return "condition/{id}";
        }
        suggestion.setLocation(locationRepository.findById(id));
        suggestionRepository.save(suggestion);
        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
        List<Condition> conditions = conditionRepository.findByLocationId(id);
        model.put("suggestion", new Suggestion());
        model.put("suggestions", suggestions);
        model.put("conditions", conditions);
        return "condition";
    }

//    @RequestMapping(value = "/condition/{id}", method = RequestMethod.GET)
//    public String suggestion(Map<String, Object> model, @PathVariable Integer id) {
//
//        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
//        model.put("suggestions", suggestions);
//        return "condition";
//    }

}
