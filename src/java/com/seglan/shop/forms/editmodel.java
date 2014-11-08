/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

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
public class editmodel extends masterActionForm {

    private String btn;
    private String btnProd;
    private String btnText;
    private FormFile file;
    private model mod;

    public editmodel() {
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
        session.setAttribute("CURRENTPARAMS", mod.getIdEvento()+","+mod.getIdModel()+",-1");
        ActionErrors errors = new ActionErrors();
        java.sql.Connection conn = common.getConnection();
        try {
            if (btn.equalsIgnoreCase("Guardar")) {
                //product prod = new product(0, 0, prodName, reference, composition, description, price);
                DataMethods DBM = new DataMethods(conn);
                if (mod.getModelName().length() == 0) {
                    errors.add("editmodelError", new ActionMessage("error.entermodelname"));
                }
                FormFile myFile = getFile();
                boolean uploadPic=myFile.getFileName().length()>0;
                if((mod.getIdModel().equals("0"))&&(myFile.getFileName().length()==0)){ errors.add("editmodelError", new ActionMessage("error.entermodelpic"));}
                
                if (errors.size() == 0) {
                    if (!DBM.saveModel(mod)) {
                        errors.add("editmodelError", new ActionMessage("error.data"));
                    } else if(uploadPic){
                        session.setAttribute("CURRENTMOD", mod);
                        
                        try {

                            String fpath = "events/" + mod.getIdEvento() + "/models";

                            fpath = this.getServlet().getServletContext().getRealPath(fpath);

                            File f = new File(fpath);
                            if (!f.exists()) {
                                f.mkdirs();
                            }

                            String extension = myFile.getFileName().substring(myFile.getFileName().lastIndexOf(".")).toLowerCase();
                            if (myFile.getFileSize() > 307200) {
                                errors.add("editmodelError", new ActionMessage("error.upload.size"));
                            } else if (!extension.equalsIgnoreCase(".jpg")) {
                                errors.add("editmodelError", new ActionMessage("error.upload.ext"));
                            } else {
                                String name = mod.getIdModel() + ".jpg";
                                File fileToCreate = new File(fpath, name);
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(myFile.getFileData());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                            errors.add("editmodelError", new ActionMessage("error.saveok"));
                        } catch (Exception e) {
                            errors.add("editmodelError", new ActionMessage("error.operation"));
                        }
                        
                    }
                } 
            }else 
            {
                if(btnText.length()==0){errors.add("addbuttonError", new ActionMessage("error.addtext"));}
                else if((btnProd==null)||(btnProd.length()==0)){errors.add("addbuttonError", new ActionMessage("error.addprod"));}
                else{
                product pr = new product(btnProd, mod.getIdEvento());
                pr.setBtnText(btnText);
                mod.getProducts().add(pr);
                session.setAttribute("CURRENTMOD", mod);
                }
            }
        } catch (Exception e) {
            errors.add("editmodelError", new ActionMessage("error.operation"));
        }
        if(errors.size()==0){errors.add("editmodelError", new ActionMessage("error.saveok"));}
        
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the prodName
     */
    public String getModName() {
        return getMod().getModelName();
    }

    /**
     * @param prodName the prodName to set
     */
    public void setModName(String modName) {
        getMod().setModelName(modName);
    }
    /**
     * @return the prodName
     */
    public String getMarca() {
        return getMod().getMarca();
    }

    /**
     * @param prodName the prodName to set
     */
    public void setMarca(String modName) {
        getMod().setMarca(modName);
    }

    /**
     * @return the prod
     */
    public model getMod() {
        if (mod == null) {
            mod = (model) session.getAttribute("CURRENTMOD");
        }
        return mod;
    }

    /**
     * @param prod the prod to set
     */
    public void setMod(model mod) {
        this.mod = mod;
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
     * @return the btn
     */
    public String getBtn() {
        return btn;
    }

    /**
     * @param btn the btn to set
     */
    public void setBtn(String btn) {
        this.btn = btn;
    }

    /**
     * @return the btnProd
     */
    public String getBtnProd() {
        return btnProd;
    }

    /**
     * @param btnProd the btnProd to set
     */
    public void setBtnProd(String btnProd) {
        this.btnProd = btnProd;
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
}
