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
 * <p>Java class for programType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="programType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Adult/Continuing Education"/>
 *     &lt;enumeration value="Alternative Education"/>
 *     &lt;enumeration value="Athletics"/>
 *     &lt;enumeration value="Bilingual"/>
 *     &lt;enumeration value="Bilingual Summer"/>
 *     &lt;enumeration value="Career and Technical Education"/>
 *     &lt;enumeration value="Cocurricular Programs"/>
 *     &lt;enumeration value="College Preparatory"/>
 *     &lt;enumeration value="Community Service Program"/>
 *     &lt;enumeration value="Community/Junior College Education Program"/>
 *     &lt;enumeration value="Compensatory Services for Disadvantaged Students"/>
 *     &lt;enumeration value="Counseling Services"/>
 *     &lt;enumeration value="District-Funded GED"/>
 *     &lt;enumeration value="English as a Second Language (ESL)"/>
 *     &lt;enumeration value="Even Start"/>
 *     &lt;enumeration value="Expelled Education"/>
 *     &lt;enumeration value="Extended Day/Child Care Services"/>
 *     &lt;enumeration value="Gifted and Talented"/>
 *     &lt;enumeration value="Head Start"/>
 *     &lt;enumeration value="Health Services Program"/>
 *     &lt;enumeration value="High School Equivalency Program (HSEP)"/>
 *     &lt;enumeration value="IDEA"/>
 *     &lt;enumeration value="Immigrant Education"/>
 *     &lt;enumeration value="Independent Study"/>
 *     &lt;enumeration value="Indian Education"/>
 *     &lt;enumeration value="International Baccalaureate"/>
 *     &lt;enumeration value="Library/Media Services Program"/>
 *     &lt;enumeration value="Magnet/Special Program Emphasis"/>
 *     &lt;enumeration value="Migrant Education"/>
 *     &lt;enumeration value="Neglected and Delinquent Program"/>
 *     &lt;enumeration value="Optional Flexible School Day Program (OFSDP)"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Regular Education"/>
 *     &lt;enumeration value="Remedial Education"/>
 *     &lt;enumeration value="Section 504 Placement"/>
 *     &lt;enumeration value="Service Learning"/>
 *     &lt;enumeration value="Special Education"/>
 *     &lt;enumeration value="Student Retention/Dropout Prevention"/>
 *     &lt;enumeration value="Substance Abuse Education/Prevention"/>
 *     &lt;enumeration value="Teacher Professional Development/Mentoring"/>
 *     &lt;enumeration value="Technical Preparatory"/>
 *     &lt;enumeration value="Title I Part A"/>
 *     &lt;enumeration value="Vocational Education"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "programType")
@XmlEnum
public enum ProgramType {

