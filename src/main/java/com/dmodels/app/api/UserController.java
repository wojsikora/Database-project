package com.dmodels.app.api;

import com.dmodels.app.security.model.Role;
import com.dmodels.app.security.model.User;
import com.dmodels.app.security.repository.RoleRepository;
import com.dmodels.app.security.repository.UserRepository;
import com.dmodels.app.security.service.RoleService;
import com.dmodels.app.security.service.UserService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll()
                .stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public List<UserResponse> findByRole(@PathVariable String name){

        System.out.println(name);
        final Role role = roleService.findRoleByName(name);
        System.out.println(role);
        return userService.findByRole(role).stream().map(UserResponse::fromUser).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request){
        final User user = userService.createUser(request.toUser(roleService));
        return UserResponse.fromUser(user);
    }

    @Data
    @Builder
    static class UserResponse {

        private UUID id;
        private String username;
        private String email;
        private Collection<String> roles;

        static UserResponse fromUser(User user) {
            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .roles(user.getRoleSet().stream().map(Role::getName).collect(Collectors.toList())).build();
        }

    }

    @Data
    static class CreateUserRequest {

        @NotNull
        private String username;

        @NotNull
        private String password;

        @NotNull
        @Pattern(regexp = ".*@.*")
        private String email;

        Collection<String> rolesNames;

        Collection<Role> getRoles(RoleService roleService){
            Collection<Role> roles = new HashSet<Role>();
            System.out.println(rolesNames);
            for(String roleName : this.rolesNames){
                Role role = roleService.findRoleByName(roleName);
                roles.add(Objects.requireNonNullElseGet(role, () -> new Role(roleName)));
            }
            return roles;
        }

        User toUser(RoleService roleService) {
            User user = new User(
                    this.username,
                    this.password,
                    this.email,
                    new Date()
            );

            user.addRoles(getRoles(roleService));

            return user;
        }

    }
}
