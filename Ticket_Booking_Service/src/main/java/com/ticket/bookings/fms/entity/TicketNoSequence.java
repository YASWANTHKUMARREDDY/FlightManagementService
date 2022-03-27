package com.ticket.bookings.fms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_sequence")
public class TicketNoSequence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "present_sequence")
	private Long presentSequence;

	@Column(name = "ticket_sequence")
	private Boolean ticketSequence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getPresentSequence() {
		return presentSequence;
	}

	public void setPresentSequence(Long presentSequence) {
		this.presentSequence = presentSequence;
	}

	public Boolean getTicketSequence() {
		return ticketSequence;
	}

	public void setTicketSequence(Boolean ticketSequence) {
		this.ticketSequence = ticketSequence;
	}

}
