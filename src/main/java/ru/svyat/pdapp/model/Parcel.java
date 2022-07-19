package ru.svyat.pdapp.model;

import lombok.*;
import ru.svyat.pdapp.dto.enums.ParcelStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@Entity
@Builder
@Table(name = "parcels")
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcels_seq")
	@SequenceGenerator(name = "parcels_seq", sequenceName = "parcels_id_seq", allocationSize = 1)
	private Long id;

	@OneToOne
	@JoinColumn(name = "requestor")
	private User requestor;
	@OneToOne
	@JoinColumn(name = "courier")
	private Courier courier;

	private String destination;
	@Column(name = "current_loc")
	private String currentLocation;
	private Long price;
	private ZonedDateTime created;
	private ZonedDateTime updated;

	@Enumerated(EnumType.STRING)
	private ParcelStatus status;
}
