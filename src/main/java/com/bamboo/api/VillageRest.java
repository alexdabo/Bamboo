/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.api;

import com.bamboo.model.entity.Village;
import com.bamboo.model.method.InvoiceImpl;
import com.bamboo.model.method.VillageImpl;
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
 *
 * @author alexander
 */
@WebServlet(name = "VillageRest", urlPatterns = {"/village"})
public class VillageRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final VillageImpl villageImpl = new VillageImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(villageImpl.findById(Integer.parseInt(request.getParameter("id"))));
            } else {
                responseJson = gson.toJson(villageImpl.find());
            }
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Boolean> map = new HashMap<>();
        String responseJson;

        Village village = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Village.class);
        try {
            if (villageImpl.save(village)) {
                map.put("saved", true);
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Boolean> map = new HashMap<>();
        String responseJson;

        Village village = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Village.class);
        try {
            if (villageImpl.update(village)) {
                map.put("updated", true);
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Boolean> map = new HashMap<>();
        String responseJson;

        try {
            Village village = new Village();
            village.setId(Integer.parseInt(request.getParameter("id")));
            if (villageImpl.delete(village)) {
                map.put("deleted", true);
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
