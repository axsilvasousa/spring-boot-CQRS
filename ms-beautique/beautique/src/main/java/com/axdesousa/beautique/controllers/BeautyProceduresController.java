package com.axdesousa.beautique.controllers;

import com.axdesousa.beautique.dtos.BeautyProcedureDto;
import com.axdesousa.beautique.services.impl.BeautyProceduresServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beauty-procedures")
public class BeautyProceduresController {

    @Autowired
    private BeautyProceduresServiceImp serviceImp;

    @PostMapping()
    public ResponseEntity<BeautyProcedureDto> create(@RequestBody BeautyProcedureDto dto){
        BeautyProcedureDto payload = serviceImp.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceImp.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping()
    public ResponseEntity<BeautyProcedureDto> update(@RequestBody BeautyProcedureDto dto){
        BeautyProcedureDto payload = serviceImp.update(dto);
        return ResponseEntity.ok(payload);
    }

}
