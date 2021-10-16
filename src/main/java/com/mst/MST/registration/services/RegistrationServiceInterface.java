package com.mst.MST.registration.services;

import com.mst.MST.registration.entities.Registration;

import java.util.List;

public interface RegistrationServiceInterface {
    List<Registration> getAll();
    Registration getById(String id);
    Registration addRegistration(Registration registration);
    Long deleteRegistration(String id);
    Registration updateRegistration(Registration registration);
}
