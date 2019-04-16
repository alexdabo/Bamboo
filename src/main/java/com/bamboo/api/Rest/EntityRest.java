package com.bamboo.api.Rest;

import com.bamboo.api.dto.EntityDto;
import com.bamboo.api.method.EntityDtoMethod;
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
    private final EntityDtoMethod entityMtd = new EntityDtoMethod();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {
            responseJson = gson.toJson(entityMtd.find());
        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        EntityDto entityDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), EntityDto.class);
        try {
            if (new EntityDtoMethod().update(entityDto)) {
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
