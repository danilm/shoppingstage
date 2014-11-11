/**
 * Paypal Button and Instant Payment Notification (IPN) Integration with Java
 * http://codeoftheday.blogspot.com/2013/07/paypal-button-and-instant-payment_6.html
 */
package com.seglan.shop.paypal.PaypalIpn;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;

/**
 * Arbitrary service class to simulate the storage and retrieval of Paypal IPN Notification related information
 *
 * User: smhumayun
 * Date: 7/6/13
 * Time: 6:23 PM
 */
public class IpnInfoService {

    /**
     * Store Paypal IPN Notification related information for future use
     *
     * @param ipnInfo {@link IpnInfo}
     * @throws IpnException
     */
    public int log (final IpnInfo ipnInfo) throws IpnException {
        /**
         * Save data to DDBB...
         */
        java.sql.Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        int result = DBM.addPaypal(ipnInfo);
        return result;
    }

    /**
     * Fetch Paypal IPN Notification related information saved earlier
     *
     * @param txnId Paypal IPN Notification's Transaction ID
     * @return {@link IpnInfo}
     * @throws IpnException
     */
    public IpnInfo getIpnInfo (final String txnId) throws IpnException {
        IpnInfo ipnInfo = null;
        /**
         * Implementation...
         */
        return ipnInfo;
    }

}
