package com.br.cfop.resources;

import com.br.cfop.dto.CfopDTO;
import com.br.cfop.services.CfopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cfops")
public class CfopResource {

    @Autowired
    private CfopService service;

    @GetMapping
    public ResponseEntity<Page<CfopDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "cfop") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<CfopDTO> dtos = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CfopDTO> findById(@PathVariable Long id) {
        CfopDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