    @XmlEnumValue("Adult/Continuing Education")
    ADULT_CONTINUING_EDUCATION("Adult/Continuing Education"),
    @XmlEnumValue("Alternative Education")
    ALTERNATIVE_EDUCATION("Alternative Education"),
    @XmlEnumValue("Athletics")
    ATHLETICS("Athletics"),
    @XmlEnumValue("Bilingual")
    BILINGUAL("Bilingual"),
    @XmlEnumValue("Bilingual Summer")
    BILINGUAL_SUMMER("Bilingual Summer"),
    @XmlEnumValue("Career and Technical Education")
    CAREER_AND_TECHNICAL_EDUCATION("Career and Technical Education"),
    @XmlEnumValue("Cocurricular Programs")
    COCURRICULAR_PROGRAMS("Cocurricular Programs"),
    @XmlEnumValue("College Preparatory")
    COLLEGE_PREPARATORY("College Preparatory"),
    @XmlEnumValue("Community Service Program")
    COMMUNITY_SERVICE_PROGRAM("Community Service Program"),
    @XmlEnumValue("Community/Junior College Education Program")
    COMMUNITY_JUNIOR_COLLEGE_EDUCATION_PROGRAM("Community/Junior College Education Program"),
    @XmlEnumValue("Compensatory Services for Disadvantaged Students")
    COMPENSATORY_SERVICES_FOR_DISADVANTAGED_STUDENTS("Compensatory Services for Disadvantaged Students"),
    @XmlEnumValue("Counseling Services")
    COUNSELING_SERVICES("Counseling Services"),
    @XmlEnumValue("District-Funded GED")
    DISTRICT_FUNDED_GED("District-Funded GED"),
    @XmlEnumValue("English as a Second Language (ESL)")
    ENGLISH_AS_A_SECOND_LANGUAGE_ESL("English as a Second Language (ESL)"),
    @XmlEnumValue("Even Start")
    EVEN_START("Even Start"),
    @XmlEnumValue("Expelled Education")
    EXPELLED_EDUCATION("Expelled Education"),
    @XmlEnumValue("Extended Day/Child Care Services")
    EXTENDED_DAY_CHILD_CARE_SERVICES("Extended Day/Child Care Services"),
    @XmlEnumValue("Gifted and Talented")
    GIFTED_AND_TALENTED("Gifted and Talented"),
    @XmlEnumValue("Head Start")
    HEAD_START("Head Start"),
    @XmlEnumValue("Health Services Program")
    HEALTH_SERVICES_PROGRAM("Health Services Program"),
    @XmlEnumValue("High School Equivalency Program (HSEP)")
    HIGH_SCHOOL_EQUIVALENCY_PROGRAM_HSEP("High School Equivalency Program (HSEP)"),
    IDEA("IDEA"),
    @XmlEnumValue("Immigrant Education")
    IMMIGRANT_EDUCATION("Immigrant Education"),
    @XmlEnumValue("Independent Study")
    INDEPENDENT_STUDY("Independent Study"),
    @XmlEnumValue("Indian Education")
    INDIAN_EDUCATION("Indian Education"),
    @XmlEnumValue("International Baccalaureate")
    INTERNATIONAL_BACCALAUREATE("International Baccalaureate"),
    @XmlEnumValue("Library/Media Services Program")
    LIBRARY_MEDIA_SERVICES_PROGRAM("Library/Media Services Program"),
    @XmlEnumValue("Magnet/Special Program Emphasis")
    MAGNET_SPECIAL_PROGRAM_EMPHASIS("Magnet/Special Program Emphasis"),
    @XmlEnumValue("Migrant Education")
    MIGRANT_EDUCATION("Migrant Education"),
    @XmlEnumValue("Neglected and Delinquent Program")
    NEGLECTED_AND_DELINQUENT_PROGRAM("Neglected and Delinquent Program"),
    @XmlEnumValue("Optional Flexible School Day Program (OFSDP)")
    OPTIONAL_FLEXIBLE_SCHOOL_DAY_PROGRAM_OFSDP("Optional Flexible School Day Program (OFSDP)"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Regular Education")
    REGULAR_EDUCATION("Regular Education"),
    @XmlEnumValue("Remedial Education")
    REMEDIAL_EDUCATION("Remedial Education"),
    @XmlEnumValue("Section 504 Placement")
    SECTION_504_PLACEMENT("Section 504 Placement"),
    @XmlEnumValue("Service Learning")
    SERVICE_LEARNING("Service Learning"),
    @XmlEnumValue("Special Education")
    SPECIAL_EDUCATION("Special Education"),
    @XmlEnumValue("Student Retention/Dropout Prevention")
    STUDENT_RETENTION_DROPOUT_PREVENTION("Student Retention/Dropout Prevention"),
    @XmlEnumValue("Substance Abuse Education/Prevention")
    SUBSTANCE_ABUSE_EDUCATION_PREVENTION("Substance Abuse Education/Prevention"),
    @XmlEnumValue("Teacher Professional Development/Mentoring")
    TEACHER_PROFESSIONAL_DEVELOPMENT_MENTORING("Teacher Professional Development/Mentoring"),
    @XmlEnumValue("Technical Preparatory")
    TECHNICAL_PREPARATORY("Technical Preparatory"),
    @XmlEnumValue("Title I Part A")
    TITLE_I_PART_A("Title I Part A"),
    @XmlEnumValue("Vocational Education")
    VOCATIONAL_EDUCATION("Vocational Education");
    private final String value;

    ProgramType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProgramType fromValue(String v) {
        for (ProgramType c: ProgramType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
