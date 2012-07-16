package org.slc.sli.modeling.sdkgen.grammars.xsd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaElement;

import org.slc.sli.modeling.sdkgen.grammars.SdkGenGrammars;

public final class SdkGenGrammarsWrapper implements SdkGenGrammars {

    private List<XmlSchema> xmlSchemas;

    public SdkGenGrammarsWrapper(final List<XmlSchema> xmlSchemas) {
        if (xmlSchemas == null) {
            throw new NullPointerException("xmlSchema");
        }
        this.xmlSchemas = Collections.unmodifiableList(new ArrayList<XmlSchema>(xmlSchemas));
    }

    @Override
    public XmlSchemaElement getElement(final QName elementName) {
        if (elementName == null) {
            throw new NullPointerException("elementName");
        }
        for (final XmlSchema xmlSchema : xmlSchemas) {
            final XmlSchemaElement element = xmlSchema.getElementByName(elementName);
            if (element != null) {
                return element;
            }
        }
        return null;
    }
}
