package ru.svyat.pdapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.svyat.pdapp.dto.enums.UserRole;
import ru.svyat.pdapp.dto.req.UserNew;
import ru.svyat.pdapp.dto.resp.UserRsp;
import ru.svyat.pdapp.service.UserService;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final UserService userService;

	@PostMapping("/token")
	public String token(Authentication authentication) {
		return userService.token(authentication);
	}

	@PostMapping("/signin")
	public UserRsp post(@RequestBody UserNew newUser){
		return userService.createNew(newUser, UserRole.USER);
	}

	@PostMapping("/login")
	public String login(Authentication authentication){
		return userService.token(authentication);
	}
}
