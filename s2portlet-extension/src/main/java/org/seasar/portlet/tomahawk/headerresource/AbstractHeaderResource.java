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
package org.seasar.portlet.tomahawk.headerresource;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * This class has basic methods for HeaderResource.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public abstract class AbstractHeaderResource implements HeaderResource
{

    private PortletContext portletContext;

    /**
     * @return Returns the portletContext.
     */
    public PortletContext getPortletContext()
    {
        return portletContext;
    }

    /**
     * @param portletContext The portletContext to set.
     */
    public void setPortletContext(PortletContext portletContext)
    {
        this.portletContext = portletContext;
    }

    public abstract void init();

    public abstract void addHeaderResources(PortletRequest request, PortletResponse response, String elements);
}
