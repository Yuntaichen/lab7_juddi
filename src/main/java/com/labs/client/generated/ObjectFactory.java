
package com.labs.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.labs.client.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RowIsNotExistsException_QNAME = new QName("http://labs.com/", "RowIsNotExistsException");
    private final static QName _DeleteStudentResponse_QNAME = new QName("http://labs.com/", "deleteStudentResponse");
    private final static QName _UpdateStudentResponse_QNAME = new QName("http://labs.com/", "updateStudentResponse");
    private final static QName _UpdateStudent_QNAME = new QName("http://labs.com/", "updateStudent");
    private final static QName _CastToIntException_QNAME = new QName("http://labs.com/", "CastToIntException");
    private final static QName _FieldValueException_QNAME = new QName("http://labs.com/", "FieldValueException");
    private final static QName _CreateStudentResponse_QNAME = new QName("http://labs.com/", "createStudentResponse");
    private final static QName _EmptyFieldException_QNAME = new QName("http://labs.com/", "EmptyFieldException");
    private final static QName _GetStudentsByFields_QNAME = new QName("http://labs.com/", "getStudentsByFields");
    private final static QName _DeleteStudent_QNAME = new QName("http://labs.com/", "deleteStudent");
    private final static QName _CreateStudent_QNAME = new QName("http://labs.com/", "createStudent");
    private final static QName _GetStudentsByFieldsResponse_QNAME = new QName("http://labs.com/", "getStudentsByFieldsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.labs.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CastToIntFault }
     * 
     */
    public CastToIntFault createCastToIntFault() {
        return new CastToIntFault();
    }

    /**
     * Create an instance of {@link FieldValueFault }
     * 
     */
    public FieldValueFault createFieldValueFault() {
        return new FieldValueFault();
    }

    /**
     * Create an instance of {@link CreateStudentResponse }
     * 
     */
    public CreateStudentResponse createCreateStudentResponse() {
        return new CreateStudentResponse();
    }

    /**
     * Create an instance of {@link RowIsNotExistsFault }
     * 
     */
    public RowIsNotExistsFault createRowIsNotExistsFault() {
        return new RowIsNotExistsFault();
    }

    /**
     * Create an instance of {@link DeleteStudentResponse }
     * 
     */
    public DeleteStudentResponse createDeleteStudentResponse() {
        return new DeleteStudentResponse();
    }

    /**
     * Create an instance of {@link UpdateStudentResponse }
     * 
     */
    public UpdateStudentResponse createUpdateStudentResponse() {
        return new UpdateStudentResponse();
    }

    /**
     * Create an instance of {@link UpdateStudent }
     * 
     */
    public UpdateStudent createUpdateStudent() {
        return new UpdateStudent();
    }

    /**
     * Create an instance of {@link DeleteStudent }
     * 
     */
    public DeleteStudent createDeleteStudent() {
        return new DeleteStudent();
    }

    /**
     * Create an instance of {@link CreateStudent }
     * 
     */
    public CreateStudent createCreateStudent() {
        return new CreateStudent();
    }

    /**
     * Create an instance of {@link GetStudentsByFieldsResponse }
     * 
     */
    public GetStudentsByFieldsResponse createGetStudentsByFieldsResponse() {
        return new GetStudentsByFieldsResponse();
    }

    /**
     * Create an instance of {@link EmptyFieldFault }
     * 
     */
    public EmptyFieldFault createEmptyFieldFault() {
        return new EmptyFieldFault();
    }

    /**
     * Create an instance of {@link GetStudentsByFields }
     * 
     */
    public GetStudentsByFields createGetStudentsByFields() {
        return new GetStudentsByFields();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RowIsNotExistsFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "RowIsNotExistsException")
    public JAXBElement<RowIsNotExistsFault> createRowIsNotExistsException(RowIsNotExistsFault value) {
        return new JAXBElement<RowIsNotExistsFault>(_RowIsNotExistsException_QNAME, RowIsNotExistsFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "deleteStudentResponse")
    public JAXBElement<DeleteStudentResponse> createDeleteStudentResponse(DeleteStudentResponse value) {
        return new JAXBElement<DeleteStudentResponse>(_DeleteStudentResponse_QNAME, DeleteStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "updateStudentResponse")
    public JAXBElement<UpdateStudentResponse> createUpdateStudentResponse(UpdateStudentResponse value) {
        return new JAXBElement<UpdateStudentResponse>(_UpdateStudentResponse_QNAME, UpdateStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "updateStudent")
    public JAXBElement<UpdateStudent> createUpdateStudent(UpdateStudent value) {
        return new JAXBElement<UpdateStudent>(_UpdateStudent_QNAME, UpdateStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CastToIntFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "CastToIntException")
    public JAXBElement<CastToIntFault> createCastToIntException(CastToIntFault value) {
        return new JAXBElement<CastToIntFault>(_CastToIntException_QNAME, CastToIntFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldValueFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "FieldValueException")
    public JAXBElement<FieldValueFault> createFieldValueException(FieldValueFault value) {
        return new JAXBElement<FieldValueFault>(_FieldValueException_QNAME, FieldValueFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "createStudentResponse")
    public JAXBElement<CreateStudentResponse> createCreateStudentResponse(CreateStudentResponse value) {
        return new JAXBElement<CreateStudentResponse>(_CreateStudentResponse_QNAME, CreateStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyFieldFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "EmptyFieldException")
    public JAXBElement<EmptyFieldFault> createEmptyFieldException(EmptyFieldFault value) {
        return new JAXBElement<EmptyFieldFault>(_EmptyFieldException_QNAME, EmptyFieldFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentsByFields }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "getStudentsByFields")
    public JAXBElement<GetStudentsByFields> createGetStudentsByFields(GetStudentsByFields value) {
        return new JAXBElement<GetStudentsByFields>(_GetStudentsByFields_QNAME, GetStudentsByFields.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "deleteStudent")
    public JAXBElement<DeleteStudent> createDeleteStudent(DeleteStudent value) {
        return new JAXBElement<DeleteStudent>(_DeleteStudent_QNAME, DeleteStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateStudent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "createStudent")
    public JAXBElement<CreateStudent> createCreateStudent(CreateStudent value) {
        return new JAXBElement<CreateStudent>(_CreateStudent_QNAME, CreateStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentsByFieldsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://labs.com/", name = "getStudentsByFieldsResponse")
    public JAXBElement<GetStudentsByFieldsResponse> createGetStudentsByFieldsResponse(GetStudentsByFieldsResponse value) {
        return new JAXBElement<GetStudentsByFieldsResponse>(_GetStudentsByFieldsResponse_QNAME, GetStudentsByFieldsResponse.class, null, value);
    }

}
