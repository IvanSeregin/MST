package com.mst.MST.registration.repositories;


import com.mst.MST.registration.entities.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {
}
