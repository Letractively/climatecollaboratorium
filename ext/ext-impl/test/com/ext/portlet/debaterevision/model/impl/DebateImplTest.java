package com.ext.portlet.debaterevision.model.impl;

import javax.naming.Context;

import org.hibernate.engine.SessionFactoryImplementor;

import junit.framework.TestCase;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debaterevision.service.impl.DebateLocalServiceImpl;
import com.ext.portlet.debaterevision.service.persistence.DebatePersistenceImpl;
import com.ext.portlet.debaterevision.service.persistence.DebateUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.cache.EhcachePortalCacheManager;
import com.liferay.portal.cache.MultiVMPoolImpl;
import com.liferay.portal.dao.orm.hibernate.EntityCacheImpl;
import com.liferay.portal.dao.orm.hibernate.SessionFactoryImpl;
import com.liferay.portal.spring.hibernate.PortalHibernateConfiguration;
import com.liferay.portal.util.InitUtil;
import com.sun.management.jmx.MBeanServerImpl;

public class DebateImplTest extends TestCase {
    
    public void setUp() {
        /*
        DebateLocalServiceImpl localSvcImpl = new DebateLocalServiceImpl();
        localSvcImpl.setDebatePersistence(new DebatePersistenceImpl());
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.SimpleContextFactory" );
        //File f = new File();
        //System.out.println(f.getAbsolutePath());
        System.setProperty("org.osjava.sj.root", "/tmp/");

        
        try {
            
            Context ctx = new InitialContext();
            //ctx.

            MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setUser("liferay");
            dataSource.setPassword("liferay");
            dataSource.setURL("jdbc:mysql://localhost/simulation3?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false");
            ctx.bind("java:comp/env/jdbc/LiferayPool", dataSource);
            
            Context ctx2 = (Context) ctx.lookup("java:comp/env");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        */
        

        //
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

        /*
        ApplicationContext ac4 = new ClassPathXmlApplicationContext("META-INF/test-data-source-spring.xml");
        App
        //ApplicationContext ac4 = new ClassPathXmlApplicationContext("META-INF/test-data-source-spring.xml");
        //ApplicationContext ac3 = new ClassPathXmlApplicationContext("META-INF/infrastructure-spring.xml");
        ApplicationContext ac1 = new ClassPathXmlApplicationContext("META-INF/hibernate-spring.xml");
        ApplicationContext ac0 = new ClassPathXmlApplicationContext("META-INF/base-spring.xml");
        ApplicationContext ac2 =  new ClassPathXmlApplicationContext("META-INF/portal-spring.xml");
        //ApplicationContext ac2 =  new ClassPathXmlApplicationContext("META-INF/ext-spring.xml");
         * 
         */
        
    	/*
        DebatePersistenceImpl debatePersistence = new DebatePersistenceImpl();
        PortalHibernateConfiguration portalHibConf = new PortalHibernateConfiguration();
        
        try {
			portalHibConf.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        SessionFactoryImpl sessionFactoryImpl = new SessionFactoryImpl();
        //sessionFactoryImpl.setSessionFactoryImplementor(portalHibConf);
        
        
        debatePersistence.setSessionFactory(sessionFactoryImpl);
        DebateLocalServiceImpl localSvcImpl = new DebateLocalServiceImpl();
        localSvcImpl.setDebatePersistence(debatePersistence);
        (new DebateLocalServiceUtil()).setService(localSvcImpl);
        
        EntityCacheImpl entityCache = new EntityCacheImpl();
        EhcachePortalCacheManager portalCacheManager = new EhcachePortalCacheManager();
        MultiVMPoolImpl multiVMPool = new MultiVMPoolImpl();
        
        portalCacheManager.setConfigPropertyKey("ehcache.multi.vm.config.location");

        portalCacheManager.setMBeanServer(new MBeanServerImpl());
        
        //portalCacheManager.set
        portalCacheManager.afterPropertiesSet();
        
        
        multiVMPool.setPortalCacheManager(portalCacheManager);
        entityCache.setMultiVMPool(multiVMPool);
        
        
        
        (new EntityCacheUtil()).setEntityCache(entityCache);
        //SingleVMPortalCacheManager
        //MultiVMPoo
        
        /*
        String[] configLocations = { "META-INF/test-data-source-spring.xml", 
                "META-INF/management-spring.xml",
                "META-INF/hibernate-spring.xml",
                "META-INF/base-spring.xml",
                "META-INF/portal-spring.xml"
        };
        
        
        ApplicationContext ac = new ClassPathXmlApplicationContext(configLocations);
        */
    	
    	//prepareDebateServices();
    	//initializeJNDI();
    	//loadSpringConf();
    	prepareDebateServices();
    }
    
