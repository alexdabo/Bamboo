package com.bamboo.api;

import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;
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

import static java.lang.Integer.*;

@WebServlet(name = "AssignedRest", urlPatterns = {"/api/assigned"})
public class AssignedRest extends HttpServlet {

    private Gson gson;
    private final AssignedImpl assignedImpl = new AssignedImpl();

    public AssignedRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";

        try {

            responseJson = gson.toJson(assignedImpl.find());

            if (request.getParameter("beneficiaryId") != null) {
                responseJson = gson.toJson(assignedImpl.findByBeneficiary(
                        Integer.parseInt(request.getParameter("beneficiaryId"))
                ));

                if (request.getParameter("measurerId") != null) {
                    responseJson = gson.toJson(assignedImpl.findById(
                            Integer.parseInt(request.getParameter("beneficiaryId")),
                            Integer.parseInt(request.getParameter("measurerId"))
                    ));

                }

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
        String responseJson = null;
        try {
            Assigned assigned = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Assigned.class);
            if (assignedImpl.save(assigned)) {
                map.put("saved", true);
            } else {
                map.put("saved", false);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            response.sendError(400, ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
