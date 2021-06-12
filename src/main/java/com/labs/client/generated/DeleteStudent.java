
package com.labs.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteStudent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteStudent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteStudent", propOrder = {
    "rowId"
})
public class DeleteStudent {

    protected String rowId;

    /**
     * Gets the value of the rowId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * Sets the value of the rowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowId(String value) {
        this.rowId = value;
    }

}
