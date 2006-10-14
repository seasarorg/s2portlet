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
package org.seasar.portlet.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class is a dummy HttpServletRequest.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class HttpServletRequestWrapper implements HttpServletRequest,
        PortletRequest {
    PortletRequest portletRequest;

    ActionRequest actionRequest;

    PortletContext portletContext;

    public HttpServletRequestWrapper(PortletRequest portletRequest,
            PortletContext portletContext) {
        this.actionRequest = null;
        this.portletRequest = portletRequest;
        this.portletContext = portletContext;
    }

    public HttpServletRequestWrapper(ActionRequest actionRequest,
            PortletContext portletContext) {
        this.actionRequest = actionRequest;
        this.portletRequest = actionRequest;
        this.portletContext = portletContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getAuthType()
     */
    public String getAuthType() {
        return portletRequest.getAuthType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getContextPath()
     */
    public String getContextPath() {
        return portletRequest.getContextPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getCookies()
     */
    public Cookie[] getCookies() {
        // TODO
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
     */
    public long getDateHeader(String arg0) {
        // TODO Portlet API does not have this method
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
     */
    public String getHeader(String arg0) {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
     */
    public Enumeration getHeaderNames() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
     */
    public Enumeration getHeaders(String arg0) {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
     */
    public int getIntHeader(String arg0) {
        // TODO Portlet API does not have this method
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getMethod()
     */
    public String getMethod() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getPathInfo()
     */
    public String getPathInfo() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
     */
    public String getPathTranslated() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getQueryString()
     */
    public String getQueryString() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
     */
    public String getRemoteUser() {
        return portletRequest.getRemoteUser();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
     */
    public String getRequestedSessionId() {
        return portletRequest.getRequestedSessionId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getRequestURI()
     */
    public String getRequestURI() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getRequestURL()
     */
    public StringBuffer getRequestURL() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getServletPath()
     */
    public String getServletPath() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getSession()
     */
    public HttpSession getSession() {
        return new HttpSessionWrapper(portletRequest.getPortletSession(),
                portletContext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
     */
    public HttpSession getSession(boolean arg0) {
        return new HttpSessionWrapper(portletRequest.getPortletSession(arg0),
                portletContext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
     */
    public Principal getUserPrincipal() {
        return portletRequest.getUserPrincipal();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
     */
    public boolean isRequestedSessionIdFromCookie() {
        // TODO Portlet API does not have this method
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
     */
    public boolean isRequestedSessionIdFromUrl() {
        // TODO Portlet API does not have this method
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
     */
    public boolean isRequestedSessionIdFromURL() {
        // TODO Portlet API does not have this method
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
     */
    public boolean isRequestedSessionIdValid() {
        // TODO Portlet API does not have this method
        return portletRequest.isRequestedSessionIdValid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
     */
    public boolean isUserInRole(String arg0) {
        return portletRequest.isUserInRole(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
     */
    public Object getAttribute(String arg0) {
        return portletRequest.getAttribute(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getAttributeNames()
     */
    public Enumeration getAttributeNames() {
        return portletRequest.getAttributeNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getCharacterEncoding()
     */
    public String getCharacterEncoding() {
        if (actionRequest != null) {
            return actionRequest.getCharacterEncoding();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getContentLength()
     */
    public int getContentLength() {
        if (actionRequest != null) {
            return actionRequest.getContentLength();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getContentType()
     */
    public String getContentType() {
        if (actionRequest != null) {
            return actionRequest.getContentType();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getInputStream()
     */
    public ServletInputStream getInputStream() throws IOException {
        // TODO Portlet API does not have this method
        if (actionRequest != null) {
            return new ServletInputStreamWrapper(actionRequest
                    .getPortletInputStream());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getLocale()
     */
    public Locale getLocale() {
        return portletRequest.getLocale();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getLocales()
     */
    public Enumeration getLocales() {
        return portletRequest.getLocales();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
     */
    public String getParameter(String arg0) {
        return portletRequest.getParameter(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getParameterMap()
     */
    public Map getParameterMap() {
        return portletRequest.getParameterMap();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getParameterNames()
     */
    public Enumeration getParameterNames() {
        return portletRequest.getParameterNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
     */
    public String[] getParameterValues(String arg0) {
        return portletRequest.getParameterValues(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getProtocol()
     */
    public String getProtocol() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getReader()
     */
    public BufferedReader getReader() throws IOException {
        if (actionRequest != null) {
            return actionRequest.getReader();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
     */
    public String getRealPath(String arg0) {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getRemoteAddr()
     */
    public String getRemoteAddr() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getRemoteHost()
     */
    public String getRemoteHost() {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
     */
    public RequestDispatcher getRequestDispatcher(String arg0) {
        // TODO Portlet API does not have this method
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getScheme()
     */
    public String getScheme() {
        return portletRequest.getScheme();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getServerName()
     */
    public String getServerName() {
        return portletRequest.getServerName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#getServerPort()
     */
    public int getServerPort() {
        return portletRequest.getServerPort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#isSecure()
     */
    public boolean isSecure() {
        return portletRequest.isSecure();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String arg0) {
        portletRequest.removeAttribute(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
     *      java.lang.Object)
     */
    public void setAttribute(String arg0, Object arg1) {
        portletRequest.setAttribute(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
     */
    public void setCharacterEncoding(String arg0)
            throws UnsupportedEncodingException {
        if (actionRequest != null) {
            actionRequest.setCharacterEncoding(arg0);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPortalContext()
     */
    public PortalContext getPortalContext() {
        return portletRequest.getPortalContext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPortletMode()
     */
    public PortletMode getPortletMode() {
        return portletRequest.getPortletMode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPortletSession()
     */
    public PortletSession getPortletSession() {
        return portletRequest.getPortletSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPortletSession(boolean)
     */
    public PortletSession getPortletSession(boolean arg0) {
        return portletRequest.getPortletSession(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPreferences()
     */
    public PortletPreferences getPreferences() {
        return portletRequest.getPreferences();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getProperties(java.lang.String)
     */
    public Enumeration getProperties(String arg0) {
        return portletRequest.getProperties(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getProperty(java.lang.String)
     */
    public String getProperty(String arg0) {
        return portletRequest.getProperty(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getPropertyNames()
     */
    public Enumeration getPropertyNames() {
        return portletRequest.getPropertyNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getResponseContentType()
     */
    public String getResponseContentType() {
        return portletRequest.getResponseContentType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getResponseContentTypes()
     */
    public Enumeration getResponseContentTypes() {
        return portletRequest.getResponseContentTypes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#getWindowState()
     */
    public WindowState getWindowState() {
        return portletRequest.getWindowState();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#isPortletModeAllowed(javax.portlet.PortletMode)
     */
    public boolean isPortletModeAllowed(PortletMode arg0) {
        return portletRequest.isPortletModeAllowed(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletRequest#isWindowStateAllowed(javax.portlet.WindowState)
     */
    public boolean isWindowStateAllowed(WindowState arg0) {
        return portletRequest.isWindowStateAllowed(arg0);
    }

    public int getRemotePort() {
        // TODO Portlet API does not have this method
        return 0;
    }

    public String getLocalName() {
        // TODO Portlet API does not have this method
        return null;
    }

    public String getLocalAddr() {
        // TODO Portlet API does not have this method
        return null;
    }

    public int getLocalPort() {
        // TODO Portlet API does not have this method
        return 0;
    }

}
