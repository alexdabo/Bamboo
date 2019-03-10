package com.bamboo.api;

import com.bamboo.model.method.StatusImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StatusRest", urlPatterns = {"/api/status"})
public class StatusRest extends HttpServlet {

    private final Gson gson = new Gson();
    private Map<String, Object> map = new HashMap<>();
    private final StatusImpl statusImpl = new StatusImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(statusImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(statusImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }
}
