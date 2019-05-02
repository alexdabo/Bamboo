package com.bamboo.api.Rest;

import com.bamboo.api.method.InvoiceSapDtoMethod;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InvoiceSapRest", urlPatterns = {"/api/invoice/sap/*"})
public class InvoiceSapRest extends HttpServlet {
    private final InvoiceSapDtoMethod invoiceSapMtd = new InvoiceSapDtoMethod();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            // Get all invoiceSaps
            if (request.getPathInfo() == null) {
                //responseJson = gson.toJson(invoiceSapMtd.findByBeneficiary());
            }

            // Get invoiceSap by id
            else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                responseJson = gson.toJson(invoiceSapMtd.findByBeneficiary(Integer.parseInt(request.getPathInfo().substring(1))));
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
