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
public class editproduct extends masterActionForm {

    private FormFile file;
    private product prod;

    public editproduct() {
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
        try {
            //product prod = new product(0, 0, prodName, reference, composition, description, price);
            DataMethods DBM = new DataMethods(conn);
            FormFile myFile = getFile();
            if (!prod.validateFields()) {
                errors.add("editproductError", new ActionMessage("error.fillall"));
            } else if ((prod.getIdProd().equals("0")) && (myFile.getFileName().length() == 0)) {
                errors.add("editproductError", new ActionMessage("error.entermodelpic"));
            }
            if (errors.size() == 0) {
                boolean uploadPic = myFile.getFileName().length() > 0;
                if (!DBM.saveProduct(prod)) {
                    errors.add("editproductError", new ActionMessage("error.data"));
                } else if (uploadPic) {
                    session.setAttribute("CURRENTPROD", prod);
                    try {

                        String fpath = "events/" + prod.getEvento() + "/products";

                        fpath = this.getServlet().getServletContext().getRealPath(fpath);

                        File f = new File(fpath);
                        if (!f.exists()) {
                            f.mkdirs();
                        }

                        String extension = myFile.getFileName().substring(myFile.getFileName().lastIndexOf(".")).toLowerCase();
                        if (myFile.getFileSize() > 307200) {
                            errors.add("editproductError", new ActionMessage("error.upload.size"));
                        } else if (!extension.equalsIgnoreCase(".jpg")) {
                            errors.add("editproductError", new ActionMessage("error.upload.ext"));
                        } else {
                            String name = prod.getIdProd() + ".jpg";
                            File fileToCreate = new File(fpath, name);
                            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                            fileOutStream.write(myFile.getFileData());
                            fileOutStream.flush();
                            fileOutStream.close();
                        }
                        errors.add("editproductError", new ActionMessage("error.saveok"));
                    } catch (Exception e) {
                        errors.add("editproductError", new ActionMessage("error.operation"));
                    }
                }
            }
        } catch (Exception e) {
            errors.add("editproductError", new ActionMessage("error.operation"));
        }
        if (errors.size() == 0) {
            errors.add("editproductError", new ActionMessage("error.saveok"));
        }
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the prodName
     */
    public String getProdName() {
        return getProd().getProdName();
    }

    /**
     * @param prodName the prodName to set
     */
    public void setProdName(String prodName) {
        getProd().setProdName(prodName);
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return getProd().getReference();
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        getProd().setReference(reference);
    }

    /**
     * @return the composition
     */
    public String getComposition() {
        return getProd().getComposition();
    }

    /**
     * @param composition the composition to set
     */
    public void setComposition(String composition) {
        getProd().setComposition(composition);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return getProd().getDescription();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        getProd().setDescription(description);
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return getProd().getPrice();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        getProd().setPrice(price);
    }

    /**
     * @return the file
     */
    public FormFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(FormFile file) {
        this.file = file;
    }

    /**
     * @return the colors
     */
    public String getColors() {
        return getProd().getColors();
    }

    /**
     * @param colors the colors to set
     */
    public void setColors(String colors) {
        getProd().setColors(colors);
    }

    /**
     * @return the sizes
     */
    public String getSizes() {
        return getProd().getSizes();
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(String sizes) {
        getProd().setSizes(sizes);
    }

    /**
     * @return the prod
     */
    public product getProd() {
        if (prod == null) {
            prod = (product) session.getAttribute("CURRENTPROD");
        }
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(product prod) {
        this.prod = prod;
    }
}
