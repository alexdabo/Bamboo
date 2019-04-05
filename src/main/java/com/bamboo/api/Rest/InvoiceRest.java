package com.bamboo.api.Rest;

import com.bamboo.model.method.InvoiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InvoiceRest", urlPatterns = {"/api/invoice"})
public class InvoiceRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final InvoiceImpl invoiceImpl = new InvoiceImpl();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(invoiceImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(invoiceImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }
}
