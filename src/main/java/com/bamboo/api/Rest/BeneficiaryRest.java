package com.bamboo.api.Rest;

import com.bamboo.api.dto.BeneficiaryDto;
import com.bamboo.model.entity.Audit;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.method.AuditImpl;
import com.bamboo.model.method.BeneficiaryImpl;
import com.bamboo.model.method.VillageImpl;
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
import java.util.stream.Collectors;

@WebServlet(name = "BeneficiaryRest", urlPatterns = {"/api/beneficiary"})
public class BeneficiaryRest extends HttpServlet {

    private final Gson gson = new Gson();
    private final BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
    private final AuditImpl audit = new AuditImpl(Beneficiary.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson = "";
        try {
            List<BeneficiaryDto> beneficiaries = new ArrayList<>();
            for (Beneficiary beneficiary : beneficiaryImpl.find()) {
                beneficiaries.add(getBeneficiaryDto(beneficiary));
            }
            responseJson = gson.toJson(beneficiaries);


            if (request.getParameter("id") != null) {
                responseJson = gson.toJson(getBeneficiaryDto(beneficiaryImpl.findById(Integer.parseInt(request.getParameter("id")))));
            }

            if (request.getParameter("peopleFromVillages") != null) {
                responseJson = gson.toJson(beneficiaryImpl.peopleFromVillages());
            }

            if (request.getParameter("data") != null) {
                if (request.getParameter("villageId") != null) {
                    beneficiaries = new ArrayList<>();
                    for (Beneficiary beneficiary : beneficiaryImpl.findByData(request.getParameter("data"), Integer.parseInt(request.getParameter("villageId")))) {
                        beneficiaries.add(getBeneficiaryDto(beneficiary));
                    }
                    responseJson = gson.toJson(beneficiaries);
                } else {

                    beneficiaries = new ArrayList<>();
                    for (Beneficiary beneficiary : beneficiaryImpl.findByData(request.getParameter("data"), 0)) {
                        beneficiaries.add(getBeneficiaryDto(beneficiary));
                    }
                    responseJson = gson.toJson(beneficiaries);

                }
            }

        } catch (Exception ex) {
            response.sendError(400);
            map.put("error", ex.getMessage());
            responseJson = gson.toJson(map);
        }
        response.getWriter().write(responseJson);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        BeneficiaryDto beneficiaryDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), BeneficiaryDto.class);
        try {
            if (beneficiaryImpl.save(getBeneficiary(beneficiaryDto))) {
                map.put("saved", true);
                audit.save(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiaryDto.getDni()));
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
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        BeneficiaryDto beneficiaryDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), BeneficiaryDto.class);
        try {
            if (beneficiaryImpl.update(getBeneficiary(beneficiaryDto))) {
                map.put("updated", true);
                audit.update(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiaryDto.getDni()));
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
        Map<String, Object> map = new HashMap<>();
        String responseJson;

        BeneficiaryDto beneficiaryDto = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), BeneficiaryDto.class);
        try {
            if (beneficiaryImpl.delete(getBeneficiary(beneficiaryDto))) {
                map.put("deleted", true);
                audit.delete(new Audit(Integer.parseInt(request.getHeader("user")), "dni: " + beneficiaryDto.getDni()));
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

    private BeneficiaryDto getBeneficiaryDto(Beneficiary beneficiary) {
        VillageImpl villageImpl = new VillageImpl();
        BeneficiaryDto beneficiaryDto = new BeneficiaryDto();
        beneficiaryDto.setId(beneficiary.getId());
        beneficiaryDto.setDni(beneficiary.getDni());
        beneficiaryDto.setLastName(beneficiary.getLastName());
        beneficiaryDto.setFirstName(beneficiary.getFirstName());
        beneficiaryDto.setAddress(beneficiary.getAddress());
        beneficiaryDto.setTelephone(beneficiary.getTelephone());
        beneficiaryDto.setPlaceReference(beneficiary.getPlaceReference());
        try {
            beneficiaryDto.setVillage(villageImpl.findById(beneficiary.getVillage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return beneficiaryDto;
    }

    private Beneficiary getBeneficiary(BeneficiaryDto beneficiaryDto) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(beneficiaryDto.getId());
        beneficiary.setDni(beneficiaryDto.getDni());
        beneficiary.setLastName(beneficiaryDto.getLastName());
        beneficiary.setFirstName(beneficiaryDto.getFirstName());
        beneficiary.setAddress(beneficiaryDto.getAddress());
        beneficiary.setTelephone(beneficiaryDto.getTelephone());
        beneficiary.setPlaceReference(beneficiaryDto.getPlaceReference());
        beneficiary.setVillage(beneficiaryDto.getVillage().getId());
        return beneficiary;
    }
}
