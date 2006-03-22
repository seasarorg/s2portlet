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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.portlet.container.deployer.PortletComponentDeployerProvider;
import org.seasar.portlet.container.impl.PortletExternalContext;
import org.seasar.portlet.container.impl.PortletExternalContextComponentDefRegister;

/**
 * This is a GenericPortlet implementation for Seasar2.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class S2GenericPortlet extends GenericPortlet
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(S2GenericPortlet.class);

    public static final String CONFIG_PATH_KEY = "configPath";

    /* (non-Javadoc)
     * @see javax.portlet.GenericPortlet#init()
     */
    public void init() throws PortletException
    {
        super.init();

        String configPath = getPortletConfig()
                .getInitParameter(CONFIG_PATH_KEY);
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
        extCtx.setApplication(getPortletContext());
        SingletonS2ContainerFactory.setExternalContext(extCtx);
        SingletonS2ContainerFactory
                .setExternalContextComponentDefRegister(new PortletExternalContextComponentDefRegister());
        SingletonS2ContainerFactory.init();
    }

    /* (non-Javadoc)
     * @see javax.portlet.GenericPortlet#destroy()
     */
    public void destroy()
    {
        SingletonS2ContainerFactory.destroy();
        super.destroy();
    }

}
