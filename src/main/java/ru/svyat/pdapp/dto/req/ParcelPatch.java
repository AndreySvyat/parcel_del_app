package ru.svyat.pdapp.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.dto.enums.ParcelStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelPatch {
	private ParcelStatus status;
	private String destination;
	private String currentLocation;
}
