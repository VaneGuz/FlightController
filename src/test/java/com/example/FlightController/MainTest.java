package com.example.FlightController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
	Añadir vuelos
	Buscar vuelos por referencia
	Buscar vuelos por origen
	Buscar vuelos entre dos fechas
	Eliminar vuelos

--Duplicidad de código
*/

@SpringBootTest
class MainTest {

	private FlightController flightController;

	@BeforeEach
	void contextLoads() {
		flightController = new FlightController();
	}

	@Test
	public void givenEmptyFlightController_whenFlightAdded_thenGetByReferenceReturnsFlight(){
		LocalDate date = LocalDate.of(2021,01,01);
		LocalTime time = LocalTime.of(10,20);
		Flight flight = new Flight("1", LocalDateTime.of(date,time), "Madrid", "Barcelona");

		flightController.addFlight(flight);
		assertEquals(flight, flightController.findFlightByReference("1"));
	}

	@Test
	public void givenEmptyFlightController_whenGetByReference_thenThrowFlightNotFoundException(){
		assertThrows(FlightNotFoundException.class, () ->{flightController.findFlightByReference("1");
		});
	}

	@Test
	public void givenFlightControllerWithFlight_WhenSameFlightAdded_ThenThrowDuplicateFlightException(){
		LocalDate date = LocalDate.of(2021,01,01);
		LocalTime time = LocalTime.of(10,20);
		Flight flight = new Flight("1", LocalDateTime.of(date,time), "Madrid", "Barcelona");

		flightController.addFlight(flight);
		assertThrows(DuplicateFlightException.class, () ->{flightController.addFlight(flight);
		});
	}

	@Test
	public void givenEmptyFlightController_whenFlightAdded_thenGetByOriginReturnsFlight(){
		LocalDate date = LocalDate.of(2021,01,01);
		LocalTime time = LocalTime.of(10,20);
		Flight flight = new Flight("1", LocalDateTime.of(date,time), "Madrid", "Barcelona");

		LocalDate date2 = LocalDate.of(2021,01,01);
		LocalTime time2 = LocalTime.of(10,20);
		Flight flight2 = new Flight("2", LocalDateTime.of(date2,time2), "Paris", "Barcelona");

		LocalDate date3 = LocalDate.of(2021,01,01);
		LocalTime time3 = LocalTime.of(10,20);
		Flight flight3 = new Flight("3", LocalDateTime.of(date3,time3), "Polonia", "Suiza");

		LocalDate date4 = LocalDate.of(2021,01,01);
		LocalTime time4 = LocalTime.of(10,20);
		Flight flight4 = new Flight("4", LocalDateTime.of(date4,time4), "Paris", "Italia");


		flightController.addFlight(flight);
		flightController.addFlight(flight2);
		flightController.addFlight(flight3);
		flightController.addFlight(flight4);

		List<Flight> actual = flightController.findFlightByOrigin("Paris");
		List<Flight> expected = new ArrayList<>();

		expected.add(flight2);
		expected.add(flight4);

		assertEquals(actual,expected);
	}


	@Test
	public void  givenFlightControllerWithFlight_whenDeleteFlight_thenGetReferenceReturnsFlightNotFoundException(){
		LocalDate date = LocalDate.of(2021,01,01);
		LocalTime time = LocalTime.of(10,20);
		Flight flight = new Flight("1", LocalDateTime.of(date,time), "Madrid", "Barcelona");

		flightController.addFlight(flight);
		flightController.deleteFlight(flight.getReference());

		assertThrows(FlightNotFoundException.class,() -> flightController.findFlightByReference(flight.getReference()) );
	}

}
