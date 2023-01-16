package com.benahmed.gestiondestock.service;

import com.benahmed.gestiondestock.DTO.MvtStkDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStockService {
    BigDecimal stockReelArticle(Integer idArticle);
    List<MvtStkDto> mvtStockArticle(Integer idArticle);
    MvtStkDto entreeStock(MvtStkDto dto);
    MvtStkDto sortieStock(MvtStkDto dto);
    MvtStkDto correctionStockPositif(MvtStkDto dto);
    MvtStkDto correctionStockNegatif(MvtStkDto dto);

}
