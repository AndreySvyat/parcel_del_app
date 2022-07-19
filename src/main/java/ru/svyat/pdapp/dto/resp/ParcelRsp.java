package ru.svyat.pdapp.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.dto.enums.ParcelStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelRsp {
	private Long id;
	private ParcelStatus status;
	private String destination;
	private String currentLoc;
	private String requestor;
	private String courier;
	private Long price;
}