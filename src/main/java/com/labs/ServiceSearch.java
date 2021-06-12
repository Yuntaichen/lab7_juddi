package com.labs;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.juddi.v3.client.UDDIConstants;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.v3_service.UDDIInquiryPortType;


public class ServiceSearch {

    public String searchAndGetService(
            UDDIInquiryPortType inquiry,
            String token,
            String serviceName) {
        try {
            return  PrintServiceInfo(GetBusinessList(inquiry, token).getBusinessInfos(), serviceName);
        } catch (Exception ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Find all of the registered businesses. This list may be filtered
     * based on access control rules
     */
    private BusinessList GetBusinessList(
            UDDIInquiryPortType inquiry,
            String token)
            throws Exception {
        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
        fb.setFindQualifiers(fq);
        Name searchName = new Name();
        searchName.setValue(UDDIConstants.WILDCARD);
        fb.getName().add(searchName);

        return inquiry.findBusiness(fb);
    }

    private String PrintServiceInfo(
            BusinessInfos businessInfos,
            String serviceName) {
        if (businessInfos == null) {
            System.out.println("No data returned");
        } else {
            for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
                // Если бизнес не содержит сервисов, то будем перехватывать
                // полученный NullPointerException
                try {
                    ServiceInfos serviceInfos = businessInfos.getBusinessInfo().get(i).getServiceInfos();

                    for (int j = 0; j < serviceInfos.getServiceInfo().size(); j++) {

                        String checkServiceName = ListToString(serviceInfos.getServiceInfo().get(j).getName());
                        if (checkServiceName.equals(serviceName)) {
                            System.out.println("Business Key: " + businessInfos.getBusinessInfo().get(i).getBusinessKey());
                            System.out.println("Owning Business Key: " + serviceInfos.getServiceInfo().get(j).getBusinessKey());
                            System.out.println("Name: " + checkServiceName);

                            // выводит идентификатор имени сервиса в списке [org.uddi.api_v3.Name@5386659f]
                            System.out.println(serviceInfos.getServiceInfo().get(j).getName());

                            String serviceKey = serviceInfos.getServiceInfo().get(j).getServiceKey();
                            System.out.println("Service Key: " + serviceKey);
                            return serviceKey;
                        }
                    }
                } catch (NullPointerException ex) {
                    Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Service doesn't exists!");
                    return null;
                }
            }
        }
        return null;
    }

    private String ListToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (Name value : name) {
            sb.append(value.getValue());
        }
//        System.out.println(sb);
        return sb.toString();
    }
}