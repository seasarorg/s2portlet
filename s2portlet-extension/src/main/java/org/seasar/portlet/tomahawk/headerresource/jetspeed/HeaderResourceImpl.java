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

import java.io.IOException;
import java.io.StringReader;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jetspeed.CommonPortletServices;
import org.apache.jetspeed.headerresource.HeaderResourceFactory;
import org.seasar.portlet.tomahawk.headerresource.AbstractHeaderResource;
import org.seasar.portlet.tomahawk.headerresource.HeaderResource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * HeaderResource implementation for Jetspeed2.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public class HeaderResourceImpl extends AbstractHeaderResource implements HeaderResource
{
    private static final Log log = LogFactory.getLog(HeaderResourceImpl.class);

    private HeaderResourceFactory headerResourceFactory;

    public HeaderResourceImpl()
    {
    }

    public void init()
    {

        headerResourceFactory = (HeaderResourceFactory) getPortletContext().getAttribute(
                CommonPortletServices.CPS_HEADER_RESOURCE_FACTORY);
        if (headerResourceFactory == null)
        {
            log.error("cannot create headerResourceFactory for Jetspeed.");
        }
    }

    public void addHeaderResources(PortletRequest request, PortletResponse response, String elements)
    {

        org.apache.jetspeed.headerresource.HeaderResource headerResource = headerResourceFactory
                .getHeaderResouce(request);

        SAXParserFactory spfactory = SAXParserFactory.newInstance();
        try
        {
            SAXParser parser = spfactory.newSAXParser();
            parser.parse(new InputSource(new StringReader(elements)), new HeaderHandler(headerResource));
        }
        catch (ParserConfigurationException e)
        {
            log.error("cannot add header resources.", e);
        }
        catch (SAXException e)
        {
            log.error("cannot add header resources.", e);
        }
        catch (IOException e)
        {
            log.error("cannot add header resources.", e);
        }
    }
}
