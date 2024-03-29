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
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.framework.container.hotdeploy.HotdeployBehavior;
import org.seasar.framework.container.impl.S2ContainerBehavior;
import org.seasar.framework.log.Logger;

/**
 * This is a HOT deploy implementation for Portlet.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class HotdeployPortletFilter implements PortletFilter {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(HotdeployPortletFilter.class);

    private static final String KEY = HotdeployPortletFilter.class.getName();

    public void init(PortletFilterConfig filterConfig) throws PortletException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("calling HotdeployPortletFilter#init(PortletFilterConfig).");
        }
    }

    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("calling HotdeployPortletFilter#renderFilter(RenderRequest, RenderResponse, PortletFilterChain).");
        }

        if (request.getAttribute(KEY) == null) {
            S2ContainerBehavior.Provider provider = S2ContainerBehavior
                    .getProvider();
            if (provider instanceof HotdeployBehavior) {
                synchronized (HotdeployPortletFilter.class) {
                    HotdeployBehavior ondemand = (HotdeployBehavior) provider;
                    ondemand.start();
                    try {
                        request.setAttribute(KEY, Thread.currentThread()
                                .getContextClassLoader());
                        chain.renderFilter(request, response);
                    } finally {
                        ondemand.stop();
                    }
                }
            } else {
                chain.renderFilter(request, response);
            }
        } else {
            ClassLoader cl = (ClassLoader) request.getAttribute(KEY);
            Thread.currentThread().setContextClassLoader(cl);
            chain.renderFilter(request, response);
        }

    }

    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("calling HotdeployPortletFilter#processActionFilter(ActionRequest, ActionResponse, PortletFilterChain).");
        }

        if (request.getAttribute(KEY) == null) {
            S2ContainerBehavior.Provider provider = S2ContainerBehavior
                    .getProvider();
            if (provider instanceof HotdeployBehavior) {
                synchronized (HotdeployPortletFilter.class) {
                    HotdeployBehavior ondemand = (HotdeployBehavior) provider;
                    ondemand.start();
                    try {
                        request.setAttribute(KEY, Thread.currentThread()
                                .getContextClassLoader());
                        chain.processActionFilter(request, response);
                    } finally {
                        ondemand.stop();
                    }
                }
            } else {
                chain.processActionFilter(request, response);
            }
        } else {
            ClassLoader cl = (ClassLoader) request.getAttribute(KEY);
            Thread.currentThread().setContextClassLoader(cl);
            chain.processActionFilter(request, response);
        }
    }

    public void destroy() {
        if (logger.isDebugEnabled()) {
            logger.debug("calling HotdeployPortletFilter#destroy().");
        }
    }

}
