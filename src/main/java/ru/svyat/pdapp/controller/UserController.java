package ru.svyat.pdapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import ru.svyat.pdapp.dto.enums.UserRole;
import ru.svyat.pdapp.dto.req.UserNew;
import ru.svyat.pdapp.dto.resp.CourierRsp;
import ru.svyat.pdapp.dto.resp.UserRsp;
import ru.svyat.pdapp.service.UserService;

import static ru.svyat.pdapp.utils.SecurityUtils.hasScope;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public UserRsp get(){
		return userService.getMe();
	}

	@GetMapping("/couriers")
	public Page<CourierRsp> getCouriers(Pageable pageable){
		return userService.getCouriers(pageable);
	}

	@PostMapping("/courier")
	public UserRsp createCourier(@RequestBody UserNew courier){
		if(!hasScope("ADMIN"))
			throw new AccessDeniedException("Current user is not Admin");
		return userService.createNew(courier, UserRole.COURIER);
	}
}
