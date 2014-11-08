/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.entities;

/**
 *
 * @author igaraizabal
 */
public class pedido {
    private String idrow;
    private String idprod;
    private String color;
    private String size;
    private String prodname;
    private String pagado;
    private String referencia;
    private String precio;
    private String totalPedido;
    
    public pedido(int IdRow,int IdProd,String Color,String Size,String ProdName,String price)
    {
        idrow=""+IdRow;
        idprod=""+IdProd;
        color=Color;
        size=Size;
        prodname=ProdName;
        precio = price;
    }
    /**
     * @return the idrow
     */
    public String getIdrow() {
        return idrow;
    }

    /**
     * @param idrow the idrow to set
     */
    public void setIdrow(String idrow) {
        this.idrow = idrow;
    }

    /**
     * @return the idprod
     */
    public String getIdprod() {
        return idprod;
    }

    /**
     * @param idprod the idprod to set
     */
    public void setIdprod(String idprod) {
        this.idprod = idprod;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the prodname
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * @param prodname the prodname to set
     */
    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(String totalPedido) {
        this.totalPedido = totalPedido;
    }
    
    
}
