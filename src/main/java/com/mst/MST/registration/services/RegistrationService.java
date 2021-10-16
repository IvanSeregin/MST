package com.mst.MST.registration.services;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.insurer.services.InsurerService;
import com.mst.MST.insurer.services.InsurerServiceInterface;
import com.mst.MST.registration.entities.Registration;
import com.mst.MST.registration.repositories.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService implements RegistrationServiceInterface{
    private final RegistrationRepository registrationRepository;
    private final InsurerServiceInterface insurerService;

    public RegistrationService(RegistrationRepository registrationRepository,
                               InsurerServiceInterface insurerService) {
        this.registrationRepository = registrationRepository;
        this.insurerService = insurerService;
    }

    public List<Registration> getAll() {
        List<Registration> registrations = new LinkedList<>();
        registrationRepository.findAll().forEach(registrations::add);
        return registrations;
    }

    public Registration getById(String id) {
        Optional<Registration> registration = registrationRepository.findById(Long.parseLong(id));
        return registration.isPresent() ? registration.get() : null;
    }

    public Registration addRegistration(Registration registration) {
        Insurer insurer = insurerService.getInsurerByName(registration.getInsurer().getName());
        if (insurer == null) {
            insurer = insurerService.addInsurer(registration.getInsurer());
        }
        registration.setInsurer(insurer);
        return registrationRepository.save(registration);
    }


    public Long deleteRegistration(String id) {
        Optional<Registration> registration = registrationRepository.findById(Long.parseLong(id));
        if (registration.isPresent()) {
            registrationRepository.deleteById(registration.get().getId());
            return registration.get().getId();
        }
        return null;
    }

    public Registration updateRegistration(Registration registration) {
        Registration updatedRegistration = getById(registration.getId().toString());
        if (updatedRegistration != null) {
            Insurer insurer = insurerService.getInsurerByName(registration.getInsurer().getName());
            updatedRegistration.setInsurer(insurer);
            updatedRegistration.setVehicle(registration.getVehicle());
            updatedRegistration.setNumberPlate(registration.getNumberPlate());
            return registrationRepository.save(updatedRegistration);
        }
        return null;
    }
}
