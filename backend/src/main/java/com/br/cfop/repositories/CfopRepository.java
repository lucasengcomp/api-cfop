package com.br.cfop.repositories;

import com.br.cfop.entities.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CfopRepository extends JpaRepository<Cfop, Long> {
}