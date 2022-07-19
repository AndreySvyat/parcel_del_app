package ru.svyat.pdapp.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
	public static boolean hasScope(String role) {
		var auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return auth != null &&
				auth.getAuthorities()
						.stream()
						.map(GrantedAuthority::getAuthority)
						.anyMatch(it -> it.equals("SCOPE_" + role));
	}

	public static String currentLogin(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
