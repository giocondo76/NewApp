package com.nc.controller;

import com.nc.entity.*;
import com.nc.repository.ConditionRepository;
import com.nc.repository.LocationRepository;
import com.nc.repository.StandartRepository;
import com.nc.repository.SuggestionRepository;
import com.nc.service.UserService;
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

//    @Autowired
//    private ChangeRepository changeRepository;

   @Autowired
   private LocationRepository locationRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private StandartRepository standartRepository;

    @RequestMapping(value = "/condition/{id}", method = RequestMethod.GET)
    public String condition(Map<String, Object> model, @PathVariable Integer id) {

        Location location = locationRepository.findById(id);
        model.put("location", location);
        LocationFlag locationFlag;
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
             locationFlag = new LocationFlag(locationRepository.findById(id),"Admin");
        }
        else {
            locationFlag = new LocationFlag(locationRepository.findById(id), "User");
        }
        model.put("locationFlag",locationFlag);

        List<Condition> conditions = conditionRepository.findByLocationId(id);
        List<ConditionStatus> conditionStatuses = getListOfConditionStatuses(conditions, location);
        model.put("conditionStatuses", conditionStatuses);
        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);
        model.put("suggestions", suggestions);
        model.put("suggestion", new Suggestion());

        User user = userService.getCurrentUser();
        model.put("user", user);

        if(location.getChanges() != null){
            model.put("change", location.getChanges());
        }
        return "condition";
    }


    @PostMapping(value = "/condition/{id}")
    public String addSuggestion(@Valid Suggestion suggestion, @PathVariable Integer id , Map<String, Object> model, BindingResult result) {

        if (result.hasErrors()) {
            return "condition/{id}";
        }

//        Standart standart = location.getStandart();
//        model.put("standart", standart);
        Location location = locationRepository.findById(id);
        LocationFlag locationFlag;
        if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
            locationFlag = new LocationFlag(locationRepository.findById(id),"Admin");
        }
        else {
            locationFlag = new LocationFlag(locationRepository.findById(id), "User");
        }
        model.put("locationFlag",locationFlag);
        suggestion.setLocation(locationRepository.findById(id));
        suggestionRepository.save(suggestion);
        List<Suggestion> suggestions = suggestionRepository.findByLocationId(id);

        List<Condition> conditions = conditionRepository.findByLocationId(id);
        List<ConditionStatus> conditionStatuses = getListOfConditionStatuses(conditions, location);

        model.put("conditionStatuses", conditionStatuses);

        model.put("suggestion", new Suggestion());
        model.put("suggestions", suggestions);
        return "condition";
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
