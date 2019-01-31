package com.nc.controller;

import com.nc.entity.DevType;
import com.nc.entity.Device;
import com.nc.entity.Location;
import com.nc.repository.DevTypeRepository;
import com.nc.repository.DeviceRepository;
import com.nc.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DevTypeRepository devTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/device/all")
    public String showAllDevices(Map<String, Object> model){

        List<Device> devices = deviceRepository.findAll();
        List<DevType> devTypes= devTypeRepository.findAll();
        model.put("devices", devices);
        model.put("devTypes", devTypes);

        return "device/all";
    }


    @GetMapping("/device/add")
    public String addDevice(Map<String, Object>  model) {

        model.put("device", new Device());
        model.put("devTypes", devTypeRepository.findAll());
        model.put("locations", locationRepository.findAll());
        return "device/add";
    }


    @PostMapping("/device/add")
    public String addDevice(@Valid Device device, BindingResult result) {
        if (result.hasErrors()) {
            return "device/add";
        }
        deviceRepository.save(device);

        return "redirect:/device/all";
    }
}
