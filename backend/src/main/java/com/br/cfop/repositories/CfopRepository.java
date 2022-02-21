package com.br.cfop.repositories;

import com.br.cfop.entities.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CfopRepository extends JpaRepository<Cfop, Long> {
    Optional<Cfop> findByCfop(String cfop);
}
