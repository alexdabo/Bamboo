package com.bamboo.api;

import com.bamboo.model.entity.SapDetail;
import com.bamboo.model.method.RoleImpl;
import com.bamboo.model.method.SapDetailImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServiceInvoiceRest", urlPatterns = {"/api/invoice/sap"})
public class SapInvoiceRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final SapDetailImpl detailImpl = new SapDetailImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(detailImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(detailImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }
}
