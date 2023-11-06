package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.CabDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Cab;
import com.novi.cabforyou.repositories.CabRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CabService {

    private CabRepository cabRepository;

    public CabService(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }

    public List<CabDto> getAllCabs() {
        List<CabDto> cabDto = new ArrayList<>();
        List<Cab> cabs = cabRepository.findAll();
        for (Cab c: cabs){
            cabDto.add(transferToCabDto(c));
        }
        return cabDto;
    }


    public CabDto getCab(long id) {
        Optional<Cab> cab = cabRepository.findById(id);
        if(!cab.isPresent()){
            throw new RecordNotFoundException();
        } else {
            return transferToCabDto(cab.get());
        }
    }

    public CabDto addCab(CabDto cabDto) {
        Cab cab = transferToCab(cabDto);
        cabRepository.save(cab);
        return cabDto;

    }


    public void updateCab(long id, CabDto newCab) {
        if(!cabRepository.existsById(id)) throw new RecordNotFoundException();
        Cab cab = cabRepository.findById(id).get();
        cab.setMake(newCab.getMake());
        cab.setModel(newCab.getModel());
        cab.setAvailableSeats(newCab.getAvailableSeats());
        cab.setLicensePlate(newCab.getLicensePlate());
        cab.setCabType(newCab.getCabType());

        cabRepository.save(cab);
    }

    public void deleteCab(long id) {
        cabRepository.deleteById(id);
    }

    private Cab transferToCab(CabDto cabDto) {
        Cab cab = new Cab();
        cab.setCabId(cabDto.getCabId());
        cab.setMake(cabDto.getMake());
        cab.setModel(cabDto.getModel());
        cab.setAvailableSeats(cabDto.getAvailableSeats());
        cab.setLicensePlate(cabDto.getLicensePlate());
        cab.setCabType(cabDto.getCabType());

        return cab;
    }

    private CabDto transferToCabDto(Cab cab) {
        var dto = new CabDto();

        dto.cabId = cab.getCabId();
        dto.make = cab.getMake();
        dto.model = cab.getModel();
        dto.availableSeats = cab.getAvailableSeats();
        dto.licensePlate = cab.getLicensePlate();
        dto.carType = cab.getCabType();

        return dto;
    }


}
