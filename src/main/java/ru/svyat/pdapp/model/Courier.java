package ru.svyat.pdapp.model;

import lombok.*;
import ru.svyat.pdapp.dto.enums.CourierStatus;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@Table(name = "couriers")
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "couriers_seq")
	@SequenceGenerator(name = "couriers_seq", sequenceName = "couriers_id_seq", allocationSize = 1)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CourierStatus status;

	@OneToOne
	@JoinColumn(name = "user")
	private User user;
}