package com.skillovilla.booksvillaa.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer SessionId;
	private Integer CustomerId;
	private String uniqueId;
	private LocalTime time;
	
}
