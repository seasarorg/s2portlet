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
package org.seasar.portlet.tomahawk.headerresource;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.portlet.PortletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * HeaderResourceFactory provides the proper HeaderResource instance by checking portal server info.
 * </p>
 * <p>
 * The portal server info and HeaderResource class are defined in ContainerInfo.properites. If you want to add a new
 * HeaderResource class, you need to add information to ContainerInfo.properties.
 * </p>
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public class HeaderResourceFactory
{
    private static final Log log = LogFactory.getLog(HeaderResourceFactory.class);

    private static String RESOURCE_BUNDLE_NAME = "org.seasar.portlet.tomahawk.headerresource.resources.ContainerInfo";

    private static ResourceBundle bundle;
    static
    {
        try
        {
            bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
        }
        catch (Exception e)
        {
            log.warn("Could not find the resource bundole: "+RESOURCE_BUNDLE_NAME, e);
        }
    }

    private static final String PORTAL_NAME_PREFIX = "headerresource.portal.";

    private static final String CLASS_NAME_PREFIX = "headerresource.class.";

    /**
     * Returns a HeaderResource instance by checking PortletContext#getServerInfo().
     * 
     * @param context portlet's context
     * @return        HeaderResource instance for each portal server
     */
    public static HeaderResource getHeaderResource(PortletContext context)
    {
        if (bundle == null)
        {
            return null;
        }

        String serverInfo = context.getServerInfo();
        for (Enumeration e = bundle.getKeys(); e.hasMoreElements();)
        {
            String key = (String) e.nextElement();
            if (key.startsWith(PORTAL_NAME_PREFIX) && serverInfo.matches(bundle.getString(key)))
            {
                String classNameKey = new String(CLASS_NAME_PREFIX + key.substring(PORTAL_NAME_PREFIX.length()));
                try
                {
                    String className=bundle.getString(classNameKey);
                    if(className==null){
                        return null;
                    }
                    Class clazz = Class.forName(bundle.getString(classNameKey));
                    if (clazz != null)
                    {
                        Object obj = clazz.newInstance();
                        if (obj instanceof HeaderResource)
                        {
                            HeaderResource headerResource = (HeaderResource) obj;
                            headerResource.setPortletContext(context);
                            headerResource.init();
                            return headerResource;
                        }
                    }
                }
                catch (InstantiationException ie)
                {
                    log.warn("Instantiation Exception: " + classNameKey, ie);
                }
                catch (IllegalAccessException iae)
                {
                    log.warn("Illegal Access Exception: " + classNameKey, iae);
                }
                catch (ClassNotFoundException cnfe)
                {
                    log.warn("Class Not Found: " + classNameKey, cnfe);
                }
            }

        }
        return null;
    }
}
