/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api;

import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Sap;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.SapImpl;
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
@WebServlet(name = "SapRest", urlPatterns = {"/api/sap"})
public class SapRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final SapImpl sapImpl = new SapImpl();
    private Map<String, Object> map = new HashMap<>();
    private final AuditImpl audit = new AuditImpl(Sap.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(sapImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(sapImpl.findById(Integer.parseInt(request.getParameter("id"))));
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
        String responseJson;

        Sap sap = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Sap.class);
        try {
            if (sapImpl.save(sap)) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + sap.getName()));
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        Sap sap = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Sap.class);
        try {
            if (sapImpl.update(sap)) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + sap.getId()));
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        try {
            Sap sap = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Sap.class);
            if (sapImpl.delete(sap)) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + sap.getId()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

}
