package com.mst.MST;

import com.mst.MST.insurer.entities.Insurer;
import com.mst.MST.insurer.services.InsurerServiceInterface;
import com.mst.MST.registration.entities.Registration;
import com.mst.MST.registration.services.RegistrationServiceInterface;
import com.mst.MST.vehicle.entities.Vehicle;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MstApplicationTests {
	@Autowired
	private InsurerServiceInterface insurerService;
	@Autowired
	private RegistrationServiceInterface registrationService;
	private final List<Registration> registrations = getRegistration();

	@BeforeEach
	public void dropTablesBeforeEach() {
		dropTables();
	}

	@Test
	@DisplayName("Test RegistrationService::getAll()")
	public void testGetAll(){
		registrationService.addRegistration(registrations.get(0));
		registrationService.addRegistration(registrations.get(1));
		assertEquals(registrations.size(), registrationService.getAll().size());
	}

	@Test
	@DisplayName("Test RegistrationService::getById(String id)")
	public void testGetById(){
		Registration registration = registrationService.addRegistration(registrations.get(0));
		assertEquals(registration.getId(), registrationService.getById(registration.getId().toString()).getId());
	}

	@Test
	@DisplayName("Test RegistrationService::addRegistration(Registration registration)")
	public void testAddRegistration(){
		Registration registration = registrationService.addRegistration(registrations.get(0));
		assertEquals(registration.getId(), registrationService.getById(registration.getId().toString()).getId());
	}

	@Test
	@DisplayName("Test RegistrationService::deleteRegistration(String id)")
	public void testDeleteRegistration(){
		Registration registration = registrationService.addRegistration(registrations.get(0));
		assertEquals(registration.getId(), registrationService.deleteRegistration(registration.getId().toString()));
	}

	@Test
	@DisplayName("Test RegistrationService::updateRegistration(Registration registration)")
	public void testUpdateRegistration(){
		Registration registration = registrationService.addRegistration(registrations.get(0));
		registration.setNumberPlate("TEST01");
		assertEquals("TEST01", registrationService.updateRegistration(registration).getNumberPlate());
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

	public void dropTables() {
		for (Registration registration : registrationService.getAll()) {
			registrationService.deleteRegistration(registration.getId().toString());
		}
		for (Insurer insurer : insurerService.getAll()) {
			insurerService.deleteInsurer(insurer.getId().toString());
		}
	}
}
