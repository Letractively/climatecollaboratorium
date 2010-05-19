/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debaterevision.model.impl;


import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.service.DebateItemReferenceLocalServiceUtil;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.SystemException;


public class DebateItemReferenceImpl extends DebateItemReferenceModelImpl
    implements DebateItemReference {
    public DebateItemReferenceImpl() {
    }


    public DebateItemReferenceImpl(String text, String url) {
      this.setText(text);
        this.setUrl(url);
    }

    /**
     * Moves this item forward and set the version to the passed in parameter
     *
     * @param version The target version for the entry being moved.
     * @throws SystemException
     */
    public void moveForward(Long version) throws SystemException {
        Long pk = CounterUtil.increment(DebateItemReference.class.getName());
        DebateItemReference ref = DebateItemReferenceLocalServiceUtil.createDebateItemReference(pk);
        ref.setText(getText());
        ref.setUrl(getUrl());
        ref.setDebateId(getDebateId());
        ref.setDebateItemId(getDebateItemId());
        ref.setItemVersion(getItemVersion());
        ref.setStatus(getStatus());
        DebateItemReferenceLocalServiceUtil.updateDebateItemReference(ref);

        setItemVersion(version);
        DebateItemReferenceLocalServiceUtil.updateDebateItemReference(this);




    }
}
