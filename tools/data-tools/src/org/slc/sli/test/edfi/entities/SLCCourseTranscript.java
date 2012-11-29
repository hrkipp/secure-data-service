//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.11.16 at 01:39:34 PM EST
//


package org.slc.sli.test.edfi.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity is the final record of a student's performance in their courses at the end of semester or school year.
 *
 * <p>Java class for SLC-CourseTranscript complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SLC-CourseTranscript">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ed-fi.org/0100}ComplexObjectType">
 *       &lt;sequence>
 *         &lt;element name="CourseAttemptResult" type="{http://ed-fi.org/0100}CourseAttemptResultType"/>
 *         &lt;element name="CreditsAttempted" type="{http://ed-fi.org/0100}Credits" minOccurs="0"/>
 *         &lt;element name="CreditsEarned" type="{http://ed-fi.org/0100}Credits"/>
 *         &lt;element name="AdditionalCreditsEarned" type="{http://ed-fi.org/0100}AdditionalCredits" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="GradeLevelWhenTaken" type="{http://ed-fi.org/0100}GradeLevelType" minOccurs="0"/>
 *         &lt;element name="MethodCreditEarned" type="{http://ed-fi.org/0100}MethodCreditEarnedType" minOccurs="0"/>
 *         &lt;element name="FinalLetterGradeEarned" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ed-fi.org/0100}GradeEarned">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FinalNumericGradeEarned" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CourseRepeatCode" type="{http://ed-fi.org/0100}CourseRepeatCodeType" minOccurs="0"/>
 *         &lt;element name="CourseReference" type="{http://ed-fi.org/0100}SLC-CourseReferenceType"/>
 *         &lt;element name="EducationOrganizationReference" type="{http://ed-fi.org/0100}EducationalOrgReferenceType" maxOccurs="unbounded"/>
 *         &lt;element name="StudentAcademicRecordReference" type="{http://ed-fi.org/0100}SLC-StudentAcademicRecordReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLC-CourseTranscript", propOrder = {
    "courseAttemptResult",
    "creditsAttempted",
    "creditsEarned",
    "additionalCreditsEarned",
    "gradeLevelWhenTaken",
    "methodCreditEarned",
    "finalLetterGradeEarned",
    "finalNumericGradeEarned",
    "courseRepeatCode",
    "courseReference",
    "educationOrganizationReference",
    "studentAcademicRecordReference"
})

