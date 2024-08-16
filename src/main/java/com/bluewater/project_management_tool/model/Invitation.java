package com.bluewater.project_management_tool.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invitations")
@Data
@NoArgsConstructor
public class Invitation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Lob
	private String message;
	
	private String subject;
	private String email;
	
	private LocalDateTime invitedAt;
	private boolean isAccepted;
	
	public Invitation(String message, String subject, String email) {
		this(email);
		this.message = message;
		this.subject = subject;
	}
	
	public Invitation(String email) {
		this.email = email;
	}
	
	

}
