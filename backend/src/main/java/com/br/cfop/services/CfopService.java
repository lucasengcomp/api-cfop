package com.br.cfop.services;

import com.br.cfop.dto.CfopDTO;
import com.br.cfop.entities.Cfop;
import com.br.cfop.repositories.CfopRepository;
import com.br.cfop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CfopService {

    @Autowired
    private CfopRepository repository;

    @Transactional
    public CfopDTO findById(Long id) {
        Optional<Cfop> cfopById = repository.findById(id);
        Cfop entity = cfopById.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado para o id informado"));
        return new CfopDTO(entity);
    }
}
