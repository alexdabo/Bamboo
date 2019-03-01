package com.bamboo.api;

import com.bamboo.model.entity.Entity;
import com.bamboo.model.method.EntityImpl;
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

@WebServlet(name = "EntityRest", urlPatterns = {"/api/entity"})
public class EntityRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final EntityImpl entityImpl = new EntityImpl();
    Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;
        try {
            responseJson = gson.toJson(entityImpl.find());
        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        Entity entity = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Entity.class);
        try {
            if (entityImpl.update(entity)) {
                map.put("updated", true);
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
