package com.ticket.bookings.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ticket.bookings.fms.VO.TicketBookingData;
import com.ticket.bookings.fms.VO.TicketResponse;
import com.ticket.bookings.fms.entity.TicketDetails;
import com.ticket.bookings.fms.entity.TicketNoSequence;
import com.ticket.bookings.fms.repository.TicketSequenceRepository;

@Component
public class TicketMapper {

	@Autowired
	TicketSequenceRepository ticketSequenceRepository;

	public List<TicketDetails> convertToEntity(TicketBookingData input) {

		List<TicketDetails> listOfTickets = new ArrayList<TicketDetails>();
		input.getUsers().forEach(u -> {
			TicketDetails entity = new TicketDetails();
			entity.setUserId(input.getUserId());
			entity.setFirstName(u.getFirstName());
			entity.setLastName(u.getLastName());
			entity.setAge(u.getAge());
			entity.setGender(u.getGender());
			entity.setFlightNo(input.getFlightNo());
			entity.setTicketNo(ticketNoGeneration(input.getFlightNo()));
			entity.setJourneyDate(input.getJourneyDate());
			entity.setCreatedDate(LocalDate.now());
			entity.setPaidAmount(u.getTicketPrice());
			entity.setTicketStatus(TicketStatusEnum.BOOKED.getName());
			listOfTickets.add(entity);
		});

		return listOfTickets;
	}

	public String ticketNoGeneration(String flightNo) {
		StringBuilder sb = new StringBuilder();
		Optional<TicketNoSequence> sequenceEntity = ticketSequenceRepository.findByTicketSequence(Boolean.TRUE);
		if (sequenceEntity.isPresent()) {
			sequenceEntity.get().setId(sequenceEntity.get().getId());
			Long currentSequence = sequenceEntity.get().getPresentSequence();
			Long ticketSeq = ++currentSequence;
			sb.append(flightNo.substring(0, 3).toUpperCase()).append(String.valueOf(ticketSeq)).append(String.valueOf(LocalDate.now().getYear()));
			sequenceEntity.get().setPresentSequence(ticketSeq);
			ticketSequenceRepository.save(sequenceEntity.get());
			return sb.toString();
		} else {
			TicketNoSequence newSequence = new TicketNoSequence();
			newSequence.setPresentSequence(1L);
			newSequence.setTicketSequence(Boolean.TRUE);
			sb.append(flightNo.substring(0, 3)).append(String.valueOf(1L)).append(String.valueOf(LocalDate.now().getYear()));
			ticketSequenceRepository.save(newSequence);
			return sb.toString();
		}

	}

	public TicketResponse convertToResponse(TicketDetails entity) {
		TicketResponse response = new TicketResponse();
		response.setTicketNo(entity.getTicketNo());
		response.setFlightNo(entity.getFlightNo());
		response.setName(entity.getFirstName()+entity.getLastName());
		response.setAge(entity.getAge());
		response.setGender(entity.getGender());
		response.setJourneyDate(entity.getJourneyDate());
		response.setTicketStatus(entity.getTicketStatus());
		response.setTicketPrice(entity.getPaidAmount());
		return response;
	}
}
