/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.sourcecode;

import com.seglan.shop.entities.*;
import com.seglan.shop.paypal.PaypalIpn.IpnInfo;
import java.sql.ResultSet;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataMethods {

    private DBAccess DBA = null;
    private Connection conn;

    public DataMethods(Connection Conn) {
        conn = Conn;
        DBA = new DBAccess(conn);
    }

    public user login2(String email, String pwd) {
        String SqlSt = "SELECT * FROM users WHERE email='" + email + "' AND password ='" + TripleDES.encryptB64(pwd) + "'";
        ResultSet res = DBA.resultsetSQL(SqlSt);
        try {
            user us = null;
            if (res.next()) {
                us = new user(res.getInt("iduser"), res.getString("username"), res.getString("userfname"),
                        TripleDES.decryptB64(res.getString("password")), res.getString("email"), res.getString("tlf"), res.getString("address"),
                        res.getString("CP"), res.getString("poblacion"), res.getString("provincia"), res.getString("pais"),
                        res.getInt("publi"), res.getString("usertype"),res.getString("sexo"),res.getString("edad"));
            }

            common.CloseStatement(res);
            return us;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public ResultSet getHojaPedido(String usid,String confirmed)
    {
        String SqlSt="SELECT * FROM (SELECT pedidos.iduser,pedidos.idevento,pedidos.confirmed, pedidos.idrow,pedidos.idprod,pedidos.color, pedidos.size,products.prodname,products.reference,products.price,(SELECT stock.cantidad FROM stock WHERE pedidos.idprod=stock.idprod AND pedidos.color=stock.color AND pedidos.size=pedidos.size) AS cantidad,(SELECT stock.cantidad2 FROM stock WHERE pedidos.idprod=stock.idprod AND pedidos.color=stock.color AND pedidos.size=pedidos.size) AS cantidad2,users.email FROM pedidos,products,users WHERE pedidos.confirmed="+confirmed+" AND pedidos.idprod=products.idprod AND pedidos.iduser=users.iduser) AS peds WHERE iduser='"+usid+"'";
        return DBA.resultsetSQL(SqlSt);
    }
    public ResultSet getEmailsHojaPedido(String confirmed)
    {
        String SqlSt="SELECT DISTINCT(users.email),users.iduser FROM pedidos,users WHERE pedidos.iduser=users.iduser AND pedidos.confirmed="+confirmed;
        return DBA.resultsetSQL(SqlSt);
    }
        public String restorePassword(String email) {
        try {
            String newpwd = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            if (DBA.executeSQL("UPDATE users SET password='" + TripleDES.encryptB64(newpwd) + "' WHERE email='" + email.toLowerCase() + "'")) {
                return newpwd;
            } else {
                return "ERROR";
            }
        } catch (Exception ex) {
            return "ERROR";
        }
    }

    public ResultSet getUsers() {
        return DBA.resultsetSQL("SELECT * FROM users");
    }

    public ResultSet getUser(String id) {
        return DBA.resultsetSQL("SELECT * FROM users WHERE iduser=" + id);
    }

    public ResultSet getUser(String email, String pwd) {
        return DBA.resultsetSQL("SELECT * FROM users WHERE email='" + email + "' AND password='" + TripleDES.encryptB64(pwd) + "'");
    }

    public boolean getUserEmailExist(String email) {
        ResultSet res = DBA.resultsetSQL("SELECT * FROM users WHERE email='" + email + "'");
        try {
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addEventorder(String idevt, String idmodel) {
        ResultSet c = DBA.resultsetSQL("SELECT COUNT(orden) AS cuenta FROM eventorder WHERE idevento=" + idevt);
        int count = -1;
        try {
            if (c.next()) {
                count = c.getInt("cuenta");
            }
            if (count >= 0) {
                return DBA.executeSQL("INSERT INTO eventorder (idevento,idmodel,orden) VALUES (" + idevt + "," + idmodel + "," + count + ")");
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteUser(String idUser) {
        return DBA.executeSQL("DELETE FROM users WHERE iduser=" + idUser);
    }

    public ResultSet getEvents() {
        return DBA.resultsetSQL("SELECT * FROM events");
    }
    public ResultSet getEventsEnabled()
    {
        return DBA.resultsetSQL("SELECT * FROM events WHERE activated=1");
    }
    public boolean EnableEvent(String idevent,int val)
    {
        return DBA.executeSQL("UPDATE events SET activated="+val+" WHERE idevento="+idevent);
    }
    public ResultSet getModels(String eventId) {
        return DBA.resultsetSQL("SELECT * FROM models WHERE evento=" + eventId);
    }
    public ArrayList<marca> getMarcasEvent(String eventId) {
        ArrayList<marca> result=new ArrayList<marca>();
        ResultSet res=DBA.resultsetSQL("SELECT DISTINCT(marca),mname,uri FROM models,marcas  WHERE idmarca=marca AND evento=" + eventId);
        marca mc;
        try {
            while (res.next()) {
                mc=new marca(eventId, ""+res.getInt("marca"), new String(res.getBytes("mname"), "UTF-8"), new String(res.getBytes("uri"), "UTF-8"));
                result.add(mc);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public ResultSet getModel(String modelId) {
        return DBA.resultsetSQL("SELECT * FROM models WHERE idmodel=" + modelId);
    }

    public ResultSet getProducts(String eventId) {
        return DBA.resultsetSQL("SELECT * FROM products WHERE evento=" + eventId);
    }

    public ResultSet getProduct(String prodId) {
        return DBA.resultsetSQL("SELECT * FROM products WHERE idprod=" + prodId);
    }
    public ResultSet getMarcas(String eventId) {
        return DBA.resultsetSQL("SELECT * FROM marcas WHERE idevent=" + eventId);
    }
    public ResultSet getMarca(String marcaId) {
        return DBA.resultsetSQL("SELECT * FROM marcas WHERE idmarca=" + marcaId);
    }

    public ResultSet getEvent(String eventId) {
        return DBA.resultsetSQL("SELECT * FROM events WHERE idevento=" + eventId);
    }

    public boolean deleteProduct(String prodId) {
        return DBA.executeSQL("DELETE FROM products WHERE idprod=" + prodId) & DBA.executeSQL("DELETE FROM modprod WHERE idprod=" + prodId) & DBA.executeSQL("DELETE FROM stock WHERE idprod=" + prodId);
    }

    public boolean addStock(String prodId, String color, String size, int cantidad, int cantidad2) {
        return DBA.executeSQL("INSERT INTO stock (idprod,color,size,cantidad,cantidad2) VALUES (" + prodId + ",'" + color + "','" + size + "'," + cantidad + "," + cantidad2 + ")");
    }

    public ResultSet getStock(String prodId) {
        return DBA.resultsetSQL("SELECT * FROM stock WHERE idprod=" + prodId);
    }

    public ResultSet getModelsOrder(String eventId) {
        return DBA.resultsetSQL("SELECT eventorder.*, models.modelname FROM eventorder,models WHERE eventorder.idmodel=models.idmodel AND eventorder.idevento=" + eventId + " ORDER BY orden ASC");
    }

    public boolean deleteModelsOrder(String orderId, String eventId) {
        if (DBA.executeSQL("DELETE FROM eventorder WHERE idevento=" + eventId + " AND orden=" + orderId)) {
            return DBA.executeSQL("UPDATE eventorder SET orden=orden-1 WHERE idevento=" + eventId + " AND orden>" + orderId);
        } else {
            return false;
        }
    }

    public boolean deleteStock(String stockId) {
        return DBA.executeSQL("DELETE FROM stock WHERE idstock=" + stockId);
    }

    public boolean deleteProductsModel(String modId, String prodId, String btnText) {
        return DBA.executeSQL("DELETE FROM modprod WHERE idmod=" + modId + " AND idprod=" + prodId + " AND btntext='" + btnText + "'");
    }

    public boolean changePassword(String userId, String password, String newPass) {
        String nuevoPass = TripleDES.encryptB64(newPass);
        String oldPass = TripleDES.encryptB64(password);
        return DBA.executeSQL("UPDATE users SET password='" + nuevoPass + "' WHERE iduser=" + userId + " AND password='"+oldPass+"'");
    }

    public boolean saveProduct(product prod) {
        String SqlSt = null;
        if (prod.getIdProd().equals("0")) {
            SqlSt = "INSERT INTO products (evento,prodname,reference,composition,description,sizes,colors,price) VALUES (" + prod.getEvento() + ",'" + prod.getProdName() + "','" + prod.getReference() + "','" + prod.getComposition() + "','" + prod.getDescription() + "','" + prod.getSizes() + "','" + prod.getColors() + "'," + prod.getPriceDB() + ");";
            int nid = DBA.executeSQL_Id(SqlSt);
            if (nid > 0) {
                prod.setIdProd("" + nid);
                return true;
            } else {
                return false;
            }
        } else {
            SqlSt = "UPDATE products SET evento=" + prod.getEvento() + ",prodname='" + prod.getProdName() + "',reference='" + prod.getReference() + "',composition='" + prod.getComposition() + "',description='" + prod.getDescription() + "',sizes='" + prod.getSizes() + "',colors='" + prod.getColors() + "',price=" + prod.getPriceDB() + " WHERE idprod=" + prod.getIdProd();
            return DBA.executeSQL(SqlSt);
        }
    }

    public boolean saveEvent(evento evt) {
        String SqlSt = null;
        if (evt.getIdEvt().equals("0")) {
            SqlSt = "INSERT INTO events (eventname,address,phone,email,fecha) VALUES ('" + evt.getEventname() + "','" + evt.getAddress() + "','" + evt.getPhone() + "','" + evt.getEmail() + "','" + evt.getFechaDB() + "')";
            int nid = DBA.executeSQL_Id(SqlSt);
            if (nid > 0) {
                evt.setIdEvt("" + nid);
                return true;
            } else {
                return false;
            }
        } else {
            SqlSt = "UPDATE events SET eventname='" + evt.getEventname() + "', address='" + evt.getAddress() + "', phone='" + evt.getPhone() + "', email='" + evt.getEmail() + "', fecha='" + evt.getFechaDB() + "' WHERE idevento=" + evt.getIdEvt();
            return DBA.executeSQL(SqlSt);
        }

    }

    public boolean saveUser(user us) {
        String SqlSt = null;
        int publi = 0;
        if (us.isPubli()) {
            publi = 1;
        }
        if (us.getId().equals("0")) {
            SqlSt = "INSERT INTO users (email,password,username,userfname,address,CP,poblacion,provincia,pais,tlf,sexo,edad,publi,usertype) VALUES ('" + us.getEmail().toLowerCase() + "','" + TripleDES.encryptB64(us.getPwd()) + "','" + us.getUsername() + "','" + us.getUserfname() + "','" + us.getAddress() + "','" + us.getCp() + "','" + us.getPoblacion() + "','" + us.getProvincia() + "','" + us.getPais() + "','" + us.getTlf() + "','" + us.getSexo() + "','" + us.getEdad()+ "'," + publi + ",'" + us.getUsertype() + "')";
            int nid = DBA.executeSQL_Id(SqlSt);
            if (nid > 0) {
                us.setId("" + nid);
                return true;
            } else {
                return false;
            }
        } else {
            SqlSt = "UPDATE users SET username='" + us.getUsername() + "', userfname='" + us.getUserfname() + "', address='" + us.getAddress() + "', CP='" + us.getCp() + "', poblacion='" + us.getPoblacion() + "', provincia='" + us.getProvincia() + "', pais='" + us.getPais() + "', tlf='" + us.getTlf() + "', sexo='" + us.getSexo()+ "', edad='" + us.getEdad() + "', publi=" + publi + ", usertype='" + us.getUsertype() + "' WHERE iduser=" + us.getId();
            return DBA.executeSQL(SqlSt);
        }

    }

    public boolean activateUser(String usId) {

        return DBA.executeSQL("UPDATE users SET activated=1 WHERE iduser=" + usId);
    }

    public boolean saveModel(model mod) {
        String SqlSt = null;
        if (mod.getIdModel().equals("0")) {
            SqlSt = "INSERT INTO models (evento,modelname,marca) VALUES (" + mod.getIdEvento() + ",'" + mod.getModelName()+ "'," + mod.getMarca()+ ")";
            int nid = DBA.executeSQL_Id(SqlSt);
            if (nid > 0) {
                mod.setIdModel("" + nid);
                if (mod.getProducts().size() > 0) {
                    String SqlSt2 = "INSERT INTO modprod (idevent,idmod,idprod,btnorder,btntext) VALUES ";
                    String values = "";
                    product prod;
                    for (int i = 0; i < mod.getProducts().size(); i++) {
                        prod = mod.getProducts().get(i);
                        values += ",(" + mod.getIdEvento() + "," + mod.getIdModel() + "," + prod.getIdProd() + "," + i + ",'" + prod.getBtnText() + "')";
                    }
                    values = values.substring(1);
                    return DBA.executeSQL(SqlSt2 + values);
                }
                return true;
            } else {
                return false;
            }
        } else {
            SqlSt = "UPDATE models SET modelname='" + mod.getModelName() + "', marca="+mod.getMarca()+" WHERE idmodel=" + mod.getIdModel();
            if (DBA.executeSQL(SqlSt)) {
                if (DBA.executeSQL("DELETE FROM modprod WHERE idmod=" + mod.getIdModel())) {
                    if (mod.getProducts().size() > 0) {
                        String SqlSt2 = "INSERT INTO modprod (idevent,idmod,idprod,btnorder,btntext) VALUES ";
                        String values = "";
                        product prod;
                        for (int i = 0; i < mod.getProducts().size(); i++) {
                            prod = mod.getProducts().get(i);
                            values += ",(" + mod.getIdEvento() + "," + mod.getIdModel() + "," + prod.getIdProd() + "," + i + ",'" + prod.getBtnText() + "')";
                        }
                        values = values.substring(1);
                        return DBA.executeSQL(SqlSt2 + values);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }
    public boolean saveMarca(marca mar) {
        String SqlSt = null;
        if (mar.getIdMarca().equals("0")) {
            SqlSt = "INSERT INTO marcas (idevent,mname,uri) VALUES (" + mar.getIdEvento() + ",'" + mar.getMarcaName() + "','"+mar.getMarcaUri()+"')";
            int nid = DBA.executeSQL_Id(SqlSt);
            if (nid > 0) {
                mar.setIdMarca("" + nid);
                return true;
            } else {
                return false;
            }
        } else {
            SqlSt = "UPDATE marcas SET mname='" + mar.getMarcaName() + "', uri='"+mar.getMarcaUri()+"' WHERE idmarca=" + mar.getIdMarca();
            return DBA.executeSQL(SqlSt); 
        }

    }

    public ArrayList<product> getProductsModel(String modelId) {
        ArrayList<product> products = new ArrayList<product>();
        try {
            ResultSet models = DBA.resultsetSQL("SELECT * FROM modprod WHERE idmod=" + modelId + " ORDER BY btnorder ASC");
            product prod;
            while (models.next()) {
                prod = new product("" + models.getInt("idprod"), "" + models.getInt("idevent"));
                prod.setBtnText(new String(models.getBytes("btntext")));
                products.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public ArrayList<pedido> getChart(String userId, String eventId) {
        ArrayList<pedido> chart = new ArrayList<pedido>();
        try {
            ResultSet pedidos = DBA.resultsetSQL("SELECT pedidos.*,products.prodname,products.price FROM pedidos, products WHERE pedidos.idprod=products.idprod AND pedidos.idevento="+eventId+" AND iduser=" + userId);
            pedido ped;
            while (pedidos.next()) {
                ped = new pedido(pedidos.getInt("idrow"), pedidos.getInt("idprod"), new String(pedidos.getBytes("color"), "UTF-8"), new String(pedidos.getBytes("size"), "UTF-8"), new String(pedidos.getBytes("prodname"),"UTF-8"), new String(pedidos.getBytes("price"),"UTF-8"));
                chart.add(ped);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chart;
    }

    //TODO: confirmar y PAGAR el carrito, hay que traer tambien la referencia y transaccion
    public boolean confirmChart(String userId, String evtId) {
        return DBA.executeSQL("UPDATE pedidos SET confirmed=1 WHERE iduser=" + userId + " AND idevento=" + evtId);
    }

    public ArrayList<pedido> deleteChart(String userId, String idRow,String idevento) {
        DBA.executeSQL("DELETE FROM pedidos WHERE iduser=" + userId + " AND idrow=" + idRow);
        return getChart(userId,idevento);
    }

    public ArrayList<model> getAllModels(String eventId) {
        ArrayList<model> models = new ArrayList<model>();
        ArrayList<product> prods = new ArrayList<product>();
        try {
            ResultSet mods = DBA.resultsetSQL("SELECT eventorder.orden,models.evento, models.idmodel, models.modelname, models.marca, modprod.btntext, products.* FROM eventorder,models,modprod,products WHERE eventorder.idmodel=models.idmodel AND models.idmodel=modprod.idmod AND modprod.idprod=products.idprod AND models.evento=" + eventId + " ORDER BY eventorder.orden ASC, modprod.btnorder ASC");
            model mod = null;
            int cmod = 0;
            while (mods.next()) {
                if (mods.getInt("idmodel") != cmod) {
                    if (mod != null) {
                        mod.setProducts(prods);
                        models.add(mod);
                    }
                    cmod = mods.getInt("idmodel");
                    mod = new model("" + mods.getInt("evento"), "" + mods.getInt("idmodel"), mods.getString("modelname"),""+mods.getInt("marca"));
                    prods = new ArrayList<product>();
                }
                prods.add(new product("" + mods.getInt("idprod"), "" + mods.getInt("evento"), new String(mods.getBytes("prodname"), "UTF-8"), new String(mods.getBytes("reference"), "UTF-8"), new String(mods.getBytes("composition"), "UTF-8"), new String(mods.getBytes("description"), "UTF-8"), mods.getBigDecimal("price").toString().replace(".", ","), new String(mods.getBytes("sizes"), "UTF-8"), new String(mods.getBytes("colors"), "UTF-8"), new String(mods.getBytes("btntext"), "UTF-8")));
            }
            if (mod != null) {
                mod.setProducts(prods);
                models.add(mod);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return models;
    }

    public int addChart(String UserId, String idevt, String prodId, String Color, String Size) {
        int result = -1;
        try {
            String SqlSt = "INSERT INTO pedidos (iduser,idevento,idprod,color,size) VALUES (" + UserId + "," + idevt + "," + prodId + ",'" + Color + "','" + Size + "')";
            if (DBA.executeSQL(SqlSt)) {
                result = 1;
            } else {
                result = -1;
            }
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }
    
    /**
     * 
     * @param UserId
     * @param idevt
     * @param prodId
     * @param like
     * @return 
     */
    public int addLike(String UserId, String idevt, String prodId, String like) {
        int result = -1;
        boolean encontrado = false;
        try {
            //En primer lugar compruebo si ya ese producto tenía un feedback
            String SqlSt = "SELECT likestatus FROM likedislike WHERE idusuario= " + UserId + " AND idevento = " + idevt + " AND idprod = " + prodId;
            ResultSet res = DBA.resultsetSQL(SqlSt);
            if (res.next()) {
               encontrado = true;
            }
            
            //Ahora o lo inserto o lo actualizo
            if (encontrado){
                 SqlSt = "UPDATE likedislike SET likestatus = " + like +  " WHERE idusuario= " + UserId + " AND idevento = " + idevt + " AND idprod = " + prodId;
           
            } else {
               SqlSt = "INSERT INTO likedislike (idusuario,idevento,idprod,likestatus) VALUES (" + UserId + "," + idevt + "," + prodId + "," + like +  ")";
            
            }
            if (DBA.executeSQL(SqlSt)) {
                result = 1;
            } else {
                result = -1;
            }
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }
    
    /**
     * Obtiene todos los likes del evento
     * @param eventId
     * @return 
     */
    public ArrayList<likestatus> getLikeStatusEvent(String eventId) {
        ArrayList<likestatus> result=new ArrayList<likestatus>();
        ResultSet res=DBA.resultsetSQL("SELECT idusuario,idprod,likestatus FROM likedislike  WHERE idevento=" + eventId);
        likestatus lk;
        try {
            while (res.next()) {
                lk=new likestatus(eventId, ""+res.getInt("idusuario"), ""+res.getInt("idprod"),""+res.getInt("likestatus"));
                result.add(lk);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * Obtiene todos los fav del evento
     * @param eventId
     * @return 
     */
    public ArrayList<favstatus> getFavStatusEvent(String eventId) {
        ArrayList<favstatus> result=new ArrayList<favstatus>();
        ResultSet res=DBA.resultsetSQL("SELECT idusuario,idprod,fav FROM fav WHERE idevento=" + eventId);
        favstatus lk;
        try {
            while (res.next()) {
                lk=new favstatus(eventId, ""+res.getInt("idusuario"), ""+res.getInt("idprod"),""+res.getInt("fav"));
                result.add(lk);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    /**
     * 
     * @param UserId
     * @param idevt
     * @param prodId
     * @param fav
     * @return 
     */
    public int addFav(String UserId, String idevt, String prodId, String fav) {
        int result = -1;
        boolean encontrado = false;
        try {
            //En primer lugar compruebo si ya ese producto tenía un feedback
            String SqlSt = "SELECT fav FROM fav WHERE idusuario= " + UserId + " AND idevento = " + idevt + " AND idprod = " + prodId;
            ResultSet res = DBA.resultsetSQL(SqlSt);
            if (res.next()) {
               encontrado = true;
            }
            
            //Ahora o lo inserto o lo actualizo
            if (encontrado){
                 SqlSt = "UPDATE fav SET fav = " + fav +  " WHERE idusuario= " + UserId + " AND idevento = " + idevt + " AND idprod = " + prodId;
           
            } else {
               SqlSt = "INSERT INTO fav (idusuario,idevento,idprod,fav) VALUES (" + UserId + "," + idevt + "," + prodId + "," + fav +  ")";
            
            }
            if (DBA.executeSQL(SqlSt)) {
                result = 1;
            } else {
                result = -1;
            }
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    public int addPaypal(IpnInfo ipnInfo) {
        int result = -1;
        try {
            
            boolean encontrado = false;
            
            String payment_date;
            SimpleDateFormat from = new SimpleDateFormat("hh:mm:ss MMM dd, yyyy zzz");
            SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = from.parse(ipnInfo.getPaymentDate());
            payment_date = to.format(date);
            try {
                //En primer lugar compruebo si ya esa orden de compra estaba incluida en la BBDD
                String SqlSt = "SELECT txn_id FROM paypalinvoices WHERE txn_id='" + ipnInfo.getTxnId() + "'";
                ResultSet res = DBA.resultsetSQL(SqlSt);
                if (res.next()) {
                    encontrado = true;
                }
                
                //Ahora o lo inserto o lo actualizo
                if (encontrado){
                    SqlSt = "UPDATE paypalinvoices SET payment_status = '" + ipnInfo.getPaymentStatus() +  "' WHERE txn_id= '" + ipnInfo.getTxnId() +"'";
                    
                } else {
                    SqlSt = "INSERT INTO paypalinvoices (txn_id,invoice,payment_date,payer_id,payer_email,address,payment_status,address_zip,request) VALUES ('" + ipnInfo.getTxnId() + "','" + ipnInfo.getInvoiceNumber() + "','" + payment_date + "','" + ipnInfo.getPayerId() + "','" + ipnInfo.getPayerEmail() + "','" + ipnInfo.getAddress() + "','" + ipnInfo.getPaymentStatus() + "','" + ipnInfo.getZip() + "','" +ipnInfo.getRequestParams() +  "')";
                    
                }
                if (DBA.executeSQL(SqlSt)) {
                    result = 1;
                } else {
                    result = -1;
                }
            } catch (Exception e) {
                result = -1;
            }
            return result;
        } catch (ParseException ex) {
            Logger.getLogger(DataMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
