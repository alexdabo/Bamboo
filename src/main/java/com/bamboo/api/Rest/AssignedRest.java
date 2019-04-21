/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api.Rest;

import com.bamboo.api.dto.AssignedDto;
import com.bamboo.api.method.AssignedDtoMethod;
import com.bamboo.model.entity.Audit;
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
        name = "AssignedRest",
        urlPatterns = {
            "/api/assigned/*",
            "/api/assigned/beneficiary/*",
            "/api/assigned/notbilled/beneficiry/*",
            "/api/assigned/measurer/*"
        }
)
public class AssignedRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final AssignedDtoMethod assignedMtd = new AssignedDtoMethod();
    private final AuditImpl audit = new AuditImpl("Comunidad");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            switch (request.getServletPath()) {
                case "/api/assigned":
                    // Get all assigneds
                    if (request.getPathInfo() == null) {
                        responseJson = gson.toJson(assignedMtd.find());
                    } // Get assigned by id
                    else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(assignedMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));
                    } // Route not found
                    else {
                        response.sendError(404);
                    }
                    break;
                case "/api/assigned/beneficiary":
                    break;
                case "/api/assigned/notbilled/beneficiry":
                    break;
                case "/api/assigned/measurer":
                    break;
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
        if (request.getPathInfo() == null) {
            try {
                AssignedDto assignedDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AssignedDto.class);
                assignedDto = assignedMtd.save(assignedDto);
                if (assignedDto != null) {
                    map.put("saved", true);
                    map.put("assigned", assignedDto);
                    audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "Beneficiario: " + assignedDto.getBeneficiary().getFullName()));
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        if (request.getPathInfo() == null) {
            try {
                AssignedDto assignedDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AssignedDto.class);
                if (assignedMtd.update(assignedDto)) {
                    map.put("updated", true);
                    audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + assignedDto.getId()));
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
                if (assignedMtd.delete(Integer.parseInt(request.getPathInfo().substring(1)))) {
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
