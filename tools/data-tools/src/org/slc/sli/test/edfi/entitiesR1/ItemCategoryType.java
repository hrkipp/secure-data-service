//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.17 at 02:49:01 PM EDT 
//


package org.slc.sli.test.edfi.entitiesR1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itemCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="itemCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Analytic"/>
 *     &lt;enumeration value="List Question"/>
 *     &lt;enumeration value="Math Matrix"/>
 *     &lt;enumeration value="Matching"/>
 *     &lt;enumeration value="Multiple Choice"/>
 *     &lt;enumeration value="Prose"/>
 *     &lt;enumeration value="Rubric"/>
 *     &lt;enumeration value="True-False"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "itemCategoryType")
@XmlEnum
public enum ItemCategoryType {

    @XmlEnumValue("Analytic")
    ANALYTIC("Analytic"),
    @XmlEnumValue("List Question")
    LIST_QUESTION("List Question"),
    @XmlEnumValue("Math Matrix")
    MATH_MATRIX("Math Matrix"),
    @XmlEnumValue("Matching")
    MATCHING("Matching"),
    @XmlEnumValue("Multiple Choice")
    MULTIPLE_CHOICE("Multiple Choice"),
    @XmlEnumValue("Prose")
    PROSE("Prose"),
    @XmlEnumValue("Rubric")
    RUBRIC("Rubric"),
    @XmlEnumValue("True-False")
    TRUE_FALSE("True-False");
    private final String value;

    ItemCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ItemCategoryType fromValue(String v) {
        for (ItemCategoryType c: ItemCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
