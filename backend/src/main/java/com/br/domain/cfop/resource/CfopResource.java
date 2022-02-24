package com.br.domain.cfop.resource;

import com.br.domain.cfop.dto.CfopDTO;
import com.br.domain.cfop.service.CfopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(value = "Recursos de CFOP")
@RestController
@RequestMapping(value = "/cfops")
public class CfopResource {

    @Autowired
    private CfopService service;

    @ApiOperation(value = "Mostra todos os CFOPs paginados por parâmetros: " +
            "Número de página \n" +
            "Linhas por página \n" +
            "Ordem crescente ou decrescente \n" +
            "Ordenado por alguma parâmetro dos campos.")
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

    @ApiOperation(value = "Busca um registro por ID passado na requisição")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CfopDTO> findById(@PathVariable Long id) {
        CfopDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "Busca um registro por CFOP passado na requisição")
    @GetMapping(value = "/cfop/{cfop}")
    public ResponseEntity<CfopDTO> findByCfop(@PathVariable("cfop") String cfop) {
        CfopDTO list = service.findByCfop(cfop);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Faz a inserção de um novo registro e mostra-o no corpo da resposta")
    @PostMapping
    public ResponseEntity<CfopDTO> insert(@RequestBody CfopDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @ApiOperation(value = "Faz a atualização de um registro por um ID passado e mostra no corpo da requisição")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CfopDTO> update(@PathVariable Long id, @RequestBody CfopDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation(value = "Deleta um registro por ID passado no corpo da requisição")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
