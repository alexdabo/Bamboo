package com.bamboo.api.Rest;

import com.bamboo.api.dto.InvoiceSapDto;
import com.bamboo.api.method.InvoiceSapDtoMethod;
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

@WebServlet(
        name = "InvoiceSapRest",
        urlPatterns = {
                "/api/invoice/sap/*",
                "/api/invoice/sap/beneficiary/*"
        })
public class InvoiceSapRest extends HttpServlet {
    private final InvoiceSapDtoMethod invoiceSapMtd = new InvoiceSapDtoMethod();
    private  Gson gson = new Gson();
    public InvoiceSapRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {
            switch (request.getServletPath()) {
                case "/api/invoice/sap":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(invoiceSapMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/invoice/sap/beneficiary":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(invoiceSapMtd.findByBeneficiary(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
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
                InvoiceSapDto sapDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), InvoiceSapDto.class);
                sapDto = invoiceSapMtd.save(sapDto);
                if (sapDto != null) {
                    map.put("saved", true);
                    map.put("invoice", sapDto);
                    //audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + sapDto.getName()));
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
}
