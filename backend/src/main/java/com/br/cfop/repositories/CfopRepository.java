package com.br.cfop.repositories;

import com.br.cfop.dto.CfopDTO;
import com.br.cfop.entities.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CfopRepository extends JpaRepository<Cfop, Long> {
    CfopDTO findByCfop(String cfop);
}
