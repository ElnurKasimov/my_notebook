package group_project.MyNotebook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MyUserJdbcDetailsService implements UserDetailsService {
    private final UserService service;
    private UserDto dto;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            dto = service.findByUsername(username);
            return new UserDetails() {
                @Override
                public List<SimpleGrantedAuthority> getAuthorities() {
                    return dto.getRoles().stream()
                            .map(roleDao ->
                                    new SimpleGrantedAuthority(roleDao.getName()))
                            .collect(Collectors.toList());
                }

                @Override
                public String getPassword() {
                    return dto.getPassword();
                }

                @Override
                public String getUsername() {
                    return dto.getName();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        } catch (ResponseStatusException e) {
            throw new UsernameNotFoundException(username);
        }

    }
}
