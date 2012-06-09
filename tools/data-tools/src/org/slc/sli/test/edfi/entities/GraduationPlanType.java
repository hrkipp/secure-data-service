//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.31 at 09:35:49 AM EDT 
//


package org.slc.sli.test.edfi.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GraduationPlanType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GraduationPlanType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Career and Technical Education"/>
 *     &lt;enumeration value="Distinguished"/>
 *     &lt;enumeration value="Minimum"/>
 *     &lt;enumeration value="Recommended"/>
 *     &lt;enumeration value="Standard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GraduationPlanType")
@XmlEnum
public enum GraduationPlanType {

    @XmlEnumValue("Career and Technical Education")
    CAREER_AND_TECHNICAL_EDUCATION("Career and Technical Education"),
    @XmlEnumValue("Distinguished")
    DISTINGUISHED("Distinguished"),
    @XmlEnumValue("Minimum")
    MINIMUM("Minimum"),
    @XmlEnumValue("Recommended")
    RECOMMENDED("Recommended"),
    @XmlEnumValue("Standard")
    STANDARD("Standard");
    private final String value;

    GraduationPlanType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GraduationPlanType fromValue(String v) {
        for (GraduationPlanType c: GraduationPlanType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
