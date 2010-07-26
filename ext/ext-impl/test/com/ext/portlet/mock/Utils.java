/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mock;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 21, 2010
 * Time: 1:22:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

     @SuppressWarnings("all")
    public static void setField(Class clazz, Object object, String fieldName, Object fieldValue) throws Exception {
        Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(object, fieldValue);
    }
}
