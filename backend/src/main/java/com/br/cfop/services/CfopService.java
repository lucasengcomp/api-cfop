package com.br.cfop.services;

import com.br.cfop.dto.CfopDTO;
import com.br.cfop.entities.Cfop;
import com.br.cfop.repositories.CfopRepository;
import com.br.cfop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    @Transactional(readOnly = true)
    public Page<CfopDTO> findAllPaged(PageRequest pageRequest) {
        Page<Cfop> cfops = repository.findAll(pageRequest);
        return cfops.map(x -> new CfopDTO(x));
    }

    @Transactional
    public CfopDTO insert(CfopDTO dto) {
        Cfop entity = new Cfop();
        objectsCfop(dto, entity);
        entity = repository.save(entity);
        return new CfopDTO(entity);
    }

    @Transactional
    public CfopDTO update(Long id, CfopDTO dto) {
        try {
            Cfop entity = repository.getOne(id);
            objectsCfop(dto, entity);
            entity = repository.save(entity);
            return new CfopDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " not found");
        }
    }

    private void objectsCfop(CfopDTO dto, Cfop entity) {
        entity.setCfop(dto.getCfop());
        entity.setGrupo(dto.getGrupo());
        entity.setDescricao(dto.getDescricao());
    }
}
