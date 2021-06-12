package com.labs;


import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JUDDIApp {
    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;
    private static UDDIPublicationPortType publish = null;

    /**
     * Main entry point
     */
    public static void main(String[] args) {

        String searchServiceName = "CRUDService";
        String userName = "uddiadmin";
        String userPass = "da_password1";

        // Create UDDIClient and proxy to config, add references to UDDI API
        JUDDIApp sp = new JUDDIApp();
        // Get Auth token as String
        String token = sp.getUDDIToken(userName, userPass);

        // Register new service (for jUDDI v.3.0 and higher)
        String businessName ="Brand New Business";
        String registeredServiceName ="CRUDService";
        String registeredServiceURL ="http://localhost:8080/CRUDService?wsdl";
        sp.registerNewService(token, businessName, registeredServiceName, registeredServiceURL);

        // Search service
        ServiceSearch ss = new ServiceSearch();
        String serviceKey = ss.searchAndGetService(inquiry, token, searchServiceName);

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
            publish = transport.getUDDIPublishService();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUDDIToken(String jUDDIUserName, String jUDDIUSerPass) {
        String token = null;
        // Get auth token (appends credentials to the ws proxies)
        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID(jUDDIUserName);
        getAuthToken.setCred(jUDDIUSerPass);
        // Making API call that retrieves the authentication token for the user.
        try {
            AuthToken authToken = security.getAuthToken(getAuthToken);
            token =  authToken.getAuthInfo();
        } catch (RemoteException ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

    private void registerNewService(
            String token,
            String businessName,
            String registeredServiceName,
            String registeredServiceURL) {
        // Creating the parent business entity that will contain our service.
        BusinessEntity myBusEntity = new BusinessEntity();
        Name myBusName = new Name();
        myBusName.setValue(businessName);
        myBusEntity.getName().add(myBusName);

        // Adding the business entity to the "save" structure, using our
        // publisher's authentication info and saving away.
        SaveBusiness sb = new SaveBusiness();
        sb.getBusinessEntity().add(myBusEntity);
        sb.setAuthInfo(token);

        try {
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
            System.out.println("myBusiness key:  " + myBusKey);

            // Creating a service to save.
            // Only adding the minimum data: the parent business key retrieved
            // from saving the business
            // above and a single name.
            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(registeredServiceName);
            myService.getName().add(myServName);

            // Add binding templates, etc...
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(registeredServiceURL);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            // optional but recommended step, this annotations our binding with all
            // the standard SOAP tModel instance infos
            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

            myService.setBindingTemplates(myBindingTemplates);

            // Adding the service to the "save" structure, using our publisher's
            // authentication info and saving away.
            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(token);
            ServiceDetail sd = publish.saveService(ss);
            String myServKey = sd.getBusinessService().get(0).getServiceKey();
            System.out.println("myService key:  " + myServKey);

            // Now you have published a business and service via
            // the jUDDI API!
            System.out.println("New service successfully registered!");

        } catch (RemoteException ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}