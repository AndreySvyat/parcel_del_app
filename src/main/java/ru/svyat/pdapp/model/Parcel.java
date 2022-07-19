package ru.svyat.pdapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.model.enums.ParcelStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parcels")
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private User requestor;
	@OneToOne
	private User recipient;
	@OneToOne
	private Courier courier;

	private String destination;
	private String current_loc;
	private Long price;

	@Enumerated(EnumType.STRING)
	private ParcelStatus status;
}
