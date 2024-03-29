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

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PreferencesValidator;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * This class is a utility class to use PortletPreference on Seasar2
 * environment.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class PortletPreferencesUtil {
    public static PortletPreferences getPortletPreferences() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container == null) {
            throw new IllegalStateException("S2Container is null.");
        }

        if (container.getExternalContext().getRequest() instanceof PortletRequest) {
            return ((PortletRequest) container.getExternalContext()
                    .getRequest()).getPreferences();
        }
        return null;
    }

    /**
     * Returns true, if the value of this key cannot be modified by the user.
     * <p>
     * Modifiable preferences can be changed by the portlet in any standard
     * portlet mode (<code>EDIT, HELP, VIEW</code>). Per default every
     * preference is modifiable.
     * <p>
     * Read-only preferences cannot be changed by the portlet in any standard
     * portlet mode, but inside of custom modes it may be allowed changing them.
     * Preferences are read-only, if they are defined in the deployment
     * descriptor with <code>read-only</code> set to <code>true</code>, or
     * if the portlet container restricts write access.
     * 
     * @return false, if the value of this key can be changed, or if the key is
     *         not known
     * 
     * @exception java.lang.IllegalArgumentException
     *                if <code>key</code> is <code>null</code>.
     */
    public static boolean isReadOnly(String key) {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        return prefs.isReadOnly(key);
    }

    /**
     * Returns the first String value associated with the specified key of this
     * preference. If there is one or more preference values associated with the
     * given key it returns the first associated value. If there are no
     * preference values associated with the given key, or the backing
     * preference database is unavailable, it returns the given default value.
     * 
     * @param key
     *            key for which the associated value is to be returned
     * @param def
     *            the value to be returned in the event that there is no value
     *            available associated with this <code>key</code>.
     * 
     * @return the value associated with <code>key</code>, or
     *         <code>def</code> if no value is associated with
     *         <code>key</code>, or the backing store is inaccessible.
     * 
     * @exception java.lang.IllegalArgumentException
     *                if <code>key</code> is <code>null</code>. (A
     *                <code>null</code> value for <code>def</code> <i>is</i>
     *                permitted.)
     * 
     * @see #getValues(String, String[])
     */
    public static String getValue(String key, String def) {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            return def;
        }

        return prefs.getValue(key, def);
    }

    /**
     * Returns the String array value associated with the specified key in this
     * preference.
     * 
     * <p>
     * Returns the specified default if there is no value associated with the
     * key, or if the backing store is inaccessible.
     * 
     * <p>
     * If the implementation supports <i>stored defaults</i> and such a default
     * exists and is accessible, it is used in favor of the specified default.
     * 
     * 
     * @param key
     *            key for which associated value is to be returned.
     * @param def
     *            the value to be returned in the event that this preference
     *            node has no value associated with <code>key</code> or the
     *            associated value cannot be interpreted as a String array, or
     *            the backing store is inaccessible.
     * 
     * @return the String array value associated with <code>key</code>, or
     *         <code>def</code> if the associated value does not exist.
     * 
     * @exception java.lang.IllegalArgumentException
     *                if <code>key</code> is <code>null</code>. (A
     *                <code>null</code> value for <code>def</code> <i>is</i>
     *                permitted.)
     * 
     * @see #getValue(String,String)
     */
    public static String[] getValues(String key, String[] def) {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            return def;
        }

        return prefs.getValues(key, def);
    }

    /**
     * Associates the specified String value with the specified key in this
     * preference.
     * <p>
     * The key cannot be <code>null</code>, but <code>null</code> values
     * for the value parameter are allowed.
     * 
     * @param key
     *            key with which the specified value is to be associated.
     * @param value
     *            value to be associated with the specified key.
     * 
     * @exception IOException
     *                if this preference cannot be modified for this request
     * @exception java.lang.IllegalArgumentException
     *                if key is <code>null</code>, or
     *                <code>key.length()</code> or <code>value.length</code>
     *                are to long. The maximum length for key and value are
     *                implementation specific.
     * 
     * @see #setValues(String, String[])
     */
    public static void setValue(String key, String value) throws IOException {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        try {
            prefs.setValue(key, value);
        } catch (ReadOnlyException e) {
            throw new IOException(e.toString());
        }
    }

    /**
     * Associates the specified String array value with the specified key in
     * this preference.
     * <p>
     * The key cannot be <code>null</code>, but <code>null</code> values in
     * the values parameter are allowed.
     * 
     * @param key
     *            key with which the value is to be associated
     * @param values
     *            values to be associated with key
     * 
     * @exception java.lang.IllegalArgumentException
     *                if key is <code>null</code>, or
     *                <code>key.length()</code> is to long or
     *                <code>value.size</code> is to large. The maximum length
     *                for key and maximum size for value are implementation
     *                specific.
     * @exception IOException
     *                if this preference cannot be modified for this request
     * 
     * @see #setValue(String,String)
     */
    public static void setValues(String key, String[] values)
            throws IOException {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        try {
            prefs.setValues(key, values);
        } catch (ReadOnlyException e) {
            throw new IOException(e.toString());
        }
    }

    /**
     * Returns all of the keys that have an associated value, or an empty
     * <code>Enumeration</code> if no keys are available.
     * 
     * @return an Enumeration of the keys that have an associated value, or an
     *         empty <code>Enumeration</code> if no keys are available.
     */
    public static java.util.Enumeration getNames() {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        return prefs.getNames();
    }

    /**
     * Returns a <code>Map</code> of the preferences.
     * <p>
     * The values in the returned <code>Map</code> are from type String array (<code>String[]</code>).
     * <p>
     * If no preferences exist this method returns an empty <code>Map</code>.
     * 
     * @return an immutable <code>Map</code> containing preference names as
     *         keys and preference values as map values, or an empty
     *         <code>Map</code> if no preference exist. The keys in the
     *         preference map are of type String. The values in the preference
     *         map are of type String array (<code>String[]</code>).
     */

    public static java.util.Map getMap() {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        return prefs.getMap();
    }

    /**
     * Resets or removes the value associated with the specified key.
     * <p>
     * If this implementation supports stored defaults, and there is such a
     * default for the specified preference, the given key will be reset to the
     * stored default.
     * <p>
     * If there is no default available the key will be removed.
     * 
     * @param key
     *            to reset
     * 
     * @exception java.lang.IllegalArgumentException
     *                if key is <code>null</code>.
     * @exception IOException
     *                if this preference cannot be modified for this request
     */

    public static void reset(String key) throws IOException {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        try {
            prefs.reset(key);
        } catch (ReadOnlyException e) {
            throw new IOException(e.toString());
        }
    }

    /**
     * Commits all changes made to the preferences via the <code>set</code>
     * methods in the persistent store.
     * <P>
     * If this call returns succesfull, all changes are made persistent. If this
     * call fails, no changes are made in the persistent store. This call is an
     * atomic operation regardless of how many preference attributes have been
     * modified.
     * <P>
     * All changes made to preferences not followed by a call to the
     * <code>store</code> method are discarded when the portlet finishes the
     * <code>processAction</code> method.
     * <P>
     * If a validator is defined for this preferences in the deployment
     * descriptor, this validator is called before the actual store is performed
     * to check wether the given preferences are vaild. If this check fails a
     * <code>ValidatorException</code> is thrown.
     * 
     * @exception java.io.IOException
     *                if changes cannot be written into the backend store
     * @exception ValidatorException
     *                if the validation performed by the associated validator
     *                fails
     * @exception java.lang.IllegalStateException
     *                if this method is called inside a render call
     * 
     * @see PreferencesValidator
     */
    public static void store() throws IOException {
        PortletPreferences prefs = getPortletPreferences();
        if (prefs == null) {
            throw new IllegalStateException(
                    "Cannot retrieve PortletPreferences.");
        }

        try {
            prefs.store();
        } catch (ValidatorException e) {
            throw new IOException(e.toString());
        }
    }

}
