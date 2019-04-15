package com.bamboo.api.Rest;

import com.bamboo.api.dto.UptakeDto;
import com.bamboo.api.method.UptakeDtoMethod;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Uptake;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.UptakeImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "UptakeRest", urlPatterns = {"/api/uptake/*"})
public class UptakeRest extends HttpServlet {

    private Gson gson = new Gson();
    private final UptakeDtoMethod uptakeMtd = new UptakeDtoMethod();
    private final AuditImpl audit = new AuditImpl("Mediadas");

    public UptakeRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = null;

        if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
            try {
                UptakeDto uptakeDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), UptakeDto.class);
                if (uptakeMtd.save(uptakeDto, Integer.parseInt(request.getPathInfo().substring(1)))) {
                    map.put("saved", true);
                    audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "medidor # " + request.getPathInfo().substring(1)));
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
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;
        if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
            try {
                if (uptakeMtd.delete(Integer.parseInt(request.getPathInfo().substring(1)))) {
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
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
