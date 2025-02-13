package com.axdesousa.ms_beautique_query.controllers;

import com.axdesousa.ms_beautique_query.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedures")
public class BeautyProcedureController {

    private final BeautyProcedureService beautyProcedureService;

    public BeautyProcedureController(BeautyProcedureService beautyProcedureService) {
        this.beautyProcedureService = beautyProcedureService;
    }

    @GetMapping
    public ResponseEntity<List<BeautyProcedureDto>> getAllBeautyProcedures() {
        return ResponseEntity.ok().body(beautyProcedureService.getAllBeautyProcedures());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BeautyProcedureDto>> getBeautyProcedureByLikeName(@PathVariable  String name) {
        return ResponseEntity.ok().body(beautyProcedureService.getBeautyProcedureByLikeName(name));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<BeautyProcedureDto>> getBeautyProcedureByLikeDescription(@PathVariable String description) {
        return ResponseEntity.ok().body(beautyProcedureService.getBeautyProcedureByLikeDescription(description));
    }
}
