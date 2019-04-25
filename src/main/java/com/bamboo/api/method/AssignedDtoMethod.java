package com.bamboo.api.method;

import com.bamboo.api.dto.AssignedDto;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;

import java.util.ArrayList;
import java.util.List;

public class AssignedDtoMethod {


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