    private void initializeJNDI() {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.SimpleContextFactory" );
    }
    
    private void loadSpringConf() {
    	
    	InitUtil.initWithSpring();
    	/*
    	String[] configLocations = { 
    	        "META-INF/base-spring.xml",
    	        
    	        "META-INF/hibernate-spring.xml",
    	        
    	        "META-INF/infrastructure-spring.xml",
    	        "META-INF/test-data-source-spring.xml", 
    	        
    	        "META-INF/management-spring.xml",
    	        
    	        "META-INF/util-spring.xml",
    	        
    	        "META-INF/editor-spring.xml",
    	        "META-INF/jcr-spring.xml",
    	        "META-INF/messaging-spring.xml",
    	        "META-INF/scheduler-spring.xml",
    	        "META-INF/search-spring.xml",
    	        
    	        "META-INF/counter-spring.xml",
    	        "META-INF/document-library-spring.xml",
    	        "META-INF/lock-spring.xml",
    	        "META-INF/mail-spring.xml",
    	        "META-INF/portal-spring.xml",
    	        "META-INF/portlet-container-spring.xml",
    	        "META-INF/wsrp-spring.xml",
    	        
    	        "META-INF/mirage-spring.xml",
    	        
    	        //"META-INF/dynamic-data-source-spring.xml",
    	        //"META-INF/shard-data-source-spring.xml",
    	        
    	        "META-INF/ext-spring.xml"
    	        

    		//"META-INF/test-data-source-spring.xml", 
            //"META-INF/management-spring.xml",
            //"META-INF/hibernate-spring.xml",
            //"META-INF/base-spring.xml",
            //"META-INF/portal-spring.xml",
            //"META-INF/util-spring.xml",
    };
    
    
    
    ApplicationContext ac = new ClassPathXmlApplicationContext(configLocations);
    */	
    }
    
    private void prepareDebateServices() {
    	
    	DebatePersistenceImpl debatePersistence = new DebatePersistenceImpl();
        PortalHibernateConfiguration portalHibConf = new PortalHibernateConfiguration();
        
        SessionFactoryImpl sessionFactoryImpl = new SessionFactoryImpl();
        
        PortalHibernateConfiguration portalHibernateConfiguration = new PortalHibernateConfiguration();
        //org.hibernate.impl.SessionFactoryImpl hibSessionFactoryImpl = new org.hibernate.impl.SessionFactoryImpl(portalHibernateConfiguration.getConfiguration(), portalHibernateConfiguration.ge, arg2, arg3, arg4) 
        
       // portalHibernateConfiguration.
        //sessionFactoryImpl.setSessionFactoryImplementor();
        debatePersistence.setSessionFactory(sessionFactoryImpl);
        DebateLocalServiceImpl localSvcImpl = new DebateLocalServiceImpl();
        localSvcImpl.setDebatePersistence(debatePersistence);
        (new DebateLocalServiceUtil()).setService(localSvcImpl);
        
        EntityCacheImpl entityCache = new EntityCacheImpl();
        EhcachePortalCacheManager portalCacheManager = new EhcachePortalCacheManager();
        MultiVMPoolImpl multiVMPool = new MultiVMPoolImpl();
        
        portalCacheManager.setConfigPropertyKey("ehcache.multi.vm.config.location");

        portalCacheManager.setMBeanServer(new MBeanServerImpl());
        
        //portalCacheManager.set
        portalCacheManager.afterPropertiesSet();
        
        
        multiVMPool.setPortalCacheManager(portalCacheManager);
        entityCache.setMultiVMPool(multiVMPool);
        
        try {
			portalHibConf.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // SessionFactoryImpl sessionFactoryImpl = new SessionFactoryImpl();
    	
    	DebateLocalServiceImpl debateLocalServiceImpl = new DebateLocalServiceImpl();
    	DebateLocalServiceUtil debateLocalServiceUtil = new DebateLocalServiceUtil();
    	DebatePersistenceImpl debatePersistenceImpl = new DebatePersistenceImpl();
    	
    	DebateUtil debateUtil = new DebateUtil();
    	
    	debateLocalServiceImpl.setDebatePersistence(debatePersistenceImpl);
    	
    	debateLocalServiceUtil.setService(debateLocalServiceImpl);
    	
    }
    
    public void tearDown() {
    }
    
    public void testCos() throws PortalException, SystemException {
        Debate debate = DebateLocalServiceUtil.getDebate(101L);
        System.out.println(debate);
        
    }
    
 
}
