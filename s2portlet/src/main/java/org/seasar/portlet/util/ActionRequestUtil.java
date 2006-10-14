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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

/**
 * This class is a utility class to use ActionRequest on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class ActionRequestUtil extends PortletRequestUtil {
    public static ActionRequest getActionRequest() {
        PortletRequest portletRequest = getPortletRequest();
        if (portletRequest == null) {
            throw new IllegalStateException(
                    "The request is not PortletRequest.");
        }
        if (portletRequest instanceof ActionRequest) {
            return (ActionRequest) portletRequest;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#getCharacterEncoding()
     */
    public static String getCharacterEncoding() {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        return actionRequest.getCharacterEncoding();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#getContentLength()
     */
    public static int getContentLength() {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        return actionRequest.getContentLength();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#getContentType()
     */
    public static String getContentType() {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        return actionRequest.getContentType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#getPortletInputStream()
     */
    public static InputStream getPortletInputStream() throws IOException {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        return actionRequest.getPortletInputStream();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#getReader()
     */
    public static BufferedReader getReader()
            throws UnsupportedEncodingException, IOException {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        return actionRequest.getReader();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionRequest#setCharacterEncoding(java.lang.String)
     */
    public static void setCharacterEncoding(String arg0)
            throws UnsupportedEncodingException {
        ActionRequest actionRequest = getActionRequest();
        if (actionRequest == null) {
            throw new IllegalStateException("The request is not ActionRequest.");
        }

        actionRequest.setCharacterEncoding(arg0);
    }
}
