package com.bamboo.api;

import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Uptake;
import com.bamboo.model.method.UptakeImpl;
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

@WebServlet(name = "UptakeRest", urlPatterns = {"/api/uptake"})
public class UptakeRest extends HttpServlet {

    private Gson gson = new Gson();
    private final UptakeImpl uptakeImpl = new UptakeImpl();
    private Map<String, Object> map = new HashMap<>();

    public UptakeRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = "";
        try {
            responseJson = gson.toJson(uptakeImpl.find());
            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(uptakeImpl.findById(Integer.parseInt(request.getParameter("id"))));
            }
            if (request.getParameter("notBilled") != null) {
                if (request.getParameter("measurerId") != null) {
                    responseJson = gson.toJson(uptakeImpl.findNotBilled(Integer.parseInt(request.getParameter("measurerId"))));
                } else {
                    map.put("error", "El Id de medidor es requerido.");
                    responseJson = gson.toJson(map);
                }
            } else {
                if (request.getParameter("measurerId") != null) {
                    responseJson = gson.toJson(uptakeImpl.findByMeasurer(
                            Integer.parseInt(request.getParameter("measurerId")))
                    );
                }
            }


        } catch (Exception ex) {
            response.setStatus(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = null;

        Uptake uptake;
        try {
            uptake = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Uptake.class);
            if (uptakeImpl.save(uptake)) {
                map.put("saved", true);
                // audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "name: " + uptake.getName()));
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

        Uptake uptake = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Uptake.class);
        try {
            if (uptakeImpl.update(uptake)) {
                map.put("updated", true);
                //audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + uptake.getId()));
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

        try {
            Uptake uptake = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Uptake.class);
            if (uptakeImpl.delete(uptake)) {
                map.put("deleted", true);
                //audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "id: " + uptake.getId()));
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
