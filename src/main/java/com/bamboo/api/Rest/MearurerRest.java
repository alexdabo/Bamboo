package com.bamboo.api.Rest;

import com.bamboo.api.dto.MeasurerDto;
import com.bamboo.api.dto.UptakeDto;
import com.bamboo.api.method.MeasurerDtoMethod;
import com.bamboo.model.entity.Measurer;
import com.bamboo.model.entity.Uptake;
import com.bamboo.model.method.MeasurerImpl;
import com.bamboo.model.method.SapImpl;
import com.bamboo.model.method.StatusImpl;
import com.bamboo.model.method.UptakeImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "MeasurerRest",
        urlPatterns = {
                "/api/measurer/*",
                "/api/measurer/notbilled/*",
                "/api/measurer/sap/*",
                "/api/measurer/status/*",
        }
)
public class MearurerRest extends HttpServlet {

    private final Gson gson = new Gson();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        MeasurerDtoMethod measurerMtd = new MeasurerDtoMethod();
        String responseJson = "";
        try {
            switch (request.getServletPath()) {

                case "/api/measurer":

                    // Get all measurers
                    if (request.getPathInfo() == null) {
                        responseJson = gson.toJson(measurerMtd.find());
                    }

                    // Get measurer by id
                    else if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findById(Integer.parseInt(request.getPathInfo().substring(1))));
                    }

                    // Route not found
                    else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/notbilled":
                    // Get all measurers with not billed uptketes
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findNotBilled(Integer.parseInt(request.getPathInfo().substring(1))));
                    }

                    // Route not found
                    else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/sap":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findBySap(Integer.parseInt(request.getPathInfo().substring(1))));
                    }
                    break;
                case "/api/measurer/status":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByStatus(Integer.parseInt(request.getPathInfo().substring(1))));
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


}
