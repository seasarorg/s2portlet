/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.portlet.filter;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.portlet.util.RequestDumpUtil;

/**
 * (From org.seasar.teeda.core.filter.RequestDumpFilter)
 * 
 * @author shinsuke
 */
public class RequestDumpFilter implements PortletFilter {

    private static final Log log = LogFactory.getLog(RequestDumpFilter.class);

    private PortletFilterConfig config = null;

    private boolean beforeRequestParameter;

    private boolean afterRequestParameter;

    private boolean beforeRequestAttribute;

    private boolean afterRequestAttribute;

    private boolean beforeCookies;

    private boolean afterCookies;

    private boolean beforeRequestHeader;

    private boolean afterRequestHeader;

    private boolean beforeSessionAttribute;

    private boolean afterSessionAttribute;

    private boolean beforeContextAttribute;

    private boolean afterContextAttribute;

    private boolean afterResponse;

    private static final String INDENT = "  ";

    private static final String LF = System.getProperty("line.separator");

    public void init(final PortletFilterConfig filterConfig)
            throws PortletException {
        this.config = filterConfig;
        beforeRequestParameter = getBooleanParameter(filterConfig,
                "beforeRequestParameter", true);
        afterRequestParameter = getBooleanParameter(filterConfig,
                "afterRequestParameter", false);
        beforeRequestAttribute = getBooleanParameter(filterConfig,
                "beforeRequestAttribute", true);
        afterRequestAttribute = getBooleanParameter(filterConfig,
                "afterRequestAttribute", true);
        beforeRequestHeader = getBooleanParameter(filterConfig,
                "beforeRequestHeader", true);
        afterRequestHeader = getBooleanParameter(filterConfig,
                "afterRequestHeader", false);
        beforeContextAttribute = getBooleanParameter(filterConfig,
                "beforeContextAttribute", true);
        afterContextAttribute = getBooleanParameter(filterConfig,
                "afterContextAttribute", true);
        beforeCookies = getBooleanParameter(filterConfig, "beforeCookies", true);
        afterCookies = getBooleanParameter(filterConfig, "afterCookies", true);
        beforeSessionAttribute = getBooleanParameter(filterConfig,
                "beforeSessionAttribute", true);
        afterSessionAttribute = getBooleanParameter(filterConfig,
                "afterSessionAttribute", true);
        afterResponse = getBooleanParameter(filterConfig, "afterResponse", true);

        final StringBuffer sb = new StringBuffer();
        RequestDumpUtil.dumpContextProperties(sb, filterConfig
                .getPortletConfig().getPortletContext(), LF, INDENT);
        log.debug(sb.toString());
    }

    public void destroy() {
        config = null;
    }

    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws IOException, PortletException {
        if (config == null) {
            return;
        }
        dumpBefore(request);
        try {
            chain.processActionFilter(request, response);
        } finally {
            dumpAfter(request, response);
        }
    }
    public void renderFilter(RenderRequest request,
            RenderResponse response, PortletFilterChain chain)
            throws IOException, PortletException {
        if (config == null) {
            return;
        }
        dumpBefore(request);
        try {
            chain.renderFilter(request, response);
        } finally {
            dumpAfter(request, response);
        }
    }

    private void dumpBefore(PortletRequest request) {
        final PortletContext context = config.getPortletConfig()
                .getPortletContext();
        final StringBuffer sb = new StringBuffer();
        sb.append(LF);
        sb.append(LF);
        sb.append("** before *****************************************: ");
        sb.append(gerContextPath(request));
        sb.append(LF);
        RequestDumpUtil.dumpRequestProperties(sb, request, LF, INDENT);
        RequestDumpUtil.dumpSessionProperties(sb, request, LF, INDENT);
        if (beforeRequestParameter) {
            RequestDumpUtil.dumpRequestParameters(sb, request, LF, INDENT);
        }
        if (beforeRequestAttribute) {
            RequestDumpUtil.dumpRequestAttributes(sb, request, LF, INDENT);
        }
        if (beforeCookies) {
            RequestDumpUtil.dumpCookies(sb, request, LF, INDENT);
        }
        if (beforeRequestHeader) {
            RequestDumpUtil.dumpRequestHeaders(sb, request, LF, INDENT);
        }
        if (beforeSessionAttribute) {
            RequestDumpUtil.dumpSessionAttributes(sb, request, LF, INDENT);
        }
        if (beforeContextAttribute) {
            RequestDumpUtil.dumpContextAttributes(sb, context, LF, INDENT);
        }
        log.debug(sb.toString());
    }

    private void dumpAfter(PortletRequest request, PortletResponse response) {
        final StringBuffer sb = new StringBuffer();
        sb.append(LF);
        sb.append(LF);
        sb.append("** after *****************************************: ");
        sb.append(gerContextPath(request));
        sb.append(LF);
        if (afterResponse) {
            RequestDumpUtil.dumpResponseProperties(sb, response, LF, INDENT);
        }
        if (afterRequestParameter) {
            RequestDumpUtil.dumpRequestParameters(sb, request, LF, INDENT);
        }
        if (afterRequestAttribute) {
            RequestDumpUtil.dumpRequestAttributes(sb, request, LF, INDENT);
        }
        if (afterCookies) {
            RequestDumpUtil.dumpCookies(sb, request, LF, INDENT);
        }
        if (afterRequestHeader) {
            RequestDumpUtil.dumpRequestHeaders(sb, request, LF, INDENT);
        }
        if (afterSessionAttribute) {
            RequestDumpUtil.dumpSessionAttributes(sb, request, LF, INDENT);
        }
        if (afterContextAttribute) {
            RequestDumpUtil.dumpContextAttributes(sb, config.getPortletConfig()
                    .getPortletContext(), LF, INDENT);
        }
        log.debug(sb.toString());
    }

    private String gerContextPath(final PortletRequest request) {
        return request.getContextPath();
    }

    public String toString() {
        if (config == null) {
            return ("RequestDumpFilter()");
        }
        final StringBuffer sb = new StringBuffer("RequestDumpFilter(");
        sb.append(config);
        sb.append(")");
        return (sb.toString());
    }

    private boolean getBooleanParameter(final PortletFilterConfig filterConfig,
            final String name, final boolean defaultValue) {
        final String value = filterConfig.getInitParameter(name);
        if (value == null) {
            return defaultValue;
        }
        return BooleanConversionUtil.toPrimitiveBoolean(value);
    }

}
