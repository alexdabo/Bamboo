package com.bamboo.api.method;

import com.bamboo.api.dto.AssignedDto;
import com.bamboo.model.entity.Assigned;
import com.bamboo.model.method.AssignedImpl;
import java.util.ArrayList;
import java.util.List;

public class AssignedDtoMethod {

    public AssignedDto save(AssignedDto assignedDto) throws Exception {
        AssignedDto newAssigned = null;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
           newAssigned = getAssignedDto(assignedImpl.save(getAssigned(assignedDto)));
        } catch (Exception e) {
            throw e;
        }
        return newAssigned;
    }


    public AssignedDto findById(int id) throws Exception {
        AssignedDto assignedDto = null;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            assignedDto = getAssignedDto(assignedImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return assignedDto;
    }

    public List<AssignedDto> find() throws Exception {
        List<AssignedDto> list = new ArrayList<>();
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            for (Assigned assigned : assignedImpl.find()) {
                list.add(getAssignedDto(assigned));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(AssignedDto assignedDto) throws Exception {
        boolean affected = false;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            if (assignedImpl.update(getAssigned(assignedDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(int id) throws Exception {
        boolean affected = false;
        AssignedImpl assignedImpl = new AssignedImpl();
        try {
            if (assignedImpl.delete(id)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private Assigned getAssigned(AssignedDto assignedDto) {
        Assigned assigned = new Assigned();
        assigned.setId(assignedDto.getId());
        assigned.setAssignmentDate(assignedDto.getAssignmentDate());
        assigned.setStatus(assignedDto.getStatus());
        assigned.setDebt(assignedDto.getDebt());
        assigned.setBeneficiary(assignedDto.getBeneficiary().getId());
        assigned.setMeasurer(assignedDto.getMeasurer().getId());
        return assigned;
    }

    private AssignedDto getAssignedDto(Assigned assigned) throws Exception {
        AssignedDto assignedDto = new AssignedDto();
        assignedDto.setId(assigned.getId());
        assignedDto.setAssignmentDate(assigned.getAssignmentDate());
        assignedDto.setStatus(assigned.getStatus());
        assignedDto.setDebt(assigned.getDebt());
        assignedDto.setBeneficiary(new BeneficiaryDtoMethod().findById(assigned.getBeneficiary()));
        assignedDto.setMeasurer(new MeasurerDtoMethod().findById(assigned.getMeasurer()));
        return assignedDto;
    }
}
