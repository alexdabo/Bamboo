package com.bamboo.api.Rest;

import com.bamboo.model.entity.AnotherServiceDetail;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.method.AnotherServiceDetailImpl;
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

@WebServlet(name = "AnotherServiceInvoice", urlPatterns = {"/api/anotherserviceinvoice"})
public class AnotherServiceInvoiceRest extends HttpServlet {
/*
    private final Gson gson = new Gson();
    private final AnotherServiceDetailImpl serviceDetailImpl = new AnotherServiceDetailImpl();
    private final AuditImpl audit = new AuditImpl(AnotherServiceDetail.class);
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(serviceDetailImpl.find());

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
        String responseJson = "";

        AnotherServiceDetail detail = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AnotherServiceDetail.class);
        try {
            if (serviceDetailImpl.save(detail)) {
                System.out.println("guardado");
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")),
                        "Factura para el beneficiario con dni " + detail.getInvoice().getBeneficiary().getDni() +
                                " por " + detail.getService().getName()));
            } else {
                map.put("saved", false);
                System.out.println("sin guardar");
            }

        } catch (Exception ex) {
            response.sendError(400);
            System.out.println(ex.getMessage());
            map.put("error", ex.getMessage());
        }
        map.put("error", detail);
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }*/
}
