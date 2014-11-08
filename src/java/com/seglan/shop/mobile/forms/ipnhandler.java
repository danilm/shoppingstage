/*
 * This class is part of a private project
 * you are not allowed to modify, copy or using without author's permissions
 */

package com.seglan.shop.mobile.forms;

import com.seglan.shop.forms.masterActionForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Daniel Lopez <dani.lopez@gmail.com>
 */
public class ipnhandler extends masterActionForm{
    public ipnhandler(){
        super();
    }
    
    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }

    
}
