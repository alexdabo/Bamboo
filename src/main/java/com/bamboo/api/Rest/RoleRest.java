package com.bamboo.api.Rest;

import com.bamboo.api.method.RoleDtoMethod;
import com.bamboo.model.method.BeneficiaryImpl;
import com.bamboo.model.method.MeasurerImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RoleRest", urlPatterns = {"/api/role/*"})
public class RoleRest extends HttpServlet {
    private final RoleDtoMethod roleMtd = new RoleDtoMethod();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            // Get all roles
            if (request.getPathInfo() == null) {
                responseJson = gson.toJson(roleMtd.find());
            }

            // Get role by id
            else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                responseJson = gson.toJson(roleMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));
            }

            // Route not found
            else {
                response.sendError(404);
            }
        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }
}
