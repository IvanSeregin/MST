package com.mst.MST.registration.controllers;

import com.mst.MST.registration.entities.Registration;
import com.mst.MST.registration.services.RegistrationService;
import com.mst.MST.registration.services.RegistrationServiceInterface;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationServiceInterface registrationService;

    public RegistrationController(RegistrationServiceInterface registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/getAll")
    public List<Registration> getAll() {
        return registrationService.getAll();
    }

    @GetMapping("/get/{id}")
    public Registration getById(@PathVariable String id) {
        return registrationService.getById(id);
    }

    @PostMapping("/add")
    public void addRegistration(@RequestBody Registration registration) {
        registrationService.addRegistration(registration);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRegistration(@PathVariable String[] id){
        for (int i = 0; i < id.length; i++) {
            registrationService.deleteRegistration(id[i]);
        }
    }

    @PutMapping("/update")
    public void updateRegistration(@RequestBody Registration registration){
        registrationService.updateRegistration(registration);
    }
}
