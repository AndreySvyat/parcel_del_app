package ru.svyat.pdapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svyat.pdapp.dto.enums.UserRole;
import ru.svyat.pdapp.dto.req.UserNew;
import ru.svyat.pdapp.dto.resp.CourierRsp;
import ru.svyat.pdapp.dto.resp.UserRsp;
import ru.svyat.pdapp.model.Courier;
import ru.svyat.pdapp.repo.CourierRepository;
import ru.svyat.pdapp.service.UserService;

import java.time.Instant;
import java.util.stream.Collectors;

import static ru.svyat.pdapp.dto.enums.UserRole.COURIER;
import static ru.svyat.pdapp.dto.enums.UserRole.USER;
import static ru.svyat.pdapp.utils.SecurityUtils.hasScope;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {

	private final JwtEncoder encoder;
	private final UserDetailsManager userManager;
	private final CourierRepository courierRepository;

	@Override
	public UserRsp getMe() {
		String userLogin = SecurityContextHolder.getContext()
				.getAuthentication()
				.getName();
		UserDetails user = userManager.loadUserByUsername(userLogin);

		return UserRsp.builder()
				.login(userLogin)
				.role(user.getAuthorities().stream()
						      .map(GrantedAuthority::getAuthority)
						      .map(UserRole::valueOf)
						      .findFirst()
						      .orElse(USER))
				.build();
	}

	@Override
	@Transactional
	public Page<CourierRsp> getCouriers(Pageable pageable) {
		if (!hasScope("ADMIN"))
			throw new AccessDeniedException("Current user is not Admin");
		return courierRepository.findAll(pageable).map(this::rspOfCourier);
	}

	@Override
	public UserRsp createNew(UserNew userNew, UserRole role) {
		userManager.createUser(
				User.builder()
						.username(userNew.getLogin())
						.password("{noop}"+userNew.getPassword())
						.authorities(role.name())
						.build());
		return UserRsp.builder()
				.login(userNew.getLogin())
				.role(role)
				.build();
	}

	@Override
	public String token(Authentication authentication) {
		Instant now = Instant.now();
		String scope = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.subject(authentication.getName())
				.claim("scope", scope)
				.build();
		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	private CourierRsp rspOfCourier(Courier courier){
		return new CourierRsp(UserRsp.builder()
				                      .login(courier.getUser().getLogin())
				                      .role(courier.getUser().getRoles().stream().findFirst().orElse(null))
				                      .build(),
		                      courier.getStatus());
	}
}
