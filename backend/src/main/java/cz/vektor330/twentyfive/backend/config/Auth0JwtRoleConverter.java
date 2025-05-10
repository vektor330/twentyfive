package cz.vektor330.twentyfive.backend.config;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class Auth0JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(final Jwt jwt) {
    final List<GrantedAuthority> authorities = new LinkedList<>();

    final Map<String, Object> claims = jwt.getClaims();

    if (claims.containsKey("permissions")) {
      // TODO refactoring: we are taking a permission and stuffing it into something called role
      final List<String> permissions = (List<String>) claims.get("permissions");
      authorities.addAll(
          permissions.stream()
              .map(SimpleGrantedAuthority::new)
              .toList()
      );
    }

    return authorities;
  }

}
