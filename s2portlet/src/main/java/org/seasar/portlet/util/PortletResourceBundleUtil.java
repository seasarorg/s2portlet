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
package org.seasar.portlet.util;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is a utility class to use a resource bundle defined by portlet.xml
 * on Seasar2 environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletResourceBundleUtil {

    public static ResourceBundle getBundle(Locale locale) {
        return PortletConfigUtil.getResourceBundle(locale);
    }

    public static Enumeration getKeys(Locale locale) {
        ResourceBundle bundle = getBundle(locale);
        if (bundle != null) {
            return bundle.getKeys();
        }
        return null;
    }

    public static Object getObject(Locale locale, String key) {
        ResourceBundle bundle = getBundle(locale);
        if (bundle != null) {
            return bundle.getObject(key);
        }
        return null;
    }

    public static String getString(Locale locale, String key) {
        return (String) getObject(locale, key);
    }

    public static String[] getStringArray(Locale locale, String key) {
        return (String[]) getObject(locale, key);
    }

    public static String getString(Locale locale, String key, Object[] params) {
        MessageFormat format = new MessageFormat(getString(locale, key), locale);
        return format.format(params);
    }
}
