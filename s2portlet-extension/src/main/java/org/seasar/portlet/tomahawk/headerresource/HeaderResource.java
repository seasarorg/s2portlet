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
 * HeaderResource manages header tags, such as &lt;script&gt; and &lt;link&gt;, on the portal server.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public interface HeaderResource
{
    /**
     * Initialize this instance.
     */
    public void init();

    /**
     * Add specified tag elements to portal server.
     * 
     * @param request   portlet's request
     * @param response  portlet's response
     * @param elements  tags provided by MyFaces's AddResource
     */
    public void addHeaderResources(PortletRequest request, PortletResponse response, String elements);

    public PortletContext getPortletContext();

    public void setPortletContext(PortletContext portletContext);
}
