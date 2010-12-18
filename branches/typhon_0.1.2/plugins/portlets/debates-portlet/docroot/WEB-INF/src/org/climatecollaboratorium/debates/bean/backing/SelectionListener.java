package org.climatecollaboratorium.debates.bean.backing;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public interface SelectionListener<T> {
    void onSelected(T item) throws SystemException, PortalException;
}