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
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

/**
 * This class is a utility class to use RenderResponse on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class RenderResponseUtil extends PortletResponseUtil {
    public static RenderResponse getRenderResponse() {
        PortletResponse portletResponse = getPortletResponse();
        if (portletResponse == null) {
            throw new IllegalStateException(
                    "The response is not PortletResponse.");
        }
        if (portletResponse instanceof RenderResponse) {
            return (RenderResponse) portletResponse;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#createActionURL()
     */
    public static PortletURL createActionURL() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.createActionURL();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#createRenderURL()
     */
    public static PortletURL createRenderURL() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.createRenderURL();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#flushBuffer()
     */
    public static void flushBuffer() throws IOException {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.flushBuffer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getBufferSize()
     */
    public static int getBufferSize() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getBufferSize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getCharacterEncoding()
     */
    public static String getCharacterEncoding() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getCharacterEncoding();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getContentType()
     */
    public static String getContentType() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getContentType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getLocale()
     */
    public static Locale getLocale() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getLocale();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getNamespace()
     */
    public static String getNamespace() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getNamespace();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getPortletOutputStream()
     */
    public static OutputStream getPortletOutputStream() throws IOException {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getPortletOutputStream();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#getWriter()
     */
    public static PrintWriter getWriter() throws IOException {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.getWriter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#isCommitted()
     */
    public static boolean isCommitted() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        return renderResponse.isCommitted();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#reset()
     */
    public static void reset() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#resetBuffer()
     */
    public static void resetBuffer() {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.resetBuffer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#setBufferSize(int)
     */
    public static void setBufferSize(int arg0) {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.setBufferSize(arg0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#setContentType(java.lang.String)
     */
    public static void setContentType(String arg0) {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.setContentType(arg0);

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.portlet.RenderResponse#setTitle(java.lang.String)
     */
    public static void setTitle(String arg0) {
        RenderResponse renderResponse = getRenderResponse();
        if (renderResponse == null) {
            throw new IllegalStateException(
                    "The response is not RenderResponse.");
        }
        renderResponse.setTitle(arg0);
    }

}
