package ru.svyat.pdapp.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svyat.pdapp.dto.enums.UserRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNew {
	private String login;
	private String password;
}
