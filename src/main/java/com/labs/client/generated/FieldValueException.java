
package com.labs.client.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "FieldValueException", targetNamespace = "http://labs.com/")
public class FieldValueException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FieldValueFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public FieldValueException(String message, FieldValueFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public FieldValueException(String message, FieldValueFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.labs.client.generated.FieldValueFault
     */
    public FieldValueFault getFaultInfo() {
        return faultInfo;
    }

}
