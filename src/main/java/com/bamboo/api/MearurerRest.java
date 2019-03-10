package com.bamboo.api;

import com.bamboo.model.method.MeasurerImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MeasurerRest", urlPatterns = {"/api/measurer"})
public class MearurerRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final MeasurerImpl measurerImpl = new MeasurerImpl();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(measurerImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(measurerImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
            if (request.getParameter("number") != null) {
                responseJson = gson.toJson(measurerImpl.findByNumber(request.getParameter("number")));
            }
            if (request.getParameter("sapId") != null) {
                responseJson = gson.toJson(measurerImpl.findBySap(Integer.parseInt(request.getParameter("sapId"))));
            }
            if (request.getParameter("statusId") != null) {
                responseJson = gson.toJson(measurerImpl.findByStatus(Integer.parseInt(request.getParameter("statusId"))));
            }

            if (request.getParameter("measurerPerService") != null) {
                responseJson = gson.toJson(measurerImpl.findMeasurerPerService());
            }
            if (request.getParameter("measurerPerStatus") != null) {
                responseJson = gson.toJson(measurerImpl.findMeasurerPerStatus());
            }
        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }
}
