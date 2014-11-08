/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.entities;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author igaraizabal
 */
public class model {

    private String modelName, marca;
    private String idEvento, idModel;
    private ArrayList<product> products;

    public model(String IdEvento, String IdModel, String ModelName, String Marca) {
        idModel = IdModel;
        idEvento = IdEvento;
        modelName = ModelName;
        products = new ArrayList<product>();
        marca = Marca;
    }

    public model(String Evento, String Model) {
        idModel = Model;
        idEvento = Evento;
        modelName = "";
        marca = "0";
        if (idModel.equals("0")) {
            products = new ArrayList<product>();
        } else {
            java.sql.Connection conn = common.getConnection();
            DataMethods DBM = new DataMethods(conn);
            ResultSet res = DBM.getModel(idModel);
            try {
                if (res.next()) {
                    modelName = res.getString("modelname");
                    marca = "" + res.getInt("marca");
                }
            } catch (Exception ex) {
            }
            products = DBM.getProductsModel(idModel);
            common.CloseConnection(conn);
        }
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the products
     */
    public ArrayList<product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(ArrayList<product> products) {
        this.products = products;
    }

    /**
     * @return the idEvento
     */
    public String getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the idModel
     */
    public String getIdModel() {
        return idModel;
    }

    /**
     * @param idModel the idModel to set
     */
    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

}
