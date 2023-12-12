package com.fruits.library.authentication;

import com.fruits.library.userConfig.Role;
import com.fruits.library.userConfig.User;
import com.fruits.library.userConfig.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public String register(RegisterRequest request){
        String password = request.getPassword();
        String rePassword = request.getRePassword();
        String role = request.getRole();
        final Role roleType;
        if(role == null){
            roleType = Role.USER;
        }
        else{
            roleType = Role.valueOf(role.toUpperCase());
        }
        if(!password.matches(rePassword)){
            throw new IllegalStateException("The passwords do not match.");
        }
        var user = User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleType)
                .build();
        userRepository.save(user);
        return "The user has been registered successfully.";
    }
}
