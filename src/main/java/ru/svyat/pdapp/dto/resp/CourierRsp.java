package ru.svyat.pdapp.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.dto.enums.CourierStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourierRsp {
	private UserRsp userData;
	private CourierStatus status;
}
