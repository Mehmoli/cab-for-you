package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.CabDto;
import com.novi.cabforyou.services.CabService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabforyou/cars")
public class CabController {

    private CabService cabService;

    public CabController(CabService cabService) {
        this.cabService = cabService;
    }

    // Is OK
    @GetMapping("")
    public ResponseEntity<List<CabDto>> getAllCabs() {

        List<CabDto> dtos = cabService.getAllCabs();

        return ResponseEntity.ok(dtos);
    }

    // Is OK
    @GetMapping("/{id}")
    public ResponseEntity<CabDto> getCab(@PathVariable("id") long id) {

        CabDto dto = cabService.getCab(id);

        return ResponseEntity.ok(dto);
    }

    //Is OK
    @PostMapping("")
    public ResponseEntity<CabDto> addCab(@RequestBody CabDto dto) {
        CabDto dto1 = cabService.addCab(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    //Is OK
    @PutMapping(value = "/{id}")
    public ResponseEntity<CabDto> updateCab(@PathVariable("id") long id, @RequestBody CabDto dto) {

        cabService.updateCab(id, dto);

        return ResponseEntity.noContent().build();
    }

    //Is OK
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        cabService.deleteCab(id);
        return ResponseEntity.noContent().build();
    }

}
