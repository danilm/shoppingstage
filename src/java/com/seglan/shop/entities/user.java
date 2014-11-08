/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.entities;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.TripleDES;
import com.seglan.shop.sourcecode.common;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author igaraizabal
 */
public class user implements Serializable {

    private boolean publi, activated;
    private String id, username, userfname, pwd, email, cp, address, poblacion, provincia, pais, tlf, usertype, edad, sexo;
    private ArrayList<evento> eventos = new ArrayList<evento>();

    public user(int Id, String Username, String Userfname, String Pwd, String Email, String Tlf, String Address, String CP, String Poblacion, String Provincia, String Pais, int Publi, String Usertype,String Sexo,String Edad) {
        id = "" + Id;
        username = Username;
        userfname = Userfname;
        pwd = Pwd;
        email = Email;
        cp = CP;
        address = Address;
        poblacion = Poblacion;
        provincia = Provincia;
        pais = Pais;
        tlf = Tlf;
        sexo=Sexo;
        edad=Edad;
        if (Publi == 1) {
            publi = true;
        } else {
            publi = false;
        }
        usertype = Usertype;

    }

    public user(String usId) {
        id = usId;
        username = "";
        pwd = "";
        email = "";
        cp = "";
        address = "";
        poblacion = "";
        provincia = "";
        pais = "";
        tlf = "";
        sexo = "M";
        edad = "27";
        publi = false;
        usertype = "";
        if (!usId.equals("0")) {
            java.sql.Connection conn = common.getConnection();
            DataMethods DBM = new DataMethods(conn);
            ResultSet res = DBM.getUser("" + usId);
            try {
                if (res.next()) {
                    username = new String(res.getBytes("username"), "UTF-8");
                    userfname = new String(res.getBytes("userfname"), "UTF-8");
                    pwd = TripleDES.decryptB64(new String(res.getBytes("password"), "UTF-8"));
                    email = new String(res.getBytes("email"), "UTF-8");
                    cp = new String(res.getBytes("CP"), "UTF-8");
                    address = new String(res.getBytes("address"), "UTF-8");
                    poblacion = new String(res.getBytes("poblacion"), "UTF-8");
                    provincia = new String(res.getBytes("provincia"), "UTF-8");
                    pais = new String(res.getBytes("pais"), "UTF-8");
                    tlf = new String(res.getBytes("tlf"), "UTF-8");
                    sexo = new String(res.getBytes("sexo"), "UTF-8");
                    edad = new String(res.getBytes("edad"), "UTF-8");
                    usertype = new String(res.getBytes("usertype"), "UTF-8");
                    int Publi = res.getInt("publi");
                    if (Publi == 1) {
                        publi = true;
                    } else {
                        publi = false;
                    }
                    int activ = res.getInt("activated");
                    if (activ == 1) {
                        activated = true;
                    } else {
                        activated = false;
                    }
                }
            } catch (Exception ex) {
            }

            common.CloseConnection(conn);
        }
    }

    public user(String uemail, String pass) {
        id = "-1";
        publi = false;
        java.sql.Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        ResultSet res = DBM.getUser(uemail.toLowerCase(), pass);
        try {
            if (res.next()) {
                id = "" + res.getInt("iduser");
                username = res.getString("username");
                userfname = res.getString("userfname");
                pwd = TripleDES.decryptB64(res.getString("password"));
                email = res.getString("email");
                cp = res.getString("CP");
                address = res.getString("address");
                poblacion = res.getString("poblacion");
                provincia = res.getString("provincia");
                pais = res.getString("pais");
                tlf = res.getString("tlf");
                sexo = res.getString("sexo");
                edad = res.getString("edad");
                usertype = res.getString("usertype");

                int Publi = res.getInt("publi");
                if (Publi == 1) {
                    publi = true;
                } else {
                    publi = false;
                }
                int activ = res.getInt("activated");
                if (activ == 1) {
                    activated = true;
                } else {
                    activated = false;
                }
            }
        } catch (Exception ex) {
        }
        if (!id.equals("-1")) {
            if(usertype.equals("A")){res = DBM.getEvents();}
            else{res = DBM.getEventsEnabled();}
            try {
                evento ev=null;
                while (res.next()) {
                ev=new evento(""+res.getInt("idevento"),res.getString("eventname"), new String(res.getBytes("address"),"UTF-8"), new String(res.getBytes("phone"),"UTF-8"), new String(res.getBytes("email"),"UTF-8"), res.getDate("fecha").toString());
                eventos.add(ev);
                }
            } catch (Exception ex) {
            }
        }
        common.CloseConnection(conn);

    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
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
     * @return the usertype
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * @param usertype the usertype to set
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    /**
     * @return the userfname
     */
    public String getUserfname() {
        return userfname;
    }

    /**
     * @param userfname the userfname to set
     */
    public void setUserfname(String userfname) {
        this.userfname = userfname;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the tlf
     */
    public String getTlf() {
        return tlf;
    }

    /**
     * @param tlf the tlf to set
     */
    public void setTlf(String tlf) {
        this.tlf = tlf;
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String toXML() {
        String res = "<id>" + id + "</id>";
        res += "<username>" + username + "</username>";
        res += "<pwd>" + pwd + "</pwd>";
        res += "<email>" + email + "</email>";
        res += "<cp>" + cp + "</cp>";
        res += "<address>" + address + "</address>";
        res += "<poblacion>" + poblacion + "</poblacion>";
        res += "<provincia>" + provincia + "</provincia>";
        res += "<pais>" + pais + "</pais>";
        res += "<tlf>" + tlf + "</tlf>";
        res += "<publi>" + isPubli() + "</publi>";
        res += "<usertype>" + usertype + "</usertype>";
        return res;
    }

    /**
     * @return the activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * @param activated the activated to set
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * @return the publi
     */
    public boolean isPubli() {
        return publi;
    }

    /**
     * @param publi the publi to set
     */
    public void setPubli(boolean publi) {
        this.publi = publi;
    }

    public String getActivation() {
        return getId() + "-" + java.util.UUID.randomUUID().toString();
    }

    /**
     * @return the eventos
     */
    public ArrayList<evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(ArrayList<evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * @return the edad
     */
    public String getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(String edad) {
        this.edad = edad;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
