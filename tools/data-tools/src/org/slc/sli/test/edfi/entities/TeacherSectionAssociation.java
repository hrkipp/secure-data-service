//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.17 at 01:12:00 PM EDT 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This association indicates the class sections to which a teacher is assigned to.
 * 
 * 
 * <p>Java class for TeacherSectionAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TeacherSectionAssociation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TeacherReference" type="{http://ed-fi.org/0100}StaffReferenceType"/>
 *         &lt;element name="SectionReference" type="{http://ed-fi.org/0100}SectionReferenceType"/>
 *         &lt;element name="ClassroomPosition" type="{http://ed-fi.org/0100}ClassroomPositionType"/>
 *         &lt;element name="BeginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="HighlyQualifiedTeacher" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeacherSectionAssociation", propOrder = {
    "teacherReference",
    "sectionReference",
    "classroomPosition",
    "beginDate",
    "endDate",
    "highlyQualifiedTeacher"
})
public class TeacherSectionAssociation {

    @XmlElement(name = "TeacherReference", required = true)
    protected StaffReferenceType teacherReference;
    @XmlElement(name = "SectionReference", required = true)
    protected SectionReferenceType sectionReference;
    @XmlElement(name = "ClassroomPosition", required = true)
    protected ClassroomPositionType classroomPosition;
    @XmlElement(name = "BeginDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String beginDate;
    @XmlElement(name = "EndDate")
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected String endDate;
    @XmlElement(name = "HighlyQualifiedTeacher")
    protected Boolean highlyQualifiedTeacher;

    /**
     * Gets the value of the teacherReference property.
     * 
     * @return
     *     possible object is
     *     {@link StaffReferenceType }
     *     
     */
    public StaffReferenceType getTeacherReference() {
        return teacherReference;
    }

    /**
     * Sets the value of the teacherReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffReferenceType }
     *     
     */
    public void setTeacherReference(StaffReferenceType value) {
        this.teacherReference = value;
    }

    /**
     * Gets the value of the sectionReference property.
     * 
     * @return
     *     possible object is
     *     {@link SectionReferenceType }
     *     
     */
    public SectionReferenceType getSectionReference() {
        return sectionReference;
    }

    /**
     * Sets the value of the sectionReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SectionReferenceType }
     *     
     */
    public void setSectionReference(SectionReferenceType value) {
        this.sectionReference = value;
    }

    /**
     * Gets the value of the classroomPosition property.
     * 
     * @return
     *     possible object is
     *     {@link ClassroomPositionType }
     *     
     */
    public ClassroomPositionType getClassroomPosition() {
        return classroomPosition;
    }

    /**
     * Sets the value of the classroomPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassroomPositionType }
     *     
     */
    public void setClassroomPosition(ClassroomPositionType value) {
        this.classroomPosition = value;
    }

    /**
     * Gets the value of the beginDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the beginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the highlyQualifiedTeacher property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHighlyQualifiedTeacher() {
        return highlyQualifiedTeacher;
    }

    /**
     * Sets the value of the highlyQualifiedTeacher property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHighlyQualifiedTeacher(Boolean value) {
        this.highlyQualifiedTeacher = value;
    }

}
