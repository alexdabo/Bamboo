package com.bamboo.api;

import com.bamboo.api.dto.UserDto;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.entity.User;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.RoleImpl;
import com.bamboo.model.method.UserImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "UserRest", urlPatterns = {"/api/user"})
public class UserRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final UserImpl userImpl = new UserImpl();
    private final AuditImpl audit = new AuditImpl(User.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            List<UserDto> users = new ArrayList<>();
            for (User user : userImpl.find()) {
                users.add(getUserDto(user));
            }
            responseJson = gson.toJson(users);


            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(getUserDto(userImpl.findById(Integer.parseInt(request.getParameter("id")))));
            }

            if (request.getParameter("username") != null && request.getParameter("password") != null) {
                User user = userImpl.findAndLogin(
                        request.getParameter("username"),
                        request.getParameter("password")
                );
                if (user == null) {
                    map.put("logged", false);
                } else {
                    map.put("logged", true);
                    map.put("user", getUserDto(user));
                }
                responseJson = gson.toJson(map);
            }


            if (request.getParameter("data") != null) {
                users = new ArrayList<>();
                for (User user : userImpl.findByData(request.getParameter("data"))) {
                    users.add(getUserDto(user));
                }
                responseJson = gson.toJson(users);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        UserDto userDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UserDto.class);
        userDto.setUserName(userDto.getEmail());
        userDto.setPassword(userDto.getDni());
        try {
            if (userImpl.save(getUser(userDto))) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + userDto.getDni()));
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;
        if (request.getParameter("currentPass") != null && request.getParameter("newPass") != null) {
            try {
                User user = userImpl.findAndLogin(request.getParameter("username"), request.getParameter("currentPass"));
                if (user != null) {
                    user.setPassword(request.getParameter("newPass"));
                    if (userImpl.updatePass(user)) {
                        map.put("updated", true);
                    }
                } else {
                    map.put("updated", false);
                    map.put("msg", "Contraseña actual no coicide");
                }
            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", ex.getMessage());
            }
        } else {
            UserDto userDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UserDto.class);
            try {
                if (userImpl.update(getUser(userDto))) {
                    map.put("updated", true);
                    audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + userDto.getDni()));
                } else {
                    map.put("updated", false);
                }

            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", ex.getMessage());
                map.put("user",getUser(userDto));
            }
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        UserDto userDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UserDto.class);
        try {
            if (userImpl.delete(getUser(userDto))) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + userDto.getDni()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    private UserDto getUserDto(User user) {
        RoleImpl roleImpl = new RoleImpl();
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
        try {
            userDto.setRole(roleImpl.findById(user.getRole()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
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
}
