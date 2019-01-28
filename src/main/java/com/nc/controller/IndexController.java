package com.nc.controller;

import com.nc.entity.Location;
import com.nc.entity.LocationFlag;
import com.nc.repository.LocationRepository;
import com.nc.repository.RoleRepository;
import com.nc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;


    @GetMapping(value={"/","/index"})
    public String index(Map<String, Object> model){

        List<LocationFlag> locationFlags = new ArrayList<>();
        List<Location> locations = locationRepository.findAll();
        for(Location location:locations){
            if(location.getUser().getId().equals(userService.getCurrentUser().getId())){
                  locationFlags.add(new LocationFlag(location,"Admin"));
            }
            else
                locationFlags.add(new LocationFlag(location,"User"));
        }
        model.put("locations", locations);
        model.put("locationFlags",locationFlags);
        return "index";

    }

//    @PostMapping(value={"/","/index"})
//    public String searchLocationById(
//            @Valid @RequestBody SearchCriteria search, Errors errors) {
//
//        AjaxResponseBody result = new AjaxResponseBody();
//
//        //If error, just return a 400 bad request, along with the error message
//        if (errors.hasErrors()) {
//
//            result.setMsg(errors.getAllErrors()
//                    .stream().map(x -> x.getDefaultMessage())
//                    .collect(Collectors.joining(",")));
//
//            return ResponseEntity.badRequest().body(result);
//
//        }
//
//        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
//        if (users.isEmpty()) {
//            result.setMsg("no user found!");
//        } else {
//            result.setMsg("success");
//        }
//        result.setResult(users);
//
//        return ResponseEntity.ok(result);
//
//    }
//
//    @GetMapping(value={"/index"})
//    public String searchLocationById(@RequestParam Integer search, Map<String, Object> model) {
//        Iterable<Location> locations;
//
//        if (search != null) {
//            locations = Collections.singleton(locationRepository.findById(search));
//        } else {
//            locations = locationRepository.findAll();
//
//        }
//        model.put("locations", locations);
//        return "index";
//    }

//    @GetMapping(value={"/","/index"})
//    public String showStudentBySurname(@RequestParam (value = "surname", required = false) String surname, Model model) {
//        model.addAttribute("search", studentService.listStudentsBySurname(surname));
//        return "students";
//    }
}
