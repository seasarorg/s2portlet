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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.portals.bridges.portletfilter.PortletFilter;
import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.apache.portals.bridges.portletfilter.PortletFilterConfig;
import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.deployer.ExternalComponentDeployerProvider;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.impl.portlet.PortletExternalContext;
import org.seasar.framework.container.impl.portlet.PortletExternalContextComponentDefRegister;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.StringUtil;

/**
 * This is a PortletFilter implementation for Seasar2.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class S2PortletFilter implements PortletFilter
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(S2PortletFilter.class);

    public static final String CONFIG_PATH_KEY = "configPath";

    private PortletConfig portletConfig = null;

    public void init(PortletFilterConfig filterConfig) throws PortletException
    {
        if (log.isDebugEnabled())
        {
            log.debug("init(PortletFilterConfig) - start");
        }

        portletConfig = filterConfig.getPortletConfig();

        // restart S2Container
        if (SingletonS2ContainerFactory.getContainer() != null)
        {
            SingletonS2ContainerFactory.destroy();
        }

        String configPath = filterConfig.getInitParameter(CONFIG_PATH_KEY);
        if (log.isDebugEnabled())
        {
            log.debug("init(PortletFilterConfig) -  :" + " configPath="
                    + configPath);
        }
        if (!StringUtil.isEmpty(configPath))
        {
            SingletonS2ContainerFactory.setConfigPath(configPath);
        }
        if (ComponentDeployerFactory.getProvider() instanceof ComponentDeployerFactory.DefaultProvider)
        {
            ComponentDeployerFactory
                    .setProvider(new ExternalComponentDeployerProvider());
        }
        PortletExternalContext extCtx = new PortletExternalContext();
        extCtx.setApplication(portletConfig.getPortletContext());
        SingletonS2ContainerFactory.setExternalContext(extCtx);
        SingletonS2ContainerFactory
                .setExternalContextComponentDefRegister(new PortletExternalContextComponentDefRegister());
        SingletonS2ContainerFactory.init();

        if (log.isDebugEnabled())
        {
            log.debug("init(PortletFilterConfig) - end");
        }
    }

    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException
    {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ExternalContext externalContext = container.getExternalContext();
        if (externalContext == null)
        {
            throw new EmptyRuntimeException("externalContext");
        }
        externalContext.setRequest(request);
        externalContext.setResponse(response);
        if (externalContext instanceof PortletExternalContext)
        {
            ((PortletExternalContext) externalContext).setConfig(portletConfig);
        }

        try
        {
            chain.renderFilter(request, response);
        }
        finally
        {
            externalContext.setRequest(null);
            externalContext.setResponse(null);
            if (externalContext instanceof PortletExternalContext)
            {
                ((PortletExternalContext) externalContext).setConfig(null);
            }
        }
    }

    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException
    {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        ExternalContext externalContext = container.getExternalContext();
        if (externalContext == null)
        {
            throw new EmptyRuntimeException("externalContext");
        }
        externalContext.setRequest(request);
        externalContext.setResponse(response);
        if (externalContext instanceof PortletExternalContext)
        {
            ((PortletExternalContext) externalContext).setConfig(portletConfig);
        }

        try
        {
            chain.processActionFilter(request, response);
        }
        finally
        {
            externalContext.setRequest(null);
            externalContext.setResponse(null);
            if (externalContext instanceof PortletExternalContext)
            {
                ((PortletExternalContext) externalContext).setConfig(null);
            }
        }

    }

    public void destroy()
    {
        SingletonS2ContainerFactory.destroy();
    }

}
