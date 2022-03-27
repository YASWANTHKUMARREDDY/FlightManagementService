package com.flight.service.fms.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.flight.service.fms.entilty.FlightDetails;

public interface FlightRepository extends CrudRepository<FlightDetails, Serializable> {

	Optional<FlightDetails> findByFlightNoAndJourneyDate(String flightNo, LocalDate journy);

	List<FlightDetails> findByFromRouteAndToRouteAndFlightStatus(String fromRoute, String toRoute, String flightStatus);
}
