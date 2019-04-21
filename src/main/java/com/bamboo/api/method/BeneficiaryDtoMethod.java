package com.bamboo.api.method;

import com.bamboo.api.dto.BeneficiaryDto;
import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.method.BeneficiaryImpl;

import java.util.ArrayList;
import java.util.List;

public class BeneficiaryDtoMethod {

    public BeneficiaryDto save(BeneficiaryDto beneficiaryDto) throws Exception {
        BeneficiaryDto newBeneficiary = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            newBeneficiary = getBeneficiaryDto(beneficiaryImpl.save(getBeneficiary(beneficiaryDto)));
        } catch (Exception e) {
            throw e;
        }
        return newBeneficiary;
    }

    public BeneficiaryDto findById(int id) throws Exception {
        BeneficiaryDto beneficiaryDto = null;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            beneficiaryDto = getBeneficiaryDto(beneficiaryImpl.findById(id));
        } catch (Exception e) {
            throw e;
        }
        return beneficiaryDto;
    }

    public List<BeneficiaryDto> find() throws Exception {
        List<BeneficiaryDto> list = new ArrayList<>();
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            for (Beneficiary beneficiary : beneficiaryImpl.find()) {
                list.add(getBeneficiaryDto(beneficiary));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(BeneficiaryDto beneficiaryDto) throws Exception {
        boolean affected = false;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            if (beneficiaryImpl.update(getBeneficiary(beneficiaryDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(int id) throws Exception {
        boolean affected = false;
        BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
        try {
            if (beneficiaryImpl.delete(id)) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
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

    private BeneficiaryDto getBeneficiaryDto(Beneficiary beneficiary) throws Exception {
        BeneficiaryDto beneficiaryDto = new BeneficiaryDto();
        beneficiaryDto.setId(beneficiary.getId());
        beneficiaryDto.setDni(beneficiary.getDni());
        beneficiaryDto.setLastName(beneficiary.getLastName());
        beneficiaryDto.setFirstName(beneficiary.getFirstName());
        beneficiaryDto.setAddress(beneficiary.getAddress());
        beneficiaryDto.setTelephone(beneficiary.getTelephone());
        beneficiaryDto.setPlaceReference(beneficiary.getPlaceReference());
        beneficiaryDto.setVillage(new VillageDtoMethod().findById(beneficiary.getVillage()));
        return beneficiaryDto;
    }
}