@XmlRootElement(name = "CourseTranscript")
public class SLCCourseTranscript
    extends ComplexObjectType
{

    @XmlElement(name = "CourseAttemptResult", required = true)
    protected CourseAttemptResultType courseAttemptResult;
    @XmlElement(name = "CreditsAttempted")
    protected Credits creditsAttempted;
    @XmlElement(name = "CreditsEarned", required = true)
    protected Credits creditsEarned;
    @XmlElement(name = "AdditionalCreditsEarned")
    protected List<AdditionalCredits> additionalCreditsEarned;
    @XmlElement(name = "GradeLevelWhenTaken")
    protected GradeLevelType gradeLevelWhenTaken;
    @XmlElement(name = "MethodCreditEarned")
    protected MethodCreditEarnedType methodCreditEarned;
    @XmlElement(name = "FinalLetterGradeEarned")
    protected String finalLetterGradeEarned;
    @XmlElement(name = "FinalNumericGradeEarned")
    protected BigInteger finalNumericGradeEarned;
    @XmlElement(name = "CourseRepeatCode")
    protected CourseRepeatCodeType courseRepeatCode;
    @XmlElement(name = "CourseReference", required = true)
    protected SLCCourseReferenceType courseReference;
    @XmlElement(name = "EducationOrganizationReference", required = true)
    protected List<EducationalOrgReferenceType> educationOrganizationReference;
    @XmlElement(name = "StudentAcademicRecordReference", required = true)
    protected SLCStudentAcademicRecordReferenceType studentAcademicRecordReference;

    /**
     * Gets the value of the courseAttemptResult property.
     *
     * @return
     *     possible object is
     *     {@link CourseAttemptResultType }
     *
     */
    public CourseAttemptResultType getCourseAttemptResult() {
        return courseAttemptResult;
    }

    /**
     * Sets the value of the courseAttemptResult property.
     *
     * @param value
     *     allowed object is
     *     {@link CourseAttemptResultType }
     *
     */
    public void setCourseAttemptResult(CourseAttemptResultType value) {
        this.courseAttemptResult = value;
    }

    /**
     * Gets the value of the creditsAttempted property.
     *
     * @return
     *     possible object is
     *     {@link Credits }
     *
     */
    public Credits getCreditsAttempted() {
        return creditsAttempted;
    }

    /**
     * Sets the value of the creditsAttempted property.
     *
     * @param value
     *     allowed object is
     *     {@link Credits }
     *
     */
    public void setCreditsAttempted(Credits value) {
        this.creditsAttempted = value;
    }

    /**
     * Gets the value of the creditsEarned property.
     *
     * @return
     *     possible object is
     *     {@link Credits }
     *
     */
    public Credits getCreditsEarned() {
        return creditsEarned;
    }

    /**
     * Sets the value of the creditsEarned property.
     *
     * @param value
     *     allowed object is
     *     {@link Credits }
     *
     */
    public void setCreditsEarned(Credits value) {
        this.creditsEarned = value;
    }

    /**
     * Gets the value of the additionalCreditsEarned property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalCreditsEarned property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalCreditsEarned().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalCredits }
     *
     *
     */
    public List<AdditionalCredits> getAdditionalCreditsEarned() {
        if (additionalCreditsEarned == null) {
            additionalCreditsEarned = new ArrayList<AdditionalCredits>();
        }
        return this.additionalCreditsEarned;
    }

    /**
     * Gets the value of the gradeLevelWhenTaken property.
     *
     * @return
     *     possible object is
     *     {@link GradeLevelType }
     *
     */
    public GradeLevelType getGradeLevelWhenTaken() {
        return gradeLevelWhenTaken;
    }

    /**
     * Sets the value of the gradeLevelWhenTaken property.
     *
     * @param value
     *     allowed object is
     *     {@link GradeLevelType }
     *
     */
    public void setGradeLevelWhenTaken(GradeLevelType value) {
        this.gradeLevelWhenTaken = value;
    }

    /**
     * Gets the value of the methodCreditEarned property.
     *
     * @return
     *     possible object is
     *     {@link MethodCreditEarnedType }
     *
     */
    public MethodCreditEarnedType getMethodCreditEarned() {
        return methodCreditEarned;
    }

    /**
     * Sets the value of the methodCreditEarned property.
     *
     * @param value
     *     allowed object is
     *     {@link MethodCreditEarnedType }
     *
     */
    public void setMethodCreditEarned(MethodCreditEarnedType value) {
        this.methodCreditEarned = value;
    }

    /**
     * Gets the value of the finalLetterGradeEarned property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFinalLetterGradeEarned() {
        return finalLetterGradeEarned;
    }

    /**
     * Sets the value of the finalLetterGradeEarned property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFinalLetterGradeEarned(String value) {
        this.finalLetterGradeEarned = value;
    }

    /**
     * Gets the value of the finalNumericGradeEarned property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getFinalNumericGradeEarned() {
        return finalNumericGradeEarned;
    }

    /**
     * Sets the value of the finalNumericGradeEarned property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setFinalNumericGradeEarned(BigInteger value) {
        this.finalNumericGradeEarned = value;
    }

    /**
     * Gets the value of the courseRepeatCode property.
     *
     * @return
     *     possible object is
     *     {@link CourseRepeatCodeType }
     *
     */
    public CourseRepeatCodeType getCourseRepeatCode() {
        return courseRepeatCode;
    }

    /**
     * Sets the value of the courseRepeatCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CourseRepeatCodeType }
     *
     */
    public void setCourseRepeatCode(CourseRepeatCodeType value) {
        this.courseRepeatCode = value;
    }

    /**
     * Gets the value of the courseReference property.
     *
     * @return
     *     possible object is
     *     {@link SLCCourseReferenceType }
     *
     */
    public SLCCourseReferenceType getCourseReference() {
        return courseReference;
    }

    /**
     * Sets the value of the courseReference property.
     *
     * @param value
     *     allowed object is
     *     {@link SLCCourseReferenceType }
     *
     */
    public void setCourseReference(SLCCourseReferenceType value) {
        this.courseReference = value;
    }

    /**
     * Gets the value of the educationOrganizationReference property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the educationOrganizationReference property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEducationOrganizationReference().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EducationalOrgReferenceType }
     *
     *
     */
    public List<EducationalOrgReferenceType> getEducationOrganizationReference() {
        if (educationOrganizationReference == null) {
            educationOrganizationReference = new ArrayList<EducationalOrgReferenceType>();
        }
        return this.educationOrganizationReference;
    }

    /**
     * Gets the value of the studentAcademicRecordReference property.
     *
     * @return
     *     possible object is
     *     {@link SLCStudentAcademicRecordReferenceType }
     *
     */
    public SLCStudentAcademicRecordReferenceType getStudentAcademicRecordReference() {
        return studentAcademicRecordReference;
    }

    /**
     * Sets the value of the studentAcademicRecordReference property.
     *
     * @param value
     *     allowed object is
     *     {@link SLCStudentAcademicRecordReferenceType }
     *
     */
    public void setStudentAcademicRecordReference(SLCStudentAcademicRecordReferenceType value) {
        this.studentAcademicRecordReference = value;
    }

}
