/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.portlet.tomahawk.headerresource.jetspeed;

import java.util.HashMap;

import org.apache.jetspeed.headerresource.HeaderResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class parses HTML tags provided by MyFaces's AddResource class
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 *
 */
public class HeaderHandler extends DefaultHandler
{
    private HeaderResource headerResource;

    private String elementName;

    private Attributes attrs;

    private StringBuffer text;

    public HeaderHandler(HeaderResource headerResource)
    {
        super();
        this.headerResource = headerResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if (text == null)
        {
            text = new StringBuffer();
        }
        text.append(new String(ch, start, length));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (!qName.equalsIgnoreCase("head"))
        {
            HashMap map = new HashMap();
            if (attrs.getLength() > 0)
            {
                for (int i = 0; i < attrs.getLength(); i++)
                {
                    map.put(attrs.getQName(i), attrs.getValue(i));
                }
            }

            // script tag needs </script> element
            if (elementName.equalsIgnoreCase("script") && text == null)
            {
                text = new StringBuffer("");
            }
            
            headerResource.addHeaderInfo(elementName, map, text != null ? text.toString() : null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     *      org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        if (!qName.equalsIgnoreCase("head"))
        {
            elementName = qName;
            attrs = attributes;
            text = null;
        }
    }

}
