package com.ext.portlet.debaterevision.model.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.service.impl.DebateLocalServiceImpl;
import com.ext.portlet.debaterevision.service.persistence.DebatePersistenceImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import junit.framework.TestCase;

public class DebateImplTest extends TestCase {
    
    public void setUp() {
        DebateLocalServiceImpl localSvcImpl = new DebateLocalServiceImpl();
        localSvcImpl.setDebatePersistence(new DebatePersistenceImpl());
        //(new DebateLocalServiceUtil()).setService(localSvcImpl);
        /*
        System.setProperty("java.naming.factory.initial", "com.sun.jndi.fscontext.RefFSContextFactory");
        System.setProperty("java.naming.provider.url", "file:///tmp/provider");
        System.setProperty("jdbc.default.driverClassName", "com.mysql.jdbc.Driver");
        System.setProperty("jdbc.default.url", "jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false");
        System.setProperty("jdbc.default.username", "liferay");
        System.setProperty("jdbc.default.password", "liferay");
        
        Reference ref = new Reference("javax.sql.DataSource");
        try {
            InitialContext ictx = new InitialContext();
            //ictx.bind("jdbc/LiferayPool", ref);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
        
        //META-INF/shard-data-source-spring.xml
        //META-INF/shard-data-source-spring.xml
        ApplicationContext ac4 = new ClassPathXmlApplicationContext("META-INF/shard-data-source-spring.xml");
        //ApplicationContext ac3 = new ClassPathXmlApplicationContext("META-INF/infrastructure-spring.xml");
        ApplicationContext ac1 = new ClassPathXmlApplicationContext("META-INF/hibernate-spring.xml");
        ApplicationContext ac0 = new ClassPathXmlApplicationContext("META-INF/base-spring.xml");
        ApplicationContext ac2 =  new ClassPathXmlApplicationContext("META-INF/portal-spring.xml");
        //ApplicationContext ac2 =  new ClassPathXmlApplicationContext("META-INF/ext-spring.xml");
    }
    
    public void tearDown() {
    }
    
    public void testCos() throws PortalException, SystemException {
        Debate debate = DebateLocalServiceUtil.getDebate(1L);
        
    }
    
 
}
