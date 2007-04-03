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
package org.seasar.portlet.filter;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.external.portlet.PortletExternalContext;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.servlet.PortletExtendedSingletonS2ContainerInitializer;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * This is a PortletFilter implementation for Seasar2.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class S2PortletFilter implements PortletFilter {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(S2PortletFilter.class);

    public static final String CONFIG_PATH_KEY = "configPath";

    private PortletConfig portletConfig = null;

    public void init(PortletFilterConfig filterConfig) throws PortletException {
        if (logger.isDebugEnabled()) {
            logger.debug("calling S2PortletFilter#init(PortletFilterConfig).");
        }

        portletConfig = filterConfig.getPortletConfig();

        if (SingletonS2ContainerFactory.getContainer() == null) {
            initializeContainer(filterConfig);
        } else {
            ExternalContext externalContext = SingletonS2ContainerFactory
                    .getContainer().getExternalContext();
            if (externalContext instanceof PortletExternalContext) {
                externalContext.setApplication(portletConfig
                        .getPortletContext());
            } else {
                SingletonS2ContainerFactory.destroy();
                initializeContainer(filterConfig);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("finished S2PortletFilter#init(PortletFilterConfig).");
        }
    }

    protected void initializeContainer(PortletFilterConfig filterConfig) {
        String configPath = filterConfig.getInitParameter(CONFIG_PATH_KEY);
        PortletExtendedSingletonS2ContainerInitializer initializer = new PortletExtendedSingletonS2ContainerInitializer();
        initializer.setConfigPath(configPath);
        initializer.setApplication(portletConfig.getPortletContext());
        initializer.initialize();
    }

    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("calling S2PortletFilter#renderFilter(RenderRequest, RenderResponse, PortletFilterChain).");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        ExternalContext externalContext = container.getExternalContext();
        if (externalContext == null) {
            throw new EmptyRuntimeException("externalContext");
        }
        externalContext.setRequest(request);
        externalContext.setResponse(response);
        if (externalContext instanceof PortletExternalContext) {
            ((PortletExternalContext) externalContext).setConfig(portletConfig);
        }

        try {
            chain.renderFilter(request, response);
        } finally {
            externalContext.setRequest(null);
            externalContext.setResponse(null);
            if (externalContext instanceof PortletExternalContext) {
                ((PortletExternalContext) externalContext).setConfig(null);
            }
        }
    }

    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("calling S2PortletFilter#processActionFilter(ActionRequest, ActionResponse, PortletFilterChain).");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        ExternalContext externalContext = container.getExternalContext();
        if (externalContext == null) {
            throw new EmptyRuntimeException("externalContext");
        }
        externalContext.setRequest(request);
        externalContext.setResponse(response);
        if (externalContext instanceof PortletExternalContext) {
            ((PortletExternalContext) externalContext).setConfig(portletConfig);
        }

        try {
            chain.processActionFilter(request, response);
        } finally {
            externalContext.setRequest(null);
            externalContext.setResponse(null);
            if (externalContext instanceof PortletExternalContext) {
                ((PortletExternalContext) externalContext).setConfig(null);
            }
        }

    }

    public void destroy() {
        // do not destroy S2Container because S2Container is shared.
        // SingletonS2ContainerFactory.destroy();
        if (logger.isDebugEnabled()) {
            logger.debug("calling S2PortletFilter#destroy().");
        }

    }

}
