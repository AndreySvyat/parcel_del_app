package ru.svyat.pdapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import ru.svyat.pdapp.dto.enums.UserRole;
import ru.svyat.pdapp.dto.req.UserNew;
import ru.svyat.pdapp.dto.resp.CourierRsp;
import ru.svyat.pdapp.dto.resp.UserRsp;

public interface UserService {
	UserRsp getMe();
	Page<CourierRsp> getCouriers(Pageable pageable);
	UserRsp createNew(UserNew userNew, UserRole role);

	String token(Authentication authentication);
}
