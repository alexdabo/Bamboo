/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api.Rest;

import com.bamboo.api.dto.SapDto;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.api.method.SapDtoMethod;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alexander
 */
@WebServlet(
        name = "SapRest",
        urlPatterns = {
                "/api/sap",
                "/api/sap/*"
        }
)
public class SapRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final SapDtoMethod sapMtd = new SapDtoMethod();
    private final AuditImpl audit = new AuditImpl("SAP");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            // Get all saps
            if (request.getPathInfo() == null) {
                responseJson = gson.toJson(sapMtd.find());
            }

            // Get sap by id
            else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                responseJson = gson.toJson(sapMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));
            }

            // Route not found
            else {
                response.sendError(404);
            }
        } catch (Exception ex) {
            response.setStatus(400);
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

        if (request.getPathInfo() == null) {
            try {
                SapDto sapDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), SapDto.class);

                if (sapMtd.save(sapDto)) {
                    map.put("saved", true);
                    audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + sapDto.getName()));
                } else {
                    map.put("saved", false);
                }

            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", ex.getMessage());
            }
        } else {
            response.setStatus(404);
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        if (request.getPathInfo() == null) {
            try {
                SapDto sapDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), SapDto.class);

                if (sapMtd.update(sapDto)) {
                    map.put("updated", true);
                    audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + sapDto.getName()));
                } else {
                    map.put("updated", false);
                }

            } catch (Exception ex) {
                response.setStatus(400);
                map.put("error", ex.getMessage());
            }
        } else {
            response.setStatus(404);
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
                if (sapMtd.delete(Integer.parseInt(request.getPathInfo().substring(1)))) {
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
            response.setStatus(404);
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
