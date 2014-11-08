/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.entities;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author igaraizabal
 */
public class marca {
    private String marcaName,marcaUri;
    private String idMarca,idEvento;
    public marca(String IdEvento,String IdMarca,String MarcaName,String MarcaUri)
    {
        idMarca=IdMarca;
        idEvento=IdEvento;
        marcaName=MarcaName;
        marcaUri=MarcaUri;
    }

    public marca(String Evento,String Marca)
    {
        idMarca=Marca;
        idEvento=Evento;
        marcaName="";
        if(!idMarca.equals("0"))
        {
            java.sql.Connection conn= common.getConnection();
            DataMethods DBM=new DataMethods(conn);
            try
            {
                ResultSet rs=DBM.getMarca(idMarca);
                if(rs.next())
                {
                    marcaName=rs.getString("mname");
                    marcaUri=rs.getString("uri");
                }
            }
            catch(SQLException ex){System.out.println(ex.getMessage());}
            
            common.CloseConnection(conn);
        }
    }

    /**
     * @return the marcaName
     */
    public String getMarcaName() {
        return marcaName;
    }

    /**
     * @param marcaName the marcaName to set
     */
    public void setMarcaName(String marcaName) {
        this.marcaName = marcaName;
    }

    /**
     * @return the marcaUri
     */
    public String getMarcaUri() {
        return marcaUri;
    }

    /**
     * @param marcaUri the marcaUri to set
     */
    public void setMarcaUri(String marcaUri) {
        this.marcaUri = marcaUri;
    }

    /**
     * @return the idMarca
     */
    public String getIdMarca() {
        return idMarca;
    }

    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
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
}
