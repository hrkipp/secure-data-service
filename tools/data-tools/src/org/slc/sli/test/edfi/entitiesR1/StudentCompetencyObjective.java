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


/**
 * This entity holds additional competencies for
 * 				student achievement that are not associated with specific learning
 * 				objectives (e.g., paying attention in class).
 * 			
 * 
 * <p>Java class for studentCompetencyObjective complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="studentCompetencyObjective">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="studentCompetencyObjectiveId" type="{http://slc-sli/ed-org/0.1}identificationCode" minOccurs="0"/>
 *         &lt;element name="objective" type="{http://slc-sli/ed-org/0.1}objective"/>
 *         &lt;element name="description" type="{http://slc-sli/ed-org/0.1}description" minOccurs="0"/>
 *         &lt;element name="objectiveGradeLevel" type="{http://slc-sli/ed-org/0.1}gradeLevelType"/>
 *         &lt;element name="educationOrganizationId" type="{http://slc-sli/ed-org/0.1}reference"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studentCompetencyObjective", propOrder = {
    "studentCompetencyObjectiveId",
    "objective",
    "description",
    "objectiveGradeLevel",
    "educationOrganizationId"
})
public class StudentCompetencyObjective {

    protected String studentCompetencyObjectiveId;
    @XmlElement(required = true)
    protected String objective;
    protected String description;
    @XmlElement(required = true)
    protected GradeLevelType objectiveGradeLevel;
    @XmlElement(required = true)
    protected String educationOrganizationId;

    /**
     * Gets the value of the studentCompetencyObjectiveId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentCompetencyObjectiveId() {
        return studentCompetencyObjectiveId;
    }

    /**
     * Sets the value of the studentCompetencyObjectiveId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentCompetencyObjectiveId(String value) {
        this.studentCompetencyObjectiveId = value;
    }

    /**
     * Gets the value of the objective property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Sets the value of the objective property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjective(String value) {
        this.objective = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the objectiveGradeLevel property.
     * 
     * @return
     *     possible object is
     *     {@link GradeLevelType }
     *     
     */
    public GradeLevelType getObjectiveGradeLevel() {
        return objectiveGradeLevel;
    }

    /**
     * Sets the value of the objectiveGradeLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link GradeLevelType }
     *     
     */
    public void setObjectiveGradeLevel(GradeLevelType value) {
        this.objectiveGradeLevel = value;
    }

    /**
     * Gets the value of the educationOrganizationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducationOrganizationId() {
        return educationOrganizationId;
    }

    /**
     * Sets the value of the educationOrganizationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducationOrganizationId(String value) {
        this.educationOrganizationId = value;
    }

}
