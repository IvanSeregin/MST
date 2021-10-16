package com.mst.MST.registration.services;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.insurer.services.InsurerServiceInterface;
import com.mst.MST.registration.entities.Registration;
import com.mst.MST.registration.repositories.RegistrationRepository;
import com.mst.MST.vehicle.entities.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    private RegistrationServiceInterface underTest;
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private InsurerServiceInterface insurerService;

    @BeforeEach
    void setUp(){
        underTest = new RegistrationService(registrationRepository, insurerService);
    }

    @Test
    @DisplayName("Test RegistrationServiceTest::getAll() if DB is empty")
    void getAllIfEmpty() {
        //given
        int size = 0;
        //when
        List<Registration> registrations = underTest.getAll();
        //then
        Mockito.verify(registrationRepository).findAll();
        assertThat(registrations.size()).isEqualTo(size);
    }

    @Test
    @DisplayName("Test RegistrationServiceTest::getAll() if registrations are presented in DB")
    void getAllIfRegistrationsPresent() {
        //given
        List<Registration> registrations = getRegistration();
        //when
        when(registrationRepository.findAll()).thenReturn(getRegistration());
        List<Registration> registrationsFromDB = underTest.getAll();
        //then
        Mockito.verify(registrationRepository).findAll();
        assertThat(registrations.size()).isEqualTo(registrationsFromDB.size());
    }

    @Test
    @DisplayName("Test RegistrationServiceTest::getById() if ID is presented in DB")
    void getByIdIfPresent() {
        //given
        Registration registration = getRegistration().get(0);
        Long id = registration.getId();
        //when
        when(registrationRepository.findById(id)).thenReturn(Optional.of(registration));
        Registration registrationFromDB = underTest.getById(id.toString());
        //then
        Mockito.verify(registrationRepository).findById(id);
        assertThat(registrationFromDB.getId()).isEqualTo(registration.getId());
    }

    @Test
    @DisplayName("Test RegistrationServiceTest::getById() if ID is not presented in DB")
    void getByIdIfNotPresent() {
        //given
        Registration registration = getRegistration().get(0);
        Long id = registration.getId();
        //when
        when(registrationRepository.findById(id)).thenReturn(Optional.empty());
        Registration registrationFromDB = underTest.getById(id.toString());
        //then
        Mockito.verify(registrationRepository).findById(id);
        assertThat(registrationFromDB).isEqualTo(null);
    }

    @Test
    @DisplayName("Test RegistrationServiceTest::addRegistration() if it can add new registration to DB and insurer is in the DB")
    void addRegistration() {
        //given
        Registration registration = getRegistration().get(0);
        //when
        when(insurerService.getInsurerByName(registration.getInsurer().getName()))
                .thenReturn(registration.getInsurer());
        Registration registrationFromDB = underTest.addRegistration(registration);
        //then
        Mockito.verify(registrationRepository).save(registration);
        //assertThat()

    }

    @Test
    @Disabled
    void deleteRegistration() {
    }

    @Test
    @Disabled
    void updateRegistration() {
    }

    private List<Registration> getRegistration() {
        List<Registration> list = new LinkedList<>();

        Insurer insurer1 = new Insurer((long) 1, "Allianz", "11");
        Vehicle vehicle1 = new Vehicle((long) 1, "Sedan", "Toyota", "Aurion", "Black");
        Registration registration1 = new Registration((long) 1, "DHU78R", vehicle1, insurer1);

        Insurer insurer2 = new Insurer((long) 2, "AAMI", "22");
        Vehicle vehicle2 = new Vehicle((long) 2, "Suv", "Holden", "Captiva", "Silver");
        Registration registration2 = new Registration((long) 2, "NBS99L", vehicle2, insurer2);

        list.add(registration1);
        list.add(registration2);

        return list;
    }
}