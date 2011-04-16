package com.ext.utils.userInput;

import org.owasp.validator.html.Policy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class PolicyFactoryBean implements FactoryBean, InitializingBean {
    Policy policy;

    Resource policyFile;

    @Override
    public Policy getObject() throws Exception {
        // TODO Auto-generated method stub
        return policy;
    }

    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Policy.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        policy = Policy.getInstance(policyFile.getURL());
    }

    public void setPolicyFile(Resource policyFile) {
        this.policyFile = policyFile;
    }

    public Resource getPolicyFile() {
        return policyFile;
    }

}                                                                                       