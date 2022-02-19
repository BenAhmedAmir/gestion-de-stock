package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.DTO.MvtStkDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

public interface MvtStkApi {
    @PostMapping(value = APP_ROOT+ "/mvtstok/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto save(@RequestBody MvtStkDto dto);

    @GetMapping(value = APP_ROOT+ "/mvtstock/{idMvtstk}", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto findById(@PathVariable("idMvtstk") Integer id);

    @GetMapping(value = APP_ROOT+ "/mvtstock/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStkDto> findAll();

    @DeleteMapping(APP_ROOT + "/mvtstock/{idMvtstk}")
    void delete(@PathVariable("idMvtstk") Integer id);
}
