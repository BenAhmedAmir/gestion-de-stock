package com.benahmed.gestiondestock.DTO;

import com.benahmed.gestiondestock.model.MvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {
    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private ArticleDto article;
    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if(mvtStk == null){
            // TODO throw an exception
            return null;
        }
        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .build();
    }
    public static MvtStk toEntity(MvtStkDto mvtStkDto){
        if(mvtStkDto == null){
            // TODO throw an exception
            return null;
        }
        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        return mvtStk;
    }
}
