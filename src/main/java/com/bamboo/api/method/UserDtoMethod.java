package com.bamboo.api.method;

import com.bamboo.api.dto.UserDto;
import com.bamboo.model.entity.User;
import com.bamboo.model.method.UserImpl;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMethod {

    public boolean save(UserDto userDto) throws Exception {
        boolean affected = false;
        UserImpl userImpl = new UserImpl();
        try {
            if (userImpl.save(getUser(userDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }


    public UserDto findById(int id) throws Exception {
        UserDto userDto = null;
        UserImpl userImpl = new UserImpl();
        try {
            userDto = getUserDto(userImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return userDto;
    }
    public UserDto findAndLogin(String credential, String password) throws Exception {
        UserDto userDto = null;
        UserImpl userImpl = new UserImpl();
        try {
            userDto = getUserDto(userImpl.findAndLogin(credential,password));
        } catch (Exception e) {
            throw e;
        }
        return userDto;
    }

    public List<UserDto> find() throws Exception {
        List<UserDto> list = new ArrayList<>();
        UserImpl userImpl = new UserImpl();
        try {
            for (User user : userImpl.find()) {
                list.add(getUserDto(user));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(UserDto userDto) throws Exception {
        boolean affected = false;
        UserImpl userImpl = new UserImpl();
        try {
            if (userImpl.update(getUser(userDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean updatePass(UserDto userDto) throws Exception {
        boolean affected = false;
        UserImpl userImpl = new UserImpl();
        try {
            if (userImpl.updatePass(getUser(userDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(int id) throws Exception {
        boolean affected = false;
        UserImpl userImpl = new UserImpl();
        try {
            if (userImpl.delete(id)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private User getUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setDni(userDto.getDni());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());
        user.setRole(userDto.getRole().getId());
        return user;
    }

    private UserDto getUserDto(User user) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setDni(user.getDni());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setTelephone(user.getTelephone());
        userDto.setAddress(user.getAddress());
        userDto.setRole(new RoleDtoMethod().findById(user.getRole()));
        return userDto;
    }
}
