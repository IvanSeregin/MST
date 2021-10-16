package com.mst.MST.insurer.controllers;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.insurer.services.InsurerService;
import com.mst.MST.insurer.services.InsurerServiceInterface;
import com.mst.MST.registration.entities.Registration;
import com.mst.MST.registration.services.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurer")
public class InsurerController {
    private final InsurerServiceInterface insurerService;

    public InsurerController(InsurerServiceInterface insurerService) {
        this.insurerService = insurerService;
    }

    @GetMapping("/getAll")
    public List<Insurer> getAll() {
        return insurerService.getAll();
    }

    @PostMapping("/add")
    public void addInsurer(@RequestBody Insurer insurer) {
        insurerService.addInsurer(insurer);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteInsurer(@PathVariable String name) {
        insurerService.deleteInsurer(name);
    }
}
