/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

import com.seglan.shop.entities.marca;
import com.seglan.shop.entities.model;
import com.seglan.shop.entities.product;
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
public class editmarca extends masterActionForm {

    private FormFile file;
    private marca mark;

    public editmarca() {
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
        session.setAttribute("CURRENTPARAMS", getMark().getIdEvento()+","+getMark().getIdMarca()+",-1");
        ActionErrors errors = new ActionErrors();
        java.sql.Connection conn = common.getConnection();
        try {

                //product prod = new product(0, 0, prodName, reference, composition, description, price);
                DataMethods DBM = new DataMethods(conn);
                if (getMark().getMarcaName().length() == 0) {
                    errors.add("editmarcaError", new ActionMessage("error.entermodelname"));
                }
                FormFile myFile = getFile();
                boolean uploadPic=myFile.getFileName().length()>0;
                //if((getMark().getIdMarca().equals("0"))&&(myFile.getFileName().length()==0)){ errors.add("editmarcaError", new ActionMessage("error.entermodelpic"));}
                
                if (errors.size() == 0) {
                    if (!DBM.saveMarca(mark)) {
                        errors.add("editmarcaError", new ActionMessage("error.data"));
                    } else if(uploadPic){
                        session.setAttribute("CURRENTMAR", getMark());
                        
                        try {

                            String fpath = "events/" + getMark().getIdEvento() + "/marcas";

                            fpath = this.getServlet().getServletContext().getRealPath(fpath);

                            File f = new File(fpath);
                            if (!f.exists()) {
                                f.mkdirs();
                            }

                            String extension = myFile.getFileName().substring(myFile.getFileName().lastIndexOf(".")).toLowerCase();
                            if (myFile.getFileSize() > 307200) {
                                errors.add("editmarcaError", new ActionMessage("error.upload.size"));
                            } else if (!extension.equalsIgnoreCase(".jpg")) {
                                errors.add("editmarcaError", new ActionMessage("error.upload.ext"));
                            } else {
                                String name = getMark().getIdMarca() + ".jpg";
                                File fileToCreate = new File(fpath, name);
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(myFile.getFileData());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            errors.add("editmarcaError", new ActionMessage("error.saveok"));
                        } catch (Exception e) {
                            errors.add("editmarcaError", new ActionMessage("error.operation"));
                        }
                        
                    }
                } 
            
        } catch (Exception e) {
            errors.add("editmodelError", new ActionMessage("error.operation"));
        }
        if(errors.size()==0){errors.add("editmarcaError", new ActionMessage("error.saveok"));}
        
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the prod
     */
    public marca getMarca() {
        if (getMark() == null) {
            setMark((marca) session.getAttribute("CURRENTMAR"));
        }
        return getMark();
    }

    /**
     * @param prod the prod to set
     */
    public void setMarca(marca mod) {
        this.setMark(mod);
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
    public void setFile(FormFile ffile) {
        this.file = ffile;
    }

    /**
     * @return the marcaName
     */
    public String getMarcaName() {
        return getMarca().getMarcaName();
    }

    /**
     * @param marcaName the marcaName to set
     */
    public void setMarcaName(String marcaName) {
        getMarca().setMarcaName(marcaName);
    }

    /**
     * @return the marcaUri
     */
    public String getMarcaUri() {
        return getMarca().getMarcaUri();
    }

    /**
     * @param marcaUri the marcaUri to set
     */
    public void setMarcaUri(String marcaUri) {
        getMarca().setMarcaUri(marcaUri);
    }

    /**
     * @return the mark
     */
    public marca getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(marca mark) {
        this.mark = mark;
    }
}
