package com.bamboo.api;

import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.BeneficiaryImpl;
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

@WebServlet(name = "BeneficiaryRest", urlPatterns = {"/api/beneficiary"})
public class BeneficiaryRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
    private Map<String, Object> map = new HashMap<>();
    private final AuditImpl audit = new AuditImpl(Beneficiary.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(beneficiaryImpl.find());


            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(beneficiaryImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }

            if (request.getParameter("peopleFromVillages") != null) {
                responseJson = gson.toJson(beneficiaryImpl.peopleFromVillages());
                System.out.println(responseJson);
            }

            if (request.getParameter("data") != null) {
                if (request.getParameter("villageId") != null) {
                    responseJson = gson.toJson(beneficiaryImpl.findByData(request.getParameter("data"), Integer.parseInt(request.getParameter("villageId"))));
                } else {
                    responseJson = gson.toJson(beneficiaryImpl.findByData(request.getParameter("data"), 0));
                }
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

        Beneficiary beneficiary = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Beneficiary.class);
        try {
            if (beneficiaryImpl.save(beneficiary)) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiary.getDni()));
            } else {
                map.put("saved", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        Beneficiary beneficiary = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Beneficiary.class);
        try {
            if (beneficiaryImpl.update(beneficiary)) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiary.getDni()));
            } else {
                map.put("updated", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson;

        Beneficiary beneficiary = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Beneficiary.class);
        try {
            if (beneficiaryImpl.delete(beneficiary)) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiary.getDni()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
