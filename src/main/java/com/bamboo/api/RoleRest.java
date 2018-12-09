package com.bamboo.api;

import com.bamboo.model.method.RoleImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoleRest", urlPatterns = {"/api/role"})
public class RoleRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final RoleImpl roleImpl = new RoleImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(roleImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(roleImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }
}
