package com.bamboo.api;

import com.bamboo.model.method.BeneficiaryImpl;
import com.bamboo.model.method.MeasurerImpl;
import com.bamboo.model.method.RoleImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StatisticRest", urlPatterns = {"/api/statistic"})
public class StatisticRest extends HttpServlet {

    private Map<String, Object> map = new HashMap<>();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            //Find the number of people in the villages
            BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
            map.put("peopleFromVillage", beneficiaryImpl.peopleFromVillages());

            MeasurerImpl measurerImpl = new MeasurerImpl();
            map.put("measurerPerService", measurerImpl.findMeasurerPerService());
            map.put("measurerPerStatus", measurerImpl.findMeasurerPerStatus());

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
        }
        responseJson = gson.toJson(map);
        response.getWriter().write(responseJson);
    }
}
