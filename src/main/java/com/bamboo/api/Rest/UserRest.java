/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api.Rest;

import com.bamboo.api.dto.UserDto;
import com.bamboo.api.method.UserDtoMethod;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.User;
import com.bamboo.model.method.AuditImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author alexander
 */
@WebServlet(
        name = "UserRest",
        urlPatterns = {
                "/api/user/*",
                "/api/user/login",
                "/api/user/password"
        }
)
public class UserRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final UserDtoMethod userMtd = new UserDtoMethod();
    private final AuditImpl audit = new AuditImpl("Usuario");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            if (request.getServletPath().equals("/api/user"))
                // Get all users
                if (request.getPathInfo() == null)
                    responseJson = gson.toJson(userMtd.find());


                    // Get user by id
                else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2)
                    responseJson = gson.toJson(userMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));


                    // Route not found
                else response.sendError(404);
            else response.sendError(404);

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
        switch (request.getServletPath()) {
            case "/api/user":
                if (request.getPathInfo() == null) {
                    try {
                        UserDto userDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UserDto.class);

                        if (userMtd.save(userDto)) {
                            map.put("saved", true);
                            audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + userDto.getFullName()));
                        } else {
                            map.put("saved", false);
                        }

                    } catch (Exception ex) {
                        response.setStatus(400);
                        map.put("error", ex.getMessage());
                    }
                } else {
                    response.sendError(404);
                }
                break;
            case "/api/user/login":
                try {

                    UserDto userDto = userMtd.findAndLogin(request.getParameter("credential"), request.getParameter("password"));
                    if (userDto != null) {
                        map.put("logged", true);
                        map.put("user", userDto);
                    } else {
                        map.put("logged", false);
                    }

                } catch (Exception ex) {
                    response.setStatus(400);
                    map.put("logged", false);
                    map.put("msg", "Usuario o contraseña incorrectas");
                }

                break;
        }

        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        switch (request.getServletPath()) {
            case "/api/user":
                if (request.getPathInfo() == null) {
                    try {
                        UserDto userDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UserDto.class);
                        if (userMtd.update(userDto)) {
                            map.put("updated", true);
                            audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + userDto.getId()));
                        } else {
                            map.put("updated", false);
                        }

                    } catch (Exception ex) {
                        response.setStatus(400);
                        map.put("error", ex.getMessage());
                    }
                } else {
                    response.sendError(404);
                }
                break;
            case "/api/user/password":

                try {
                    UserDto userDto = userMtd.findAndLogin(request.getParameter("credential"), request.getParameter("currentPass"));
                    if (userDto != null) {
                        userDto.setPassword(request.getParameter("newPass"));
                        if (userMtd.updatePass(userDto)) {
                            map.put("updated", true);
                        }
                    }
                } catch (Exception ex) {
                    response.setStatus(400);
                    map.put("updated", false);
                    map.put("msg", "Usuario o contraseña incorrectas");
                }

                break;
        }


        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;
        if (request.getServletPath().equals("/api/user"))
            if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                try {
                    if (userMtd.delete(Integer.parseInt(request.getPathInfo().substring(1)))) {
                        map.put("deleted", true);
                        audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + request.getPathInfo().substring(1)));
                    } else {
                        map.put("deleted", false);
                    }
                } catch (Exception ex) {
                    response.setStatus(400);
                    map.put("error", ex.getMessage());
                }
            } else {
                response.sendError(404);
            }
        else response.sendError(404);
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
