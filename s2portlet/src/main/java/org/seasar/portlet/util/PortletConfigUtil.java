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
package org.seasar.portlet.util;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.impl.portlet.PortletExternalContext;

/**
 * This class is a utility class to use PortletConfig on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletConfigUtil
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(PortletConfigUtil.class);

    public static PortletConfig getPortletConfig()
    {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container == null)
        {
            throw new IllegalStateException("S2Container is null.");
        }
        PortletConfig portletConfig = null;
        if (container.getExternalContext() instanceof PortletExternalContext)
        {
            portletConfig = (PortletConfig) ((PortletExternalContext) container
                    .getExternalContext()).getConfig();
        }
        else
        {
            throw new IllegalStateException(
                    "ExternalContext is not PortletExternalContext.");
        }
        if (portletConfig != null)
        {
            return portletConfig;
        }
        return null;
    }

    /**
     * Returns the name of the portlet.
     * <P>
     * The name may be provided via server administration, assigned in the
     * portlet application deployment descriptor with the <code>portlet-name</code>
     * tag.
     *
     * @return   the portlet name
     */
    public static String getPortletName()
    {
        PortletConfig portletConfig = getPortletConfig();
        if (portletConfig == null)
        {
            throw new IllegalStateException("Cannot retrieve PortletConfig.");
        }
        return portletConfig.getPortletName();
    }

    /**
     * Returns the <code>PortletContext</code> of the portlet application 
     * the portlet is in.
     *
     * @return   a <code>PortletContext</code> object, used by the 
     *           caller to interact with its portlet container
     *
     * @see PortletContext
     */
    public static PortletContext getPortletContext()
    {
        PortletConfig portletConfig = getPortletConfig();
        if (portletConfig == null)
        {
            throw new IllegalStateException("Cannot retrieve PortletConfig.");
        }
        return portletConfig.getPortletContext();
    }

    /**
     * Gets the resource bundle for the given locale based on the
     * resource bundle defined in the deployment descriptor
     * with <code>resource-bundle</code> tag or the inlined resources
     * defined in the deployment descriptor.
     *
     * @param    locale    the locale for which to retrieve the resource bundle
     * 
     * @return   the resource bundle for the given locale
     *
     */
    public static java.util.ResourceBundle getResourceBundle(
            java.util.Locale locale)
    {
        PortletConfig portletConfig = getPortletConfig();
        if (portletConfig == null)
        {
            throw new IllegalStateException("Cannot retrieve PortletConfig.");
        }
        return portletConfig.getResourceBundle(locale);
    }

    /**
     * Returns a String containing the value of the named initialization parameter, 
     * or null if the parameter does not exist.
     *
     * @param name  a <code>String</code> specifying the name
     *          of the initialization parameter
     *
     * @return      a <code>String</code> containing the value 
     *          of the initialization parameter
     *
     * @exception   java.lang.IllegalArgumentException  
     *                      if name is <code>null</code>.
     */
    public static String getInitParameter(java.lang.String name)
    {
        PortletConfig portletConfig = getPortletConfig();
        if (portletConfig == null)
        {
            throw new IllegalStateException("Cannot retrieve PortletConfig.");
        }
        return portletConfig.getInitParameter(name);
    }

    /**
     * Returns the names of the portlet initialization parameters as an 
     * <code>Enumeration</code> of String objects, or an empty <code>Enumeration</code> if the 
     * portlet has no initialization parameters.    
     *
     * @return      an <code>Enumeration</code> of <code>String</code> 
     *          objects containing the names of the portlet 
     *          initialization parameters, or an empty <code>Enumeration</code> if the 
     *                    portlet has no initialization parameters. 
     */
    public static java.util.Enumeration getInitParameterNames()
    {
        PortletConfig portletConfig = getPortletConfig();
        if (portletConfig == null)
        {
            throw new IllegalStateException("Cannot retrieve PortletConfig.");
        }
        return portletConfig.getInitParameterNames();
    }
}
