/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/FormPropertyConfig.java,v 1.2 2002/01/17 00:15:05 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2002/01/17 00:15:05 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.config;


import java.io.Serializable;
import org.apache.commons.beanutils.ConvertUtils;


/**
 * <p>A JavaBean representing the configuration information of a
 * <code>&lt;form-property&gt;</code> element in a Struts application
 * configuration file.<p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2002/01/17 00:15:05 $
 * @since Struts 1.1
 */

public class FormPropertyConfig implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * Have we calculated the initial value object yet?
     */
    protected transient boolean initialized = false;


    /**
     * The calculated initial value for this property.
     */
    protected transient Object initialValue = null;


    // ------------------------------------------------------------- Properties


    /**
     * String representation of the initial value for this property.
     */
    protected String initial = null;

    public String getInitial() {
        return (this.initial);
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }


    /**
     * The JavaBean property name of the property described by this element.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The fully qualified Java class name of the implementation class
     * of this bean property, optionally followed by <code>[]</code> to
     * indicate that the property is indexed.
     */
    protected String type = null;

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return an object representing the initial value of this property.
     */
    public Object initial() {

        // Don't bother synchronizing, a race is basically harmless
        if (!initialized) {
            try {
                if (initial == null) {
                    if ("boolean".equals(type)) {
                        initialValue = Boolean.FALSE;
                    } else if ("byte".equals(type)) {
                        initialValue = new Byte((byte) 0);
                    } else if ("char".equals(type)) {
                        initialValue = new Character((char) 0);
                    } else if ("double".equals(type)) {
                        initialValue = new Double((double) 0.0);
                    } else if ("float".equals(type)) {
                        initialValue = new Float((float) 0.0);
                    } else if ("int".equals(type)) {
                        initialValue = new Integer(0);
                    } else if ("long".equals(type)) {
                        initialValue = new Long((long) 0);
                    } else if ("short".equals(type)) {
                        initialValue = new Short((short) 0);
                    } else {
                        initialValue = null;
                    }
                } else {
                    Class clazz = Class.forName(type);
                    initialValue = ConvertUtils.convert(initial, clazz);
                }
            } catch (Throwable t) {
                initialValue = null;
            }
            initialized = true;
        }
        return (initialValue);

    }


    /**
     * Return a String representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("FormPropertyConfig[");
        sb.append("name=");
        sb.append(this.name);
        sb.append(",type=");
        sb.append(this.type);
        sb.append(",initial=");
        sb.append(this.initial);
        sb.append("]");
        return (sb.toString());

    }


}
