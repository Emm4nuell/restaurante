package br.com.management_restaurant.security;

import br.com.management_restaurant.enuns.RoleEnum;
import br.com.management_restaurant.exception.NotFoundException;
import br.com.management_restaurant.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var employee = employeeRepository.findByEmail(username).orElseThrow(() -> {
            throw new NotFoundException("Usuario nao localizado na base de dados. USERNAME: " + username);
        });

        Set<RoleEnum> roles = employee.getUserEntity().getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet());
        return User.builder()
                .id(employee.getId())
                .name(employee.getName())
                .username(employee.getEmail())
                .authorities(roles)
                .password(employee.getUserEntity().getPassword())
                .build();
    }
}
