package com.bamboo.api.method;

import com.bamboo.api.dto.AssignedDto;
import com.bamboo.api.dto.SimpleAssignedDto;
import com.bamboo.api.dto.StatusDto;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;

import java.util.ArrayList;
import java.util.List;

public class AssignedDtoMethod {

    public AssignedDto save(AssignedDto assigned) throws Exception {
        AssignedDto newAssigned = null;
        try {

            newAssigned = new AssignedDto();
            newAssigned.setBeneficiary(
                    new BeneficiaryDtoMethod().findById(assigned.getBeneficiary().getId())
            );


            boolean saved = false;
            for (SimpleAssignedDto simpleAssigned : assigned.getAssigneds()) {
                if (simpleAssigned.getMeasurer().getStatus() == null) {
                    simpleAssigned.getMeasurer().setStatus(new StatusDto(1, null));//default status active
                }
                if (new SimpleAssignedDtoMethod().save(
                        simpleAssigned, newAssigned.getBeneficiary().getId()
                ) != null) saved = true;
            }

            if (saved)
                newAssigned.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiary(
                        newAssigned.getBeneficiary().getId()
                ));
        } catch (Exception e) {
            throw e;
        }
        return newAssigned;
    }

    public AssignedDto transfer(AssignedDto assigned) throws Exception {
        AssignedDto newAssigned = null;
        try {

            newAssigned = new AssignedDto();
            newAssigned.setBeneficiary(
                    new BeneficiaryDtoMethod().findById(assigned.getBeneficiary().getId())
            );


            boolean saved = false;
            for (SimpleAssignedDto simpleAssigned : assigned.getAssigneds()) {
                if (simpleAssigned.getMeasurer().getStatus() == null) {
                    simpleAssigned.getMeasurer().setStatus(new StatusDto(1, null));//default status active
                }
                if (new SimpleAssignedDtoMethod().transfer(
                        simpleAssigned, newAssigned.getBeneficiary().getId()
                ) != null) saved = true;
            }

            if (saved)
                newAssigned.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiary(
                        newAssigned.getBeneficiary().getId()
                ));
        } catch (Exception e) {
            throw e;
        }
        return newAssigned;
    }


    public List<AssignedDto> find() throws Exception {
        List<AssignedDto> assigneds = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.findDistinctBeneficiary()) {
                AssignedDto assignedDto = new AssignedDto();
                assignedDto.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiary(assigned.getBeneficiary()));
                assignedDto.setBeneficiary(new BeneficiaryDtoMethod().findById(assigned.getBeneficiary()));
                assigneds.add(assignedDto);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            throw e;
        }
        return assigneds;

    }

    public AssignedDto findByBeneficiary(int beneficiaryId) throws Exception {
        AssignedDto assignedDto = null;

        try {
            assignedDto = new AssignedDto();
            assignedDto.setBeneficiary(new BeneficiaryDtoMethod().findById(beneficiaryId));
            assignedDto.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiary(beneficiaryId));
        } catch (Exception e) {
            throw e;
        }
        return assignedDto;

    }


    public AssignedDto findByBeneficiaryWithUptakes(int beneficiaryId) throws Exception {
        AssignedDto assignedDto = null;

        try {
            assignedDto = new AssignedDto();
            assignedDto.setBeneficiary(new BeneficiaryDtoMethod().findById(beneficiaryId));
            assignedDto.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiaryWithUptakes(beneficiaryId));
        } catch (Exception e) {
            throw e;
        }
        return assignedDto;

    }


    public AssignedDto findByBeneficiaryUnbilledUptakes(int beneficiaryId) throws Exception {
        AssignedDto assignedDto = null;

        try {
            assignedDto = new AssignedDto();
            assignedDto.setBeneficiary(new BeneficiaryDtoMethod().findById(beneficiaryId));
            assignedDto.setAssigneds(new SimpleAssignedDtoMethod().findByBeneficiaryUnbilledUptakes(beneficiaryId));
        } catch (Exception e) {
            throw e;
        }
        return assignedDto;

    }


}