package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.Users.LoginRequest;
import com.rayyanhunerkar.todolist.POJO.Users.SignUpRequest;
import com.rayyanhunerkar.todolist.POJO.Users.SignUpResponse;
import com.rayyanhunerkar.todolist.exception.UserExistsException;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User newUser;
        Optional<User> user = userRepository.findByEmail(
                username
        );
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return user.get();
    }

    public Response<Object> createUser(SignUpRequest request) throws Exception {
        User newUser;
        Optional<User> user = userRepository.findByEmail(
                request.getEmail()
        );

        if (user.isPresent()) {
            throw new UserExistsException("User Already Exists");
        }


        newUser = userRepository.save(User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build()
        );

        SignUpResponse response = SignUpResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .build();

        return Response.builder()
                .data(response)
                .message("User created successfully!")
                .build();
    }

    public Response<Object> loginUser(@NotNull LoginRequest request) throws Exception {

        User user = loadUserByUsername(request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        request.getPassword()
                )
        );

        return Response.builder()
                .data("Logged in")
                .message("User created successfully!")
                .build();
    }

}
