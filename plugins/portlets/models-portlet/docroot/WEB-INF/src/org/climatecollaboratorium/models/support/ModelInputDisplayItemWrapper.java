package org.climatecollaboratorium.models.support;

import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputDisplayItemType;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.liferay.portal.SystemException;

public class ModelInputDisplayItemWrapper {
    protected ModelInputDisplayItem wrappedItem;


    public static ModelInputDisplayItemWrapper getInputWrapper(ModelInputDisplayItem input) {
        if (input.getDisplayItemType() == ModelInputDisplayItemType.GROUP) {
            return new ModelInputGroupDisplayItemWrapper((ModelInputGroupDisplayItem) input);
        }
        return new ModelInputDisplayItemWrapper(input);
    }
    
    public ModelInputDisplayItemWrapper(ModelInputDisplayItem wrappedItem) {
        this.wrappedItem = wrappedItem;
    }
    
    public Long getGroupId() {
        if (wrappedItem instanceof ModelInputGroupDisplayItem) {
            return ((ModelInputGroupDisplayItem) wrappedItem).getGroupId();
        }
        else if (wrappedItem instanceof ModelInputIndividualDisplayItem) {
            return ((ModelInputIndividualDisplayItem) wrappedItem).getGroupId();
        }
        return 0L;
    }
    

}
