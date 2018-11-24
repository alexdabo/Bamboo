package com.bamboo.api;

import com.bamboo.model.entity.AnotherService;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.method.AnotherServiceImpl;
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

@WebServlet(name = "AnotheServiceRest", urlPatterns = {"/anotherservice"})
public class AnotherServiceRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final AnotherServiceImpl anotherServiceImpl = new AnotherServiceImpl();
    private final AuditImpl audit = new AuditImpl(AnotherService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(anotherServiceImpl.findById(Integer.parseInt(request.getParameter("id"))));
            } else {
                responseJson = gson.toJson(anotherServiceImpl.find());
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

        AnotherService anotherService = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AnotherService.class);
        try {
            if (anotherServiceImpl.save(anotherService)) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")),"name: "+anotherService.getName()));
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

        AnotherService anotherService = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AnotherService.class);
        try {
            if (anotherServiceImpl.update(anotherService)) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")),"id: "+anotherService.getId()));
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

            AnotherService anotherService = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), AnotherService.class);
            if (anotherServiceImpl.delete(anotherService)) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")),"Service id: "+anotherService.getId()));
            } else {
                map.put("deleted", false);
            }

        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
