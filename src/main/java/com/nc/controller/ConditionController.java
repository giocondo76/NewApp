package com.nc.controller;

import com.nc.entity.Condition;
import com.nc.entity.Location;
import com.nc.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class ConditionController {

    @Autowired
    private ConditionRepository conditionRepository;


    private Location location;

//    @Autowired
//    private ChangeRepository changeRepository;


    @RequestMapping(value = "/condition/{id}", method = RequestMethod.GET)
    public String condition(Map<String, Object> model, @PathVariable Integer id) {

        List<Condition> conditions = conditionRepository.findByLocationId(id);
        model.put("conditions", conditions);
        return "condition";
    }
}
