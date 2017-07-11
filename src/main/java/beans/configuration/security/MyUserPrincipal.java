package beans.configuration.security;

import beans.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private User user;

    MyUserPrincipal(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        String roles = user.getRole();
        List<String> roleList = parseRolesList(roles);
        for (String role: roleList)
            auths.add(new SimpleGrantedAuthority(role));

        return auths;
    }

    private List<String> parseRolesList(String roles) {

        String regexCommaPattern = "\\s*,\\s*";
        return Arrays.asList(roles.split(regexCommaPattern));

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
}
