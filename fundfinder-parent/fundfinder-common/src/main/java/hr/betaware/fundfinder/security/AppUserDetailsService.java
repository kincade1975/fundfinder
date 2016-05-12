package hr.betaware.fundfinder.security;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.betaware.fundfinder.security.AppUser.AppUserRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {

	private static String ADMIN_USERNAME = "admin";
	private static String ADMIN_PASSWORD = "admin";
	private static List<String> ADMIN_ROLES = Arrays.asList(AppUserRole.ROLE_ADMIN.name());

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Authenticating user with username [{}]", username);

		AppUser user = null;
		if (ADMIN_USERNAME.equals(username)) {
			user = new AppUser(username, DigestUtils.sha1Hex(ADMIN_PASSWORD), false, false, true, false, ADMIN_ROLES);
		}

		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}

		return new AppUserDetails(user);
	}

}
