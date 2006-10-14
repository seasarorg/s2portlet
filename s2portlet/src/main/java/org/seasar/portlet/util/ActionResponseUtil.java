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

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/**
 * This class is a utility class to use ActionResponse on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class ActionResponseUtil extends PortletResponseUtil {
    public static ActionResponse getActionResponse() {
        PortletResponse portletResponse = getPortletResponse();
        if (portletResponse == null) {
            throw new IllegalStateException(
                    "The response is not PortletResponse.");
        }
        if (portletResponse instanceof ActionResponse) {
            return (ActionResponse) portletResponse;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#sendRedirect(java.lang.String)
     */
    public static void sendRedirect(String arg0) throws IOException {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.sendRedirect(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#setPortletMode(javax.portlet.PortletMode)
     */
    public static void setPortletMode(PortletMode arg0)
            throws PortletModeException {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.setPortletMode(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#setRenderParameter(java.lang.String,
     *      java.lang.String)
     */
    public static void setRenderParameter(String arg0, String arg1) {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.setRenderParameter(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#setRenderParameter(java.lang.String,
     *      java.lang.String[])
     */
    public static void setRenderParameter(String arg0, String[] arg1) {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.setRenderParameter(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#setRenderParameters(java.util.Map)
     */
    public static void setRenderParameters(Map arg0) {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.setRenderParameters(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.ActionResponse#setWindowState(javax.portlet.WindowState)
     */
    public static void setWindowState(WindowState arg0)
            throws WindowStateException {
        ActionResponse actionResponse = getActionResponse();
        if (actionResponse == null) {
            throw new IllegalStateException(
                    "The response is not ActionResponse.");
        }
        actionResponse.setWindowState(arg0);
    }

}
