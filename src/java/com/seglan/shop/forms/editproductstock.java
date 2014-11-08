/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

import com.seglan.shop.entities.product;
import com.seglan.shop.forms.masterActionForm;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author igaraizabal
 */
public class editproductstock extends masterActionForm {
    private String color;
    private String size;
    private int cantidad;
    private int cantidad2;
    public editproductstock() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        java.sql.Connection conn = common.getConnection();
        product prod=(product)session.getAttribute("CURRENTPROD");
        try {
            //product prod = new product(0, 0, prodName, reference, composition, description, price);
            DataMethods DBM = new DataMethods(conn);
            if(cantidad<=0){errors.add("editproductstockError", new ActionMessage("error.cantidad"));}
            if (!DBM.addStock(prod.getIdProd(),color,size,cantidad,cantidad2)) {
                errors.add("editproductstockError", new ActionMessage("error.operation"));
            } else {
                errors.add("editproductstockError", new ActionMessage("error.empty"));
            }
        } catch (Exception e) {
            errors.add("editproductstockError", new ActionMessage("error.operation"));
        }
        common.CloseConnection(conn);
        return errors;
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
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the cantidad2
     */
    public int getCantidad2() {
        return cantidad2;
    }

    /**
     * @param cantidad2 the cantidad2 to set
     */
    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }
}
