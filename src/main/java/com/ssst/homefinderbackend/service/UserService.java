package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserDto createUser(UserDto user) {
        user.setId(1);
        user.setName("Nejra");
        user.setLast_name("Muzaferija");
        user.setAge(21);
        user.setOccupation("engineer");
        return user;
    }

    public List<UserDto> getUsers() {
        List<UserDto> result = new ArrayList<>();
        UserDto x = new UserDto(2, "Bekir", 22,"hadziomerovic", "eng");
        UserDto y = new UserDto(3, "Zakira",  21,"skaljic", "eng");
        result.add(x);
        result.add(y);
        return result;
    }


    public UserDto getUser(Integer id) {
        return new UserDto(id,"Nejra",  21,"Muzaferija","eng");
    }

    public UserDto updateUser(Integer id, UserDto user) {
        user.setId(id);
        user.setAge(30);
        return user;
    }

    public void deleteUser(Integer id) {
        System.out.println("Deleted " + id);
    }
}
