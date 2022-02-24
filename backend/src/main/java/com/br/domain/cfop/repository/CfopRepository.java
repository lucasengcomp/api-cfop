package com.br.domain.cfop.repository;

import com.br.domain.cfop.entity.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CfopRepository extends JpaRepository<Cfop, Long> {
    Optional<Cfop> findByCfop(String cfop);
}
