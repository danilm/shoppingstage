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
public class evento {
    private String idEvt;
    private String eventname;
    private String address;
    private String phone;
    private String email;
    private String fecha;

    public evento(String IdEvt, String EventName, String Address, String Phone, String Email, String Fecha)
    {
        idEvt=IdEvt;
        eventname=EventName;
        address=Address;
        phone=Phone;
        email=Email;
        fecha=Fecha;
    }
    
    public evento(String Evento)
    {
        idEvt=Evento;
        eventname="";
        address="";
        phone="";
        email="";
        fecha="";
        if(!Evento.equals("0"))
        {
            java.sql.Connection conn= common.getConnection();
            DataMethods DBM=new DataMethods(conn);
            ResultSet res=DBM.getEvent(Evento);
            try{
            if(res.next())
            {
                eventname=new String(res.getBytes("eventname"),"UTF-8");
                address=new String(res.getBytes("address"),"UTF-8");
                phone=new String(res.getBytes("phone"),"UTF-8");
                email=new String(res.getBytes("email"),"UTF-8");
                fecha=res.getDate("fecha").toString();
            }
            }catch(Exception ex){}
            
            common.CloseConnection(conn);
        }
    }

    /**
     * @return the idEvt
     */
    public String getIdEvt() {
        return idEvt;
    }

    /**
     * @param idEvt the idEvt to set
     */
    public void setIdEvt(String idEvt) {
        this.idEvt = idEvt;
    }

    /**
     * @return the eventname
     */
    public String getEventname() {
        return eventname;
    }

    /**
     * @param eventname the eventname to set
     */
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fecha
     */
    public String getFechaDB() {
        return fecha;
    }
    public String getFecha() {
        if(fecha.contains("-"))
        {
            String[] f=fecha.split("-");
            return f[2]+"/"+f[1]+"/"+f[0];
        }
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        if(fecha.contains("/"))
        {
            String[] f=fecha.split("/");
            fecha = f[2]+"-"+f[1]+"-"+f[0];
        }
        this.fecha = fecha;
    }
}
