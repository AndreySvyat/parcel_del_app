package ru.svyat.pdapp.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.dto.enums.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRsp {
	private String login;
	private UserRole role;
}
