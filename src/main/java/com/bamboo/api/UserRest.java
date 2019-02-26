package com.bamboo.api;

import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.entity.User;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.UserImpl;
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

@WebServlet(name = "UserRest", urlPatterns = {"/api/user"})
public class UserRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final UserImpl userImpl = new UserImpl();
    private final AuditImpl audit = new AuditImpl(Beneficiary.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(userImpl.find());

            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(userImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }

            if (request.getParameter("username") != null && request.getParameter("password") != null) {
                System.out.println("logear");
                responseJson = gson.toJson(userImpl.findAndLogin(
                        request.getParameter("username"),
                        request.getParameter("password")
                ));
            }


            if (request.getParameter("data") != null) {
                responseJson = gson.toJson(userImpl.findByData(request.getParameter("data")));
            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        User user = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), User.class);
        user.setUserName(user.getEmail());
        user.setPassword(user.getDni());
        try {
            if (userImpl.save(user)) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + user.getDni()));
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("errorText", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        User user = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), User.class);
        try {
            if (userImpl.update(user)) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + user.getDni()));
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("errorText", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        User user = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), User.class);
        try {
            if (userImpl.delete(user)) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + user.getDni()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("errorText", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
