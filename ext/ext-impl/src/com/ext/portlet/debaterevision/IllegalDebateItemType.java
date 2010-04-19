/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision;

/**
 * @author: jintrone
 * @date: Mar 17, 2010
 */
public class IllegalDebateItemType extends Exception {

    public IllegalDebateItemType(DebateItemType parent, DebateItemType child) {
       super(child+" is not a legal child of "+parent);
    }
}
