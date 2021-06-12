
package com.labs.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createStudent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createStudent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="studentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="studentSurname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="studentAge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="studentMark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createStudent", propOrder = {
    "studentName",
    "studentSurname",
    "studentAge",
    "studentId",
    "studentMark"
})
public class CreateStudent {

    protected String studentName;
    protected String studentSurname;
    protected String studentAge;
    protected String studentId;
    protected String studentMark;

    /**
     * Gets the value of the studentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the value of the studentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentName(String value) {
        this.studentName = value;
    }

    /**
     * Gets the value of the studentSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentSurname() {
        return studentSurname;
    }

    /**
     * Sets the value of the studentSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentSurname(String value) {
        this.studentSurname = value;
    }

    /**
     * Gets the value of the studentAge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentAge() {
        return studentAge;
    }

    /**
     * Sets the value of the studentAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentAge(String value) {
        this.studentAge = value;
    }

    /**
     * Gets the value of the studentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the value of the studentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentId(String value) {
        this.studentId = value;
    }

    /**
     * Gets the value of the studentMark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentMark() {
        return studentMark;
    }

    /**
     * Sets the value of the studentMark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentMark(String value) {
        this.studentMark = value;
    }

}
