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
public class ParcelShortRsp {
	private Long id;
	private ParcelStatus status;
	private String courier;
	private String destination;
}
