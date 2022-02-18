package com.benahmed.gestiondestock.repository;

import com.benahmed.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<Integer, MvtStk> {
}
