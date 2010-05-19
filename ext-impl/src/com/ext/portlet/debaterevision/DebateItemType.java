/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: jintrone
 * @date: Mar 17, 2010
 */
public enum DebateItemType {
    ARGUMENT_PRO(0,1),ARGUMENT_CON(0,1),POSITION(0,1),QUESTION(2);

    Set<Integer> legalChildren = new HashSet<Integer>();

    DebateItemType(int... legalChildren) {
        for (int x:legalChildren) {
          this.legalChildren.add(x);
        }
    }

    public boolean isLegalChild(DebateItemType proposed, boolean checked) throws IllegalDebateItemType {
      if (!legalChildren.contains(proposed.ordinal())) {
          if (checked) {
              throw new IllegalDebateItemType(this,proposed);
          }
          return false;
      }
      return true;
    }
}
