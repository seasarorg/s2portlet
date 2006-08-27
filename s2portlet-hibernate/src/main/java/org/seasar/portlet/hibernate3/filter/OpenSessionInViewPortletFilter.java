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
package org.seasar.portlet.hibernate3.filter;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * This is a Open Session In View implementation for Seasar2 on Portlet.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */public class OpenSessionInViewPortletFilter implements PortletFilter
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory
            .getLog(OpenSessionInViewPortletFilter.class);

    /* (non-Javadoc)
     * @see org.apache.portals.bridges.portletfilter.PortletFilter#init(org.apache.portals.bridges.portletfilter.PortletFilterConfig)
     */
    public void init(PortletFilterConfig filterConfig) throws PortletException
    {
        if (log.isDebugEnabled())
        {
            log.debug("init(PortletFilterConfig) - start");
        }

        if (log.isDebugEnabled())
        {
            log.debug("init(PortletFilterConfig) - end");
        }
    }

    /* (non-Javadoc)
     * @see org.apache.portals.bridges.portletfilter.PortletFilter#processActionFilter(javax.portlet.ActionRequest, javax.portlet.ActionResponse, org.apache.portals.bridges.portletfilter.PortletFilterChain)
     */
    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException
    {
        if (log.isDebugEnabled())
        {
            log
                    .debug("processActionFilter(ActionRequest, ActionResponse, PortletFilterChain) - start");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        PortletFilterChainTx chainProxy = (PortletFilterChainTx) container
                .getComponent(PortletFilterChainTx.class);
        chainProxy.processActionFilter(request, response, chain);

        if (log.isDebugEnabled())
        {
            log
                    .debug("processActionFilter(ActionRequest, ActionResponse, PortletFilterChain) - end");
        }
    }

    /* (non-Javadoc)
     * @see org.apache.portals.bridges.portletfilter.PortletFilter#renderFilter(javax.portlet.RenderRequest, javax.portlet.RenderResponse, org.apache.portals.bridges.portletfilter.PortletFilterChain)
     */
    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException
    {
        if (log.isDebugEnabled())
        {
            log
                    .debug("renderFilter(RenderRequest, RenderResponse, PortletFilterChain) - start");
        }

        S2Container container = SingletonS2ContainerFactory.getContainer();
        PortletFilterChainTx chainProxy = (PortletFilterChainTx) container
                .getComponent(PortletFilterChainTx.class);
        chainProxy.renderFilter(request, response, chain);

        if (log.isDebugEnabled())
        {
            log
                    .debug("renderFilter(RenderRequest, RenderResponse, PortletFilterChain) - end");
        }
    }

    public void destroy()
    {

    }
}
