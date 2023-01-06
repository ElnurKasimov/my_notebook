package group_project.MyNotebook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class MyUserJdbcDetailsService implements UserDetailsService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<UserDetails> userData = jdbcTemplate.query(
                "SELECT password, email, role, user_name" +
                        " FROM users AS u" +
                        " INNER JOIN userRoles AS ur ON u.id = ur.user_id" +
                        " INNER JOIN roles AS r ON r.id = ur.role_id" +
                        " WHERE email = :userName",
                Map.of("userName", email),
                new UserDataRowMapper()
        );
        if (userData.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s is not found", email));
        } else {
            return userData.get(0);
        }
    }
    private static class UserDataRowMapper implements RowMapper<UserDetails> {

        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String role = rs.getString("role");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String name = rs.getString("user_name");

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Collections.singleton(() -> "ROLE_" + role);
                }

                @Override
                public String getPassword() {
                    return password;
                }

                @Override
                public String getUsername() {
                    return email;
                }

                public String getName() {
                    return name;
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
        }
    }
}
