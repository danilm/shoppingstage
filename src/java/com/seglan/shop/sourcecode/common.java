/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.sourcecode;

import com.google.gson.Gson;
import com.seglan.shop.entities.evento;
import com.seglan.shop.entities.favstatus;
import com.seglan.shop.entities.likestatus;
import com.seglan.shop.entities.marca;
import com.seglan.shop.entities.model;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Date;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;

/**
 *
 * @author Iñaki Garaizabal
 */
public class common {

    //private static String cevento, cpase;
    public static DataSource dataSource = null;
    public static ArrayList<evento> events = new ArrayList<evento>();
    private static ArrayList<model> allmodels = null;
    private static HashMap<String, String> evtmodels = new HashMap<String, String>();
    private static HashMap<String, String> evtmarcas = new HashMap<String, String>();
    private static HashMap<String, String> evtlike = new HashMap<String, String>();
    private static HashMap<String, String> evtfav = new HashMap<String, String>();
    public static HashMap<String, String> currentstate = new HashMap<String, String>();

    public static boolean saveCurrentState(String evento, String pase) {
        boolean result = true;
        if (pase.equals("-1")||pase.equals("-2")) {
            int val=Integer.parseInt(pase)+2;
            java.sql.Connection conn = getConnection();
            DataMethods DBM = new DataMethods(conn);
            boolean res = DBM.EnableEvent(evento,val);
            CloseConnection(conn);
            pase="0";
        }
        if (currentstate == null) {
            currentstate = new HashMap<String, String>();
        }
        currentstate.put(evento, pase);
        return true;
    }

    public static String getEventModelsJSON(String evento) {
        if (evtmodels.containsKey(evento)) {
            return evtmodels.get(evento);
        } else {
            String serialized = "";
            ArrayList<model> models = common.getAllModels(evento);
            Gson gson = new Gson();
            try {
                serialized = new String(gson.toJson(models).getBytes(), "UTF-8");
                serialized = formatTextWeb(serialized);
                evtmodels.put(evento, serialized);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return serialized;
        }
    }

    public static String getMarcasJSON(String evento) {
        if (evtmarcas.containsKey(evento)) {
            return evtmarcas.get(evento);
        } else {
            String serialized = "";
            java.sql.Connection conn = getConnection();
            DataMethods DBM = new DataMethods(conn);
            ArrayList<marca> marcas = DBM.getMarcasEvent(evento);
            CloseConnection(conn);

            Gson gson = new Gson();
            try {
                serialized = new String(gson.toJson(marcas).getBytes(), "UTF-8");
                serialized = formatTextWeb(serialized);
                evtmarcas.put(evento, serialized);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return serialized;
        }
    }

    /*public static String getCurrentEvent(ServletContext ctx) {
     if (cevento == null) {
     readCurrentState(ctx);
     }
     return cevento;
     }*/
    public static String getCurrentPase(String evt) {
        if (currentstate == null) {
            currentstate = new HashMap<String, String>();
        }
        if (currentstate.containsKey(evt)) {
            return currentstate.get(evt);
        } else {
            currentstate.put(evt, "0");
            return "0";
        }
    }

    public static ArrayList<model> getAllModels(String evtId) {
        java.sql.Connection conn = getConnection();
        DataMethods DBM = new DataMethods(conn);
        allmodels = DBM.getAllModels(evtId);
        CloseConnection(conn);
        return allmodels;
    }

    public static java.sql.Connection getConnection() {
        ConnectionManager cm = new ConnectionManager();
        return cm.connection;
    }

    public static void CloseConnection(java.sql.Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void CloseStatement(ResultSet resultset) {
        try {
            resultset.getStatement().close();
            resultset.close();
        } catch (Exception e) {
        }
    }

    public static String formatNumber(double number, int decimalPositions) {
        String expression = "#0";
        if (number > 999) {
            expression = "#,000";
        }
        if (decimalPositions > 0) {
            expression += ".";
            for (int i = 0; i < decimalPositions; i++) {
                expression += "0";
            }
        }
        NumberFormat formatter = new DecimalFormat(expression);
        return formatter.format(number);
    }

    public static String currentDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String currentTime() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static double formatDouble(String Value) {
        return Double.valueOf(Value.replace(',', '.'));
    }

    public static String formatTextWeb(String txt) {
        txt = txt.replace("á", "&aacute;");
        txt = txt.replace("é", "&eacute;");
        txt = txt.replace("í", "&iacute;");
        txt = txt.replace("ó", "&oacute;");
        txt = txt.replace("ú", "&uacute;");
        txt = txt.replace("Á", "&Aacute;");
        txt = txt.replace("É", "&Eacute;");
        txt = txt.replace("Í", "&Iacute;");
        txt = txt.replace("Ó", "&Oacute;");
        txt = txt.replace("Ú", "&Uacute;");
        txt = txt.replace("Ñ", "&Ntilde;");
        txt = txt.replace("ñ", "&ntilde;");
        return txt;
    }
    public static String formatTextUnicode(String txt) {
        txt = txt.replace("á", "\\u00e1");
        txt = txt.replace("é", "\\u00e9");
        txt = txt.replace("í", "\\u00ed");
        txt = txt.replace("ó", "\\u00f3");
        txt = txt.replace("ú", "\\u00fa");
        txt = txt.replace("Á", "\\u00c1");
        txt = txt.replace("É", "\\u00c9");
        txt = txt.replace("Í", "\\u00cd");
        txt = txt.replace("Ó", "\\u00d3");
        txt = txt.replace("Ú", "\\u00da");
        txt = txt.replace("Ñ", "\\u00d1");
        txt = txt.replace("ñ", "\\u00f1");
        return txt;
    }

    public static String dateToString(Date date) {
        String Day = String.valueOf(date.getDay());
        if (Day.length() < 2) {
            Day = "0" + Day;
        }
        String Month = String.valueOf(date.getMonth());
        if (Month.length() < 2) {
            Month = "0" + Month;
        }
        return Day + "/" + Month + "/" + date.getYear();
    }
    public static final String[] COUNTRIES = new String[]{
        "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
        "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
        "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
        "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
        "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
        "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
        "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
        "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
        "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
        "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
        "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
        "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
        "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
        "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
        "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
        "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
        "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
        "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
        "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
        "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
        "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
        "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
        "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
        "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
        "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
        "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
        "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
        "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
        "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
        "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
        "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
        "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
        "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
        "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
        "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
        "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda",
        "Ukraine", "United Arab Emirates", "United Kingdom",
        "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
        "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara",
        "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
    };

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    
    public static String getLikeStatusJSON(String evento) {
        
            String serialized = "";
            java.sql.Connection conn = getConnection();
            DataMethods DBM = new DataMethods(conn);
            ArrayList<likestatus> likestatus = DBM.getLikeStatusEvent(evento);
            CloseConnection(conn);

            Gson gson = new Gson();
            try {
                serialized = new String(gson.toJson(likestatus).getBytes(), "UTF-8");
                serialized = formatTextWeb(serialized);
                evtlike.put(evento, serialized);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return serialized;
        
    }
    
    public static String getFavStatusJSON(String evento) {
        
            String serialized = "";
            java.sql.Connection conn = getConnection();
            DataMethods DBM = new DataMethods(conn);
            ArrayList<favstatus> favstatus = DBM.getFavStatusEvent(evento);
            CloseConnection(conn);

            Gson gson = new Gson();
            try {
                serialized = new String(gson.toJson(favstatus).getBytes(), "UTF-8");
                serialized = formatTextWeb(serialized);
                evtfav.put(evento, serialized);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return serialized;
        
    }
}
