package com.bamboo.api;

import com.bamboo.api.dto.MeasurerDto;
import com.bamboo.model.entity.Measurer;
import com.bamboo.model.method.MeasurerImpl;
import com.bamboo.model.method.SapImpl;
import com.bamboo.model.method.StatusImpl;
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

@WebServlet(name = "MeasurerRest", urlPatterns = {"/api/measurer"})
public class MearurerRest extends HttpServlet {

    private final Gson gson = new Gson();
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        MeasurerImpl measurerImpl = new MeasurerImpl();
        String responseJson = "";
        try {
            responseJson = gson.toJson(measurerImpl.find());

            List<MeasurerDto> measurers = new ArrayList<>();
            for (Measurer measurer : measurerImpl.find()) {
                measurers.add(getMeasurerDto(measurer));
            }
            responseJson = gson.toJson(measurers);


            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(
                        getMeasurerDto(measurerImpl.findById(Integer.parseInt(request.getParameter("id"))))
                );
            }
            if (request.getParameter("number") != null) {
                responseJson = gson.toJson(
                        getMeasurerDto(measurerImpl.findByNumber(request.getParameter("number")))
                );
            }
            if (request.getParameter("sapId") != null) {
                measurers = new ArrayList<>();
                for (Measurer measurer : measurerImpl.findBySap(Integer.parseInt(request.getParameter("sapId")))) {
                    measurers.add(getMeasurerDto(measurer));
                }
                responseJson = gson.toJson(measurers);

            }
            if (request.getParameter("statusId") != null) {
                measurers = new ArrayList<>();
                for (Measurer measurer : measurerImpl.findByStatus(Integer.parseInt(request.getParameter("statusId")))) {
                    measurers.add(getMeasurerDto(measurer));
                }
                responseJson = gson.toJson(measurers);

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

    private MeasurerDto getMeasurerDto(Measurer measurer) {
        SapImpl sapImpl = new SapImpl();
        StatusImpl statusImpl = new StatusImpl();
        MeasurerDto measurerDto = new MeasurerDto();
        measurerDto.setId(measurer.getId());
        measurerDto.setNumber(measurer.getNumber());
        measurerDto.setInstallationDate(measurer.getInstallationDate());
        try {
            measurerDto.setSap(sapImpl.findById(measurer.getSap()));
            measurerDto.setStatus(statusImpl.findById(measurer.getStatus()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measurerDto;
    }
}
