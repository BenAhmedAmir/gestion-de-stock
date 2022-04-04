package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.MvtStkDto;
import com.benahmed.gestiondestock.controller.api.MvtStkApi;
import com.benahmed.gestiondestock.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class MvtStkController implements MvtStkApi {
    private final MvtStockService service;
    @Autowired
    public MvtStkController(MvtStockService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<BigDecimal> stockReelArticle(Integer idArticle) {
        return ResponseEntity.ok(service.stockReelArticle(idArticle));
    }

    @Override
    public ResponseEntity<List<MvtStkDto>> mvtStockArticle(Integer idArticle) {
        return ResponseEntity.ok(service.mvtStockArticle(idArticle));
    }

    @Override
    public ResponseEntity<MvtStkDto> entreeStock(MvtStkDto dto) {
        return ResponseEntity.ok(service.entreeStock(dto));
    }

    @Override
    public  ResponseEntity<MvtStkDto> sortieStock(MvtStkDto dto) {
        return ResponseEntity.ok(service.sortieStock(dto));
    }

    @Override
    public ResponseEntity<MvtStkDto> correctionStockPositif(MvtStkDto dto) {
        return ResponseEntity.ok(service.correctionStockPositif(dto));
    }

    @Override
    public ResponseEntity<MvtStkDto> correctionStockNegatif(MvtStkDto dto) {
        return ResponseEntity.ok(service.correctionStockNegatif(dto));
    }
}
