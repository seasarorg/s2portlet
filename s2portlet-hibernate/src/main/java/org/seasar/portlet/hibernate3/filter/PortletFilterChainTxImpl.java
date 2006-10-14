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

import org.apache.portals.bridges.portletfilter.PortletFilterChain;
import org.hibernate.Session;
import org.seasar.hibernate3.S2SessionFactory;

/**
 * This is a PortletFilterChain implementation for S2Hibernate.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletFilterChainTxImpl implements PortletFilterChainTx {

    private S2SessionFactory sessionFactory_;

    private String requestAttributeName_ = "S2Session";

    public PortletFilterChainTxImpl(S2SessionFactory sessionFactory) {
        sessionFactory_ = sessionFactory;
    }

    public void setRequestAttributeName(String requestAttributeName) {
        requestAttributeName_ = requestAttributeName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.seasar.portlet.hibernate3.filter.PortletFilterChainTx#processActionFilter(javax.portlet.ActionRequest,
     *      javax.portlet.ActionResponse,
     *      org.apache.portals.bridges.portletfilter.PortletFilterChain)
     */
    public void processActionFilter(ActionRequest request,
            ActionResponse response, PortletFilterChain chain)
            throws PortletException, IOException {
        Session session = sessionFactory_.getSession();
        request.setAttribute(requestAttributeName_, session);

        chain.processActionFilter(request, response);

        if (session.isOpen()) {
            session.close();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.seasar.portlet.hibernate3.filter.PortletFilterChainTx#renderFilter(javax.portlet.RenderRequest,
     *      javax.portlet.RenderResponse,
     *      org.apache.portals.bridges.portletfilter.PortletFilterChain)
     */
    public void renderFilter(RenderRequest request, RenderResponse response,
            PortletFilterChain chain) throws PortletException, IOException {
        Session session = sessionFactory_.getSession();
        request.setAttribute(requestAttributeName_, session);

        chain.renderFilter(request, response);

        if (session.isOpen()) {
            session.close();
        }
    }
}
