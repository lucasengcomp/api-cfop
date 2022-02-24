package com.br.domain.cfop.service;

import com.br.domain.cfop.dto.CfopDTO;
import com.br.domain.cfop.entity.Cfop;
import com.br.domain.cfop.repository.CfopRepository;
import com.br.domain.cfop.service.exceptions.DatabaseException;
import com.br.domain.cfop.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public CfopDTO findByCfop(String cfop) {
        Optional<Cfop> resultCfop = repository.findByCfop(cfop);
        Cfop entity = resultCfop.orElseThrow(() -> new ResourceNotFoundException("CFOP " + cfop + " não existe"));
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

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void objectsCfop(CfopDTO dto, Cfop entity) {
        entity.setCfop(dto.getCfop());
        entity.setGrupo(dto.getGrupo());
        entity.setDescricao(dto.getDescricao());
    }
}
