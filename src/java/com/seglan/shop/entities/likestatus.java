/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.entities;

/**
 *
 * @author daniellopezmesa
 */
public class likestatus {
    private String idusuario,idprod,idevento,likestatus;

    public likestatus(String eventId, String iduser, String idproduc, String likstatus) {
        idusuario=iduser;
        idprod=idproduc;
        idevento=eventId;
        likestatus=likstatus;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getIdprod() {
        return idprod;
    }

    public void setIdprod(String idprod) {
        this.idprod = idprod;
    }

    public String getIdevento() {
        return idevento;
    }

    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }

    public String getLikestatus() {
        return likestatus;
    }

    public void setLikestatus(String likestatus) {
        this.likestatus = likestatus;
    }
    
    
}
