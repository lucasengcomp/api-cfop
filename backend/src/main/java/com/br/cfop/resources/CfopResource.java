package com.br.cfop.resources;

import com.br.cfop.dto.CfopDTO;
import com.br.cfop.services.CfopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cfops")
public class CfopResource {

    @Autowired
    private CfopService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CfopDTO> findById(@PathVariable Long id) {
        CfopDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
