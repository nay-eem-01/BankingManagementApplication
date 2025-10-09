package org.example.firstproject.security.service;

import lombok.*;
import org.example.firstproject.entity.Privilege;
import org.example.firstproject.entity.Role;
import org.example.firstproject.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails build(User user){
        List<Role> roles = new ArrayList<>(user.getRoles());
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();

        for (Role role : roles){
            privileges.addAll(role.getPrivileges());
        }

        for (Privilege privilege : privileges){
            authorities.add(new SimpleGrantedAuthority(privilege.getPrivilegeName()));
        }
        return new CustomUserDetails(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                authorities);
    }

    @Override
    public String getUsername() {
        return name;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
