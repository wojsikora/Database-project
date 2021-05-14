package com.dmodels.app.security.service;

import com.dmodels.app.security.model.Role;
import com.dmodels.app.security.model.User;
import com.dmodels.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.event.CaretListener;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByRole(Role role){
        return userRepository.findByRoleSetContains(role);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }
}
