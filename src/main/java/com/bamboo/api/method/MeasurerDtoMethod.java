package com.bamboo.api.method;

import com.bamboo.api.dto.MeasurerDto;
import com.bamboo.api.dto.UptakeDto;
import com.bamboo.model.entity.Measurer;
import com.bamboo.model.method.MeasurerImpl;

import java.util.ArrayList;
import java.util.List;

public class MeasurerDtoMethod {

    /*


     */
    public MeasurerDto save(MeasurerDto measurerDto) throws Exception {
        MeasurerDto measurerDto1 = null;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            measurerDto1 = getMeasurerDto(measurerImpl.save(getMeasurer(measurerDto)));
        } catch (Exception e) {
            throw e;
        }
        return measurerDto1;
    }

    // Return simple
    public List<MeasurerDto> find() throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.find()) {
                list.add(getMeasurerDto(measurer));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public MeasurerDto findById(int id) throws Exception {
        MeasurerDto measurerDto = null;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            measurerDto = getMeasurerDto(measurerImpl.findById(id));
            measurerDto.setUptakes(new UptakeDtoMethod().findByMeasurer(id));
        } catch (Exception e) {
            throw e;
        }
        return measurerDto;
    }

    public List<MeasurerDto> findBySap(int id) throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.findBySap(id)) {
                list.add(getMeasurerDto(measurer));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<MeasurerDto> findByStatus(int id) throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.findByStatus(id)) {
                list.add(getMeasurerDto(measurer));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<MeasurerDto> findByBeneficiary(int beneficiaryId) throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.findByBeneficiary(beneficiaryId)) {
                list.add(getMeasurerDto(measurer));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    // Return with all uptakes
    public MeasurerDto findByIdWithUptakes(int id) throws Exception {
        MeasurerDto measurerDto = null;
        try {
            measurerDto = getMeasurerDto(new MeasurerImpl().findById(id));
            measurerDto.setUptakes(new UptakeDtoMethod().findByMeasurer(id));

        } catch (Exception e) {
            throw e;
        }
        return measurerDto;
    }

    public List<MeasurerDto> findByBeneficiaryWithUptakes(int beneficiaryId) throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.findByBeneficiary(beneficiaryId)) {
                MeasurerDto measurerDto = getMeasurerDto(measurer);
                measurerDto.setUptakes(new UptakeDtoMethod().findByMeasurer(measurer.getId()));
                list.add(measurerDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    // Return with uptakes not billed

    public MeasurerDto findByIdUnbilledUptakes(int id) throws Exception {
        MeasurerDto measurerDto = null;
        try {
            measurerDto = getMeasurerDto(new MeasurerImpl().findById(id));
            measurerDto.setUptakes(new UptakeDtoMethod().findNotBilled(id));

        } catch (Exception e) {
            throw e;
        }
        return measurerDto;
    }

    public List<MeasurerDto> findByBeneficiaryUnbilledUptakes(int beneficiaryId) throws Exception {
        List<MeasurerDto> list = new ArrayList<>();
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            for (Measurer measurer : measurerImpl.findByBeneficiary(beneficiaryId)) {
                MeasurerDto measurerDto = getMeasurerDto(measurer);
                measurerDto.setUptakes(new UptakeDtoMethod().findNotBilled(measurer.getId()));
                list.add(measurerDto);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean update(MeasurerDto measurerDto) throws Exception {
        boolean affected = false;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            if (measurerImpl.update(getMeasurer(measurerDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    public boolean delete(MeasurerDto measurerDto) throws Exception {
        boolean affected = false;
        MeasurerImpl measurerImpl = new MeasurerImpl();
        try {
            if (measurerImpl.delete(getMeasurer(measurerDto))) {
                affected = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return affected;
    }

    private Measurer getMeasurer(MeasurerDto measurerDto) {
        Measurer measurer = new Measurer();
        measurer.setId(measurerDto.getId());
        measurer.setInstallationDate(measurerDto.getInstallationDate());
        measurer.setNumber(measurerDto.getNumber());
        measurer.setSap(measurerDto.getSap().getId());
        measurer.setStatus(measurerDto.getStatus().getId());
        return measurer;
    }

    private MeasurerDto getMeasurerDto(Measurer measurer) throws Exception {
        SapDtoMethod sapDtoMethod = new SapDtoMethod();
        StatusDtoMethod statusDtoMethod = new StatusDtoMethod();
        MeasurerDto measurerDto = new MeasurerDto();
        measurerDto.setId(measurer.getId());
        measurerDto.setInstallationDate(measurer.getInstallationDate());
        measurerDto.setNumber(measurer.getNumber());
        measurerDto.setSap(sapDtoMethod.findById(measurer.getSap()));
        measurerDto.setStatus(statusDtoMethod.findById(measurer.getStatus()));
        return measurerDto;
    }
}
