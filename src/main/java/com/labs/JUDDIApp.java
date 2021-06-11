package com.labs;


import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.DiscardAuthToken;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JUDDIApp {
    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;

    /**
     * Main entry point
     */
    public static void main(String[] args) {

        String service = "CRUDService";
        String userName = "uddiadmin";
        String userPass = "da_password1";

        JUDDIApp sp = new JUDDIApp();
        String token = sp.getUDDIToken(userName, userPass);

        ServiceSearch ss = new ServiceSearch();

        String serviceKey = ss.searchAndGetService(inquiry, token, service);

        System.out.println(security);
        System.out.println(inquiry);
        System.out.println(token);

        System.out.println(serviceKey);

        try {
            security.discardAuthToken(new DiscardAuthToken(token));
        } catch (RemoteException ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JUDDIApp() {
        try {
            // create UDDIClient and proxy to config
            UDDIClient client = new UDDIClient("META-INF/service_search.xml");
            // a UDDIClient can be a client to multiple UDDI nodes, so
            // supply the nodeName defined in service_search.xml.
            // The transport also is defined in the service_search.xml.
            Transport transport = client.getTransport("default");
            // Now, create a reference to the UDDI API
            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUDDIToken(String jUDDIUserName, String jUDDIUSerPass) {
        String token = null;
        // Get auth token (appends credentials to the ws proxies)
        GetAuthToken getAuthTokenRoot = new GetAuthToken();
        getAuthTokenRoot.setUserID(jUDDIUserName);
        getAuthTokenRoot.setCred(jUDDIUSerPass);
        // Making API call that retrieves the authentication token for the user.
        try {
            AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
            token =  rootAuthToken.getAuthInfo();
        } catch (RemoteException ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return token;
    }

}