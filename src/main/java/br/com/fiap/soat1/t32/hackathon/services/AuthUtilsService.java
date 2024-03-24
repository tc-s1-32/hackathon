package br.com.fiap.soat1.t32.hackathon.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUtilsService {
	public static String getUserId(OidcUser oidcUser){
		return oidcUser != null && oidcUser.getSubject() != null ? oidcUser.getSubject().replace("auth0|",""):null;
	}
}
