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

import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * This class is a utility class to use PortletRequest on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletRequestUtil
{
    public static PortletRequest getPortletRequest()
    {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container == null)
        {
            throw new IllegalStateException("S2Container is null.");
        }

        if (container.getExternalContext().getRequest() instanceof PortletRequest)
        {
            return (PortletRequest) container.getExternalContext().getRequest();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getAttribute(java.lang.String)
     */
    public static Object getAttribute(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }
        return portletRequest.getAttribute(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getAttributeNames()
     */
    public static Enumeration getAttributeNames()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getAttributeNames();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getAuthType()
     */
    public static String getAuthType()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getAuthType();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getContextPath()
     */
    public static String getContextPath()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getContextPath();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getLocale()
     */
    public static Locale getLocale()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getLocale();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getLocales()
     */
    public static Enumeration getLocales()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getLocales();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getParameter(java.lang.String)
     */
    public static String getParameter(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getParameter(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getParameterMap()
     */
    public static Map getParameterMap()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getParameterMap();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getParameterNames()
     */
    public static Enumeration getParameterNames()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getParameterNames();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getParameterValues(java.lang.String)
     */
    public static String[] getParameterValues(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getParameterValues(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPortalContext()
     */
    public static PortalContext getPortalContext()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPortalContext();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPortletMode()
     */
    public static PortletMode getPortletMode()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPortletMode();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPortletSession()
     */
    public static PortletSession getPortletSession()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPortletSession();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPortletSession(boolean)
     */
    public static PortletSession getPortletSession(boolean arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPortletSession(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPreferences()
     */
    public static PortletPreferences getPreferences()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPreferences();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getProperties(java.lang.String)
     */
    public static Enumeration getProperties(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getProperties(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getProperty(java.lang.String)
     */
    public static String getProperty(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getProperty(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getPropertyNames()
     */
    public static Enumeration getPropertyNames()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getPropertyNames();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getRemoteUser()
     */
    public static String getRemoteUser()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getRemoteUser();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getRequestedSessionId()
     */
    public static String getRequestedSessionId()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getRequestedSessionId();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getResponseContentType()
     */
    public static String getResponseContentType()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getResponseContentType();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getResponseContentTypes()
     */
    public static Enumeration getResponseContentTypes()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getResponseContentTypes();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getScheme()
     */
    public static String getScheme()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getScheme();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getServerName()
     */
    public static String getServerName()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getServerName();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getServerPort()
     */
    public static int getServerPort()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getServerPort();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getUserPrincipal()
     */
    public static Principal getUserPrincipal()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getUserPrincipal();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#getWindowState()
     */
    public static WindowState getWindowState()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.getWindowState();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#isPortletModeAllowed(javax.portlet.PortletMode)
     */
    public static boolean isPortletModeAllowed(PortletMode arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.isPortletModeAllowed(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#isRequestedSessionIdValid()
     */
    public static boolean isRequestedSessionIdValid()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.isRequestedSessionIdValid();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#isSecure()
     */
    public static boolean isSecure()
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.isSecure();
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#isUserInRole(java.lang.String)
     */
    public static boolean isUserInRole(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.isUserInRole(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#isWindowStateAllowed(javax.portlet.WindowState)
     */
    public static boolean isWindowStateAllowed(WindowState arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        return portletRequest.isWindowStateAllowed(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#removeAttribute(java.lang.String)
     */
    public static void removeAttribute(String arg0)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        portletRequest.removeAttribute(arg0);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletRequest#setAttribute(java.lang.String, java.lang.Object)
     */
    public static void setAttribute(String arg0, Object arg1)
    {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null)
        {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }

        portletRequest.setAttribute(arg0, arg1);
    }
}
