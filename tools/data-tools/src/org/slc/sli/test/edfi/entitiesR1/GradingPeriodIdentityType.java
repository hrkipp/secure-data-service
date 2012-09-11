//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.31 at 10:43:34 AM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Encapsulates the possible attributes that can be used to lookup the grading period.
 * 
 * <p>Java class for gradingPeriodIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gradingPeriodIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gradingPeriod" type="{http://slc-sli/ed-org/0.1}gradingPeriodType"/>
 *         &lt;element name="schoolYear" type="{http://slc-sli/ed-org/0.1}schoolYearType"/>
 *         &lt;element name="schoolId" type="{http://slc-sli/ed-org/0.1}reference"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gradingPeriodIdentityType", propOrder = {
    "gradingPeriod",
    "schoolYear",
    "schoolId"
})
public class GradingPeriodIdentityType {

    @XmlElement(required = true)
    protected GradingPeriodType gradingPeriod;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolYear;
    @XmlElement(required = true)
    protected String schoolId;

    /**
     * Gets the value of the gradingPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link GradingPeriodType }
     *     
     */
    public GradingPeriodType getGradingPeriod() {
        return gradingPeriod;
    }

    /**
     * Sets the value of the gradingPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link GradingPeriodType }
     *     
     */
    public void setGradingPeriod(GradingPeriodType value) {
        this.gradingPeriod = value;
    }

    /**
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolYear(String value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the schoolId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolId() {
        return schoolId;
    }

    /**
     * Sets the value of the schoolId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolId(String value) {
        this.schoolId = value;
    }

}
