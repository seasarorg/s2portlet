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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import javax.portlet.PortletResponse;
import javax.portlet.RenderResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is a dummy HttpServletResponse.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 *
 */
public class HttpServletResponseWrapper implements HttpServletResponse,
        PortletResponse
{
    /**
     * Dummy writer to store elements, such as &lt;script&gt; and &lt;link&gt;.
     */
    private Writer writer;

    private PortletResponse portletResponse;

    private RenderResponse renderResponse;

    public HttpServletResponseWrapper(PortletResponse portletResponse)
    {
        this.renderResponse = null;
        this.portletResponse = portletResponse;
        writer = new StringWriter();
    }

    public HttpServletResponseWrapper(RenderResponse renderResponse)
    {
        this.renderResponse = renderResponse;
        this.portletResponse = renderResponse;
        writer = new StringWriter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    public PrintWriter getWriter() throws IOException
    {
        return new PrintWriter(writer);
    }

    /**
     * Returns writer to which MyFaces' AddResource stores elements.
     * 
     * @return writer which has elements, such as &lt;script&gt; and &lt;link&gt;
     */
    public StringWriter getStringWriter()
    {
        return (StringWriter) writer;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#flushBuffer()
     */
    public void flushBuffer() throws IOException
    {
        if (renderResponse != null)
        {
            renderResponse.flushBuffer();
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getBufferSize()
     */
    public int getBufferSize()
    {
        if (renderResponse != null)
        {
            return renderResponse.getBufferSize();
        }
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getCharacterEncoding()
     */
    public String getCharacterEncoding()
    {
        if (renderResponse != null)
        {
            return renderResponse.getCharacterEncoding();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getLocale()
     */
    public Locale getLocale()
    {
        if (renderResponse != null)
        {
            return renderResponse.getLocale();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getOutputStream()
     */
    public ServletOutputStream getOutputStream() throws IOException
    {
        if (renderResponse != null)
        {
            return new ServletOutputStreamWrapper(renderResponse
                    .getPortletOutputStream());
        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#isCommitted()
     */
    public boolean isCommitted()
    {
        if (renderResponse != null)
        {
            return renderResponse.isCommitted();
        }
        return false;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#reset()
     */
    public void reset()
    {
        if (renderResponse != null)
        {
            renderResponse.reset();
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#resetBuffer()
     */
    public void resetBuffer()
    {
        if (renderResponse != null)
        {
            renderResponse.resetBuffer();
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setBufferSize(int)
     */
    public void setBufferSize(int arg0)
    {
        // TODO Portlet API does not have this method
        if (renderResponse != null)
        {
            renderResponse.setBufferSize(arg0);
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setContentLength(int)
     */
    public void setContentLength(int arg0)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
     */
    public void setContentType(String arg0)
    {
        if (renderResponse != null)
        {
            renderResponse.setContentType(arg0);
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
     */
    public void setLocale(Locale arg0)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addCookie(javax.servlet.http.Cookie)
     */
    public void addCookie(Cookie arg0)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addDateHeader(java.lang.String, long)
     */
    public void addDateHeader(String arg0, long arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addHeader(java.lang.String, java.lang.String)
     */
    public void addHeader(String arg0, String arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#addIntHeader(java.lang.String, int)
     */
    public void addIntHeader(String arg0, int arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#containsHeader(java.lang.String)
     */
    public boolean containsHeader(String arg0)
    {
        // TODO Portlet API does not have this method
        return false;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
     */
    public String encodeRedirectUrl(String arg0)
    {
        // TODO Portlet API does not have this method
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
     */
    public String encodeRedirectURL(String arg0)
    {
        // TODO Portlet API does not have this method
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
     */
    public String encodeUrl(String arg0)
    {
        if (renderResponse != null)
        {
            return renderResponse.encodeURL(arg0);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
     */
    public String encodeURL(String arg0)
    {
        return portletResponse.encodeURL(arg0);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendError(int, java.lang.String)
     */
    public void sendError(int arg0, String arg1) throws IOException
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendError(int)
     */
    public void sendError(int arg0) throws IOException
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#sendRedirect(java.lang.String)
     */
    public void sendRedirect(String arg0) throws IOException
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setDateHeader(java.lang.String, long)
     */
    public void setDateHeader(String arg0, long arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setHeader(java.lang.String, java.lang.String)
     */
    public void setHeader(String arg0, String arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setIntHeader(java.lang.String, int)
     */
    public void setIntHeader(String arg0, int arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setStatus(int, java.lang.String)
     */
    public void setStatus(int arg0, String arg1)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServletResponse#setStatus(int)
     */
    public void setStatus(int arg0)
    {
        // TODO Portlet API does not have this method

    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletResponse#addProperty(java.lang.String, java.lang.String)
     */
    public void addProperty(String arg0, String arg1)
    {
        portletResponse.addProperty(arg0, arg1);
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletResponse#setProperty(java.lang.String, java.lang.String)
     */
    public void setProperty(String arg0, String arg1)
    {
        portletResponse.setProperty(arg0, arg1);
    }

    public String getContentType()
    {
        if (renderResponse != null)
        {
            return renderResponse.getContentType();
        }
        return null;
    }

    public void setCharacterEncoding(String arg0)
    {
        // TODO Portlet API does not have this method

    }

}
