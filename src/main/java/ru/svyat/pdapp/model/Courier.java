package ru.svyat.pdapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.model.enums.CourierStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "couriers")
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CourierStatus status;

	@OneToOne
	private User user;
}