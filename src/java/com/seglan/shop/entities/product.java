/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.entities;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.ResultSet;

/**
 *
 * @author igaraizabal
 */
public class product {
    private String idProd;
    private String evento;
    private String prodName;
    private String reference;
    private String composition;
    private String description;
    private String price;
    private String sizes;
    private String colors;
    private String btnText; 
    public product(String IdProd, String Evento,String ProdName,String Reference,String Composition,String Description,String Price,String Sizes,String Colors,String BtnText)
    {
        idProd=IdProd;
        evento=Evento;
        prodName=ProdName;
        reference=Reference;
        composition=Composition;
        description=Description;
        price=Price;
        sizes=Sizes;
        colors=Colors;
        btnText=BtnText;
    }
    public product(String IdProd, String Evento)
    {
        idProd=IdProd;
        evento=Evento;
        prodName="";
        reference="";
        composition="";
        description="";
        price="";
        sizes="";
        colors="";
        btnText="";
        if(!idProd.equals("0"))
        {
            java.sql.Connection conn= common.getConnection();
            DataMethods DBM=new DataMethods(conn);
            ResultSet res=DBM.getProduct(""+idProd);
            try{
            if(res.next())
            {
                prodName=new String(res.getBytes("prodname"),"UTF-8");
                reference=new String(res.getBytes("reference"),"UTF-8");
                composition=new String(res.getBytes("composition"),"UTF-8");
                description=new String(res.getBytes("description"),"UTF-8");
                price=res.getBigDecimal("price").toString().replace(".", ",");
                //price=new String(res.get("prize"),"UTF-8");
                sizes=new String(res.getBytes("sizes"),"UTF-8");
                colors=new String(res.getBytes("colors"),"UTF-8");
            }
            }catch(Exception ex){}
            
            common.CloseConnection(conn);
        }
    }
    /**
     * @return the idProd
     */
    public String getIdProd() {
        return idProd;
    }

    /**
     * @param idProd the idProd to set
     */
    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    /**
     * @return the evento
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     * @return the prodName
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * @param prodName the prodName to set
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the composition
     */
    public String getComposition() {
        return composition;
    }

    /**
     * @param composition the composition to set
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceDB()
    {
        return price.replace(",", ".");
    }
    /**
     * @return the price
     */
    public String getPrice() {
        return price.replace(".", ",");
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price.replace(",", ".");
    }

    /**
     * @return the sizes
     */
    public String getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the colors
     */
    public String getColors() {
        return colors;
    }

    /**
     * @param colors the colors to set
     */
    public void setColors(String colors) {
        this.colors = colors;
    }

    /**
     * @return the btnText
     */
    public String getBtnText() {
        return btnText;
    }

    /**
     * @param btnText the btnText to set
     */
    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }
    public boolean validateFields()
    {
        if((prodName.length()==0)||(reference.length()==0)||(price.length()==0)||(description.length()==0)||(composition.length()==0)){return false;}
        else{return true;}
    }
}
