package com.bamboo.api;

import com.bamboo.model.method.MeasurerImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MeasurerRest", urlPatterns = {"/api/measurer"})
public class MearurerRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final MeasurerImpl measurerImpl = new MeasurerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(measurerImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(measurerImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
        }
        response.getWriter().write(responseJson);
    }
}
