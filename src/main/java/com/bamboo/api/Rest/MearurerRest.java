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
import com.google.gson.GsonBuilder;

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
                // Simple search of all the meters
                "/api/measurer/simple",
                // Simple search for meter by id
                "/api/measurer/simple/*",
                // Simple meter search by SAP.
                "/api/measurer/simple/by/sap/*",
                // Simple meter search by status.
                "/api/measurer/simple/by/status/*",
                //Simple meter search from a beneficiary.
                "/api/measurer/simple/from/beneficiary/*",
                //Search for meter by id with all its uptakes
                "/api/measurer/with/uptakes/from/measurer/*",
                //Search for meter by beneficiary with all its uptakes
                "/api/measurer/with/uptakes/from/beneficiary/*",
                //Meter search by id with all your not billed uptakes.
                "/api/measurer/with/unbilled/uptakes/from/measurer/*",
                //Meter search by beneficiary with all your not billed uptakes.
                "/api/measurer/with/unbilled/uptakes/from/beneficiary/*",

        }
)
public class MearurerRest extends HttpServlet {

    private Gson gson = new Gson();
    private Map<String, Object> map = new HashMap<>();

    public MearurerRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        MeasurerDtoMethod measurerMtd = new MeasurerDtoMethod();
        String responseJson = "";
        try {
            switch (request.getServletPath()) {

                case "/api/measurer/simple":

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

                case "/api/measurer/simple/by/sap":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findBySap(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/simple/by/status":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByStatus(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/simple/from/beneficiary":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByBeneficiary(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/with/uptakes/from/measurer":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByIdWithUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/with/uptakes/from/beneficiary":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByBeneficiaryWithUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/with/unbilled/uptakes/from/measurer":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByIdUnbilledUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
                    }
                    break;
                case "/api/measurer/with/unbilled/uptakes/from/beneficiary":
                    if (request.getPathInfo() != null && request.getPathInfo().split("/").length == 2) {
                        responseJson = gson.toJson(measurerMtd.findByBeneficiaryUnbilledUptakes(Integer.parseInt(request.getPathInfo().substring(1))));
                    } else {
                        response.sendError(404);
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
