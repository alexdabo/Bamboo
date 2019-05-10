package com.bamboo.api.Rest;

import com.bamboo.api.method.InvoiceDtoMethod;
import com.bamboo.api.method.RoleDtoMethod;
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

@WebServlet(
        name = "InvoiceRest",
        urlPatterns = {
                "/api/invoice/balance"
        }
)
public class InvoiceRest extends HttpServlet {
    private final RoleDtoMethod roleMtd = new RoleDtoMethod();
    private  Gson gson = new Gson();
    public InvoiceRest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {

            // Get all roles

            responseJson = gson.toJson(new InvoiceDtoMethod().findByDate(request.getParameter("date")));
        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }
}
