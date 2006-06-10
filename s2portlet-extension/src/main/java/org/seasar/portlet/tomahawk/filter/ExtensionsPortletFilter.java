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
package org.seasar.portlet.tomahawk.filter;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;
import org.apache.myfaces.webapp.filter.ExtensionsFilter;
import org.apache.myfaces.webapp.filter.ExtensionsResponseWrapper;
import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.portlet.tomahawk.headerresource.HeaderResource;
import org.seasar.portlet.tomahawk.headerresource.HeaderResourceFactory;
import org.seasar.portlet.tomahawk.multipart.MultipartPortletRequestWrapper;
import org.seasar.portlet.wrapper.HttpServletRequestWrapper;
import org.seasar.portlet.wrapper.HttpServletResponseWrapper;
import org.seasar.portlet.wrapper.ServletContextWrapper;

/**
 * This portlet filter supports Tomahawk's extended components, such as 
 * inputHtml and fileUpload.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public class ExtensionsPortletFilter implements PortletFilter
{
    private static final Log log = LogFactory
            .getLog(ExtensionsPortletFilter.class);

    private static final String UPLOAD_REPOSITORY_PATH = "uploadRepositoryPath";

    private static final String UPLOAD_THRESHOLD_SIZE = "uploadThresholdSize";

    private static final String UPLOAD_MAX_FILE_SIZE = "uploadMaxFileSize";

    private int uploadMaxFileSize = 100 * 1024 * 1024; // 10 MB

    private int uploadThresholdSize = 1 * 1024 * 1024; // 1 MB

    private String uploadRepositoryPath = null; // standard temp directory

    private HeaderResource headerResource;

    private PortletConfig portletConfig;

    /**
     * Called by init method of MyFacesFilterPortlet to initialize this 
     * portlet filter.
     * 
     * @param filterConfig
     * @throws PortletException
     */
    public void init(PortletFilterConfig filterConfig) throws PortletException
    {
        if (log.isTraceEnabled())
            log.trace("Initializing ExtensionsPortletFilter.");

        setPortletConfig(filterConfig.getPortletConfig());

        // HeaderResource
        headerResource = HeaderResourceFactory
                .getHeaderResource(getPortletConfig().getPortletContext());
        if (headerResource == null)
        {
            log
                    .error("headerResource is null. This portlet cannot probably put some tags into <head> element.");
        }

        // for inputFileUpload
        String param = filterConfig.getInitParameter(UPLOAD_MAX_FILE_SIZE);

        uploadMaxFileSize = resolveSize(param, uploadMaxFileSize);

        param = filterConfig.getInitParameter(UPLOAD_THRESHOLD_SIZE);

        uploadThresholdSize = resolveSize(param, uploadThresholdSize);

        uploadRepositoryPath = filterConfig
                .getInitParameter(UPLOAD_REPOSITORY_PATH);

        if (log.isTraceEnabled())
        {
            log.trace("uploadMaxFileSize=" + uploadMaxFileSize);
            log.trace("uploadThresholdSize=" + uploadThresholdSize);
            log.trace("uploadRepositoryPath=" + uploadRepositoryPath);
        }
    }

    /**
     * Called by render method of MyFacesFilterPortlet to put tags, such as 
     * &lt;style&gt;, into &lt;head&gt;.
     * 
     * @param request
     * @param response
     * @param chain PortletFilterChain instance
     * @throws PortletException
     */
    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException
    {
        if (log.isTraceEnabled())
            log.trace("called renderFilter.");

        if (headerResource == null)
        {
            // call next rednerFilter
            chain.renderFilter(request, response);
        }
        else
        {
            //            HttpServletRequest extendedRequest = new HttpServletRequestWrapper(request);
            //
            //            AddResource addResource = AddResource.getInstance(extendedRequest);
            //
            //            // call next rednerFilter
            //            chain.renderFilter(request, response);
            //
            //            if (addResource.hasHeaderBeginInfos(extendedRequest))
            //            {
            //                HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
            //                ExtensionsResponseWrapper extendedResponse = new ExtensionsResponseWrapper(
            //                        (HttpServletResponse) responseWrapper);
            //                extendedResponse.getWriter().write("<head></head>");
            //                extendedResponse.finishResponse();
            //
            //                // Some headerInfo has to be added
            //                addResource.writeWithFullHeader(extendedRequest, extendedResponse.toString(), responseWrapper);
            //
            //                headerResource.addHeaderResources(request, response, responseWrapper.getStringWriter().toString());
            //            }

            if (request.getAttribute(ExtensionsFilter.DOFILTER_CALLED) != null)
            {
                chain.renderFilter(request, response);
                return;
            }

            request.setAttribute(ExtensionsFilter.DOFILTER_CALLED, "true");

            HttpServletRequestWrapper extendedRequest = new HttpServletRequestWrapper(
                    request, getPortletConfig().getPortletContext());

            // Serve resources
            AddResource addResource = null;

            try
            {
                addResource = AddResourceFactory.getInstance(extendedRequest);
                //                if (addResource.isResourceUri(extendedRequest))
                //                {
                //                    addResource.serveResource(new ServletContextWrapper(
                //                            getPortletConfig().getPortletContext()),
                //                            extendedRequest, new HttpServletResponseWrapper(
                //                                    response));
                //                    return;
                //                }
            }
            catch (Throwable th)
            {
                log.error("Exception while retrieving addResource", th);
                throw new PortletException(th);
            }

            try
            {
                addResource.responseStarted();

                if (addResource.requiresBuffer())
                {
                    // call next rednerFilter
                    chain.renderFilter(request, response);

                    HttpServletResponseWrapper servletResponse = new HttpServletResponseWrapper(
                            response);
                    ExtensionsResponseWrapper extendedResponse = new ExtensionsResponseWrapper(
                            servletResponse);

                    if (log.isDebugEnabled())
                    {
                        log.debug("renderFilter 0 "
                                + extendedResponse.getContentType());
                    }

                    //                    // only parse HTML responses
                    //                    if (extendedResponse.getContentType() != null
                    //                            && isValidContentType(extendedResponse
                    //                                    .getContentType()))
                    //                    {
                    if (log.isDebugEnabled())
                    {
                        log.debug("renderFilter 1 "
                                + extendedResponse.toString());
                    }

                    addResource.parseResponse(extendedRequest,
                            "<html><head></head><body></body></html>",
                            servletResponse);

                    addResource.writeMyFacesJavascriptBeforeBodyEnd(
                            extendedRequest, servletResponse);

                    //                    if (!addResource.hasHeaderBeginInfos())
                    //                    {
                    //                        if (log.isDebugEnabled())
                    //                        {
                    //                            log.debug("renderFilter 11 "
                    //                                    + extendedResponse.toString());
                    //                        }
                    //                        // writes the response if no header info is needed
                    //                        addResource.writeResponse(extendedRequest,
                    //                                servletResponse);
                    //                        return;
                    //                    }

                    // Some headerInfo has to be added
                    addResource.writeWithFullHeader(extendedRequest,
                            extendedResponse);
                    addResource
                            .writeResponse(extendedRequest, extendedResponse);
                    extendedResponse.finishResponse();

                    if (log.isDebugEnabled())
                    {
                        log.debug("renderFilter 12 "
                                + extendedResponse.toString());
                    }

                    headerResource.addHeaderResources(request, response,
                            extendedResponse.toString());
                    //                    }
                    //                    else
                    //                    {
                    //                        // When not filtering due to not valid content-type, deliver the byte-array instead of a charset-converted string.
                    //                        // Otherwise a binary stream get corrupted.
                    //                        servletResponse.getOutputStream().write(
                    //                                extendedResponse.getBytes());
                    //                    }
                }
                else
                {
                    if (log.isDebugEnabled())
                    {
                        log.debug("renderFilter 3 ");
                    }

                    //                    chain.doFilter(extendedRequest, response);
                    chain.renderFilter(request, response);
                }
            }
            finally
            {
                addResource.responseFinished();
            }
        }
    }

    /**
     * Called by render method of MyFacesFilterPortlet to wrap the request when
     * it has a multipart content.
     * 
     * @param request
     * @param response
     * @param chain PortletFilterChain instance
     * @throws PortletException
     */
    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException
    {
        if (log.isTraceEnabled())
            log.trace("called processActionFilter.");

        // Check multipart/form-data
        if (PortletFileUpload.isMultipartContent(request))
        {
            if (log.isTraceEnabled())
                log.trace("ActionRequest is multipart content.");
            request = new MultipartPortletRequestWrapper(request,
                    uploadMaxFileSize, uploadThresholdSize,
                    uploadRepositoryPath);
        }

        // call next processActionFilter
        chain.processActionFilter(request, response);
    }

    /**
     * Called by destroy method of MyFacesFilterPortlet to destroy this portlet
     * filter.
     */
    public void destroy()
    {
    }

    private int resolveSize(String param, int defaultValue)
    {
        int numberParam = defaultValue;

        if (param != null)
        {
            param = param.toLowerCase();
            int factor = 1;
            String number = param;

            if (param.endsWith("g"))
            {
                factor = 1024 * 1024 * 1024;
                number = param.substring(0, param.length() - 1);
            }
            else if (param.endsWith("m"))
            {
                factor = 1024 * 1024;
                number = param.substring(0, param.length() - 1);
            }
            else if (param.endsWith("k"))
            {
                factor = 1024;
                number = param.substring(0, param.length() - 1);
            }

            numberParam = Integer.parseInt(number) * factor;
        }
        return numberParam;
    }

    /**
     * @return Returns the portletConfig.
     */
    public PortletConfig getPortletConfig()
    {
        return portletConfig;
    }

    /**
     * @param portletConfig The portletConfig to set.
     */
    public void setPortletConfig(PortletConfig portletConfig)
    {
        this.portletConfig = portletConfig;
    }

    public boolean isValidContentType(String contentType)
    {
        return contentType.startsWith("text/html")
                || contentType.startsWith("text/xml")
                || contentType.startsWith("application/xhtml+xml")
                || contentType.startsWith("application/xml");
    }

}
