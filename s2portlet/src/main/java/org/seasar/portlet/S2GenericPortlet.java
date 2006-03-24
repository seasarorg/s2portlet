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
package org.seasar.portlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.seasar.framework.container.ExternalContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.StringUtil;
import org.seasar.portlet.container.deployer.PortletComponentDeployerProvider;
import org.seasar.portlet.container.impl.PortletExternalContext;
import org.seasar.portlet.container.impl.PortletExternalContextComponentDefRegister;

/**
 * This is a Portlet implementation for Seasar2 environment.
 * S2GenericPortlet uses a Portlet instance defined by *.dicon.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class S2GenericPortlet implements Portlet, PortletConfig
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(S2GenericPortlet.class);

    public static final String CONFIG_PATH_KEY = "configPath";

    public static final String PORTLET_NAME_KEY = "portletName";

    private PortletConfig config = null;

    private String portletName = null;

    /* (non-Javadoc)
     * @see javax.portlet.Portlet#destroy()
     */
    public void destroy()
    {
        try
        {
            getGenericPortlet().destroy();
        }
        catch (PortletException e)
        {
            log.error(e);
        }
        SingletonS2ContainerFactory.destroy();
    }

    /* (non-Javadoc)
     * @see javax.portlet.Portlet#init(javax.portlet.PortletConfig)
     */
    public void init(PortletConfig portletConfig) throws PortletException
    {
        config = portletConfig;

        portletName = config.getInitParameter(PORTLET_NAME_KEY);
        if (portletName == null)
        {
            portletName = PORTLET_NAME_KEY;
        }
        String configPath = config.getInitParameter(CONFIG_PATH_KEY);
        if (log.isDebugEnabled())
        {
            log.debug("init() -  : configPath=" + configPath);
        }
        if (!StringUtil.isEmpty(configPath))
        {
            SingletonS2ContainerFactory.setConfigPath(configPath);
        }
        if (ComponentDeployerFactory.getProvider() instanceof ComponentDeployerFactory.DefaultProvider)
        {
            ComponentDeployerFactory
                    .setProvider(new PortletComponentDeployerProvider());
        }
        PortletExternalContext extCtx = new PortletExternalContext();
        extCtx.setApplication(config.getPortletContext());
        SingletonS2ContainerFactory.setExternalContext(extCtx);
        SingletonS2ContainerFactory
                .setExternalContextComponentDefRegister(new PortletExternalContextComponentDefRegister());
        SingletonS2ContainerFactory.init();
    }

    /* (non-Javadoc)
     * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
     */
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException
    {
        // set request, response, portletConfig
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
            ((PortletExternalContext) externalContext).setConfig(config);
        }

        getGenericPortlet().processAction(request, response);

        // remove request, response, portletConfig
        externalContext.setRequest(null);
        externalContext.setResponse(null);
        if (externalContext instanceof PortletExternalContext)
        {
            ((PortletExternalContext) externalContext).setConfig(null);
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.Portlet#render(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
     */
    public void render(RenderRequest request, RenderResponse response)
            throws PortletException, IOException
    {
        // set request, response, portletConfig
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
            ((PortletExternalContext) externalContext).setConfig(config);
        }

        getGenericPortlet().render(request, response);

        // remove request, response, portletConfig
        externalContext.setRequest(null);
        externalContext.setResponse(null);
        if (externalContext instanceof PortletExternalContext)
        {
            ((PortletExternalContext) externalContext).setConfig(null);
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletConfig#getInitParameter(java.lang.String)
     */
    public String getInitParameter(String str)
    {
        try
        {
            return getGenericPortlet().getInitParameter(str);
        }
        catch (PortletException e)
        {
            log.warn(e);
            return config.getInitParameter(str);
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletConfig#getInitParameterNames()
     */
    public Enumeration getInitParameterNames()
    {
        try
        {
            return getGenericPortlet().getInitParameterNames();
        }
        catch (PortletException e)
        {
            log.warn(e);
            return config.getInitParameterNames();
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletConfig#getPortletContext()
     */
    public PortletContext getPortletContext()
    {
        try
        {
            return getGenericPortlet().getPortletContext();
        }
        catch (PortletException e)
        {
            log.warn(e);
            return config.getPortletContext();
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletConfig#getPortletName()
     */
    public String getPortletName()
    {
        try
        {
            return getGenericPortlet().getPortletName();
        }
        catch (PortletException e)
        {
            log.warn(e);
            return config.getPortletName();
        }
    }

    /* (non-Javadoc)
     * @see javax.portlet.PortletConfig#getResourceBundle(java.util.Locale)
     */
    public ResourceBundle getResourceBundle(Locale locale)
    {
        try
        {
            return getGenericPortlet().getResourceBundle(locale);
        }
        catch (PortletException e)
        {
            log.warn(e);
            return config.getResourceBundle(locale);
        }
    }

    /**
     * @return The Portlet instance defined by dicon file
     * @throws PortletException
     */
    protected GenericPortlet getGenericPortlet() throws PortletException
    {
        GenericPortlet portlet = (GenericPortlet) SingletonS2ContainerFactory
                .getContainer().getComponent(portletName);
        if (portlet == null)
        {
            throw new PortletException("GenericPortlet is not defined in "
                    + config.getInitParameter(CONFIG_PATH_KEY) + ".");
        }

        // Initialize portlet instance
        if (portlet.getPortletConfig() == null)
        {
            portlet.init(config);
        }
        return portlet;
    }

}
