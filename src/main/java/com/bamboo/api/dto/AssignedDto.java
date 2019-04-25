package com.bamboo.api.dto;

import java.util.List;

public class AssignedDto {
    private BeneficiaryDto beneficiary;
    private List<SimpleAssignedDto> assigneds;

    public AssignedDto() {

    }

    public AssignedDto(BeneficiaryDto beneficiary, List<SimpleAssignedDto> assigneds) {
        this.beneficiary = beneficiary;
        this.assigneds = assigneds;
    }

    public BeneficiaryDto getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDto beneficiary) {
        this.beneficiary = beneficiary;
    }

    public List<SimpleAssignedDto> getAssigneds() {
        return assigneds;
    }

    public void setAssigneds(List<SimpleAssignedDto> assigneds) {
        this.assigneds = assigneds;
    }

    public void addAssigned(SimpleAssignedDto assigned) {
        this.assigneds.add(assigned);
    }

}
