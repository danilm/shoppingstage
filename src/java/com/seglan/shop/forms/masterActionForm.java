/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.forms;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iñaki Garaizabal
 */
public class masterActionForm  extends org.apache.struts.action.ActionForm {

    public masterActionForm() {super();}
    public  HttpSession session;
    private String button="";
 

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {}
        session=request.getSession(true);
    }

    /**
     * @return the button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }
}
