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

import javax.portlet.PortletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * This class is a utility class to use PortletResponse on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletResponseUtil {
    public static PortletResponse getPortletResponse() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container == null) {
            throw new IllegalStateException("S2Container is null.");
        }

        if (container.getExternalContext().getResponse() instanceof PortletResponse) {
            return (PortletResponse) container.getExternalContext()
                    .getResponse();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletResponse#addProperty(java.lang.String,
     *      java.lang.String)
     */
    public static void addProperty(String arg0, String arg1) {
        PortletResponse portletResponse = getPortletResponse();
        if (portletResponse == null) {
            throw new IllegalStateException(
                    "The response is not PortletResponse.");
        }
        portletResponse.addProperty(arg0, arg1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletResponse#encodeURL(java.lang.String)
     */
    public static String encodeURL(String arg0) {
        PortletResponse portletResponse = getPortletResponse();
        if (portletResponse == null) {
            throw new IllegalStateException(
                    "The response is not PortletResponse.");
        }
        return portletResponse.encodeURL(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.PortletResponse#setProperty(java.lang.String,
     *      java.lang.String)
     */
    public static void setProperty(String arg0, String arg1) {
        PortletResponse portletResponse = getPortletResponse();
        if (portletResponse == null) {
            throw new IllegalStateException(
                    "The response is not PortletResponse.");
        }
        portletResponse.setProperty(arg0, arg1);
    }

}
