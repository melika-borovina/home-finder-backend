package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.data.entity.UserEntity;
import com.ssst.homefinderbackend.data.repository.UserRepo;
import com.ssst.homefinderbackend.model.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerNewUser(UserEntity user) {
        RoleEntity role = new RoleEntity();
        role.setId(2); // Default role "user"

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public UserEntity createFirstAdminUser() {
        UserEntity user = new UserEntity();
        user.setUsername("test@example.com");
        user.setPassword("test");
        user.setFirst_name("test");
        user.setLast_name("test");

        RoleEntity role = new RoleEntity();
        role.setId(1);

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<String> roleNames = user.getRoles()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getGrantedAuthorities(roleNames)
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        return roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public SimpleUser getUserByUsername(String userName) {
        getFullUserByUsername(userName); // user exists?
        return new SimpleUser(userName);
    }

    public UserEntity getUserWithoutPassword(String userName) {

         UserEntity user = userRepository.findOneByUsername(userName);
         UserEntity userResponse = new UserEntity();

         userResponse.setRoles(user.getRoles());
         userResponse.setFirst_name(user.getFirst_name());
         userResponse.setLast_name(user.getLast_name());
         userResponse.setRoles(user.getRoles());
         userResponse.setUsername(user.getUsername());

         return userResponse;
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity setUserRoleToAdmin(String username) {
        UserEntity user = userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        RoleEntity role = new RoleEntity();
        role.setId(1); // Set the role to 1

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        return userRepository.save(user);
    }


    private UserEntity getFullUserByUsername(String userName) {

        return userRepository.findOneByUsername(userName);
    }
}
