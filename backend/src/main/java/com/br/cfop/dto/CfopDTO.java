package com.br.cfop.dto;

import com.br.cfop.entities.Cfop;

import java.io.Serializable;

public class CfopDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String cfop;
    private String descricao;
    private String grupo;

    public CfopDTO() {
    }

    public CfopDTO(Long id, String cfop, String descricao, String grupo) {
        this.id = id;
        this.cfop = cfop;
        this.descricao = descricao;
        this.grupo = grupo;
    }

    public CfopDTO(Cfop entity) {
        id = entity.getId();
        cfop = entity.getCfop();
        descricao = entity.getDescricao();
        grupo = entity.getGrupo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
