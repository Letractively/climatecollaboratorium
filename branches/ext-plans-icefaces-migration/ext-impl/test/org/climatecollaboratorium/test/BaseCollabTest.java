package org.climatecollaboratorium.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaProps;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.log.Log4jLogFactoryImpl;
import com.liferay.portal.util.InitUtil;
import com.liferay.util.SystemProperties;
import com.liferay.util.log4j.Log4JUtil;

import junit.framework.TestCase;

public abstract class BaseCollabTest extends TestCase {
    private static boolean _initialized;
    private static Object mutex = new Object();

    public BaseCollabTest() {
        synchronized (mutex) {
            if (!_initialized) {
                initializeWithOwnSpring();
                _initialized = true;
            }
        }
    }

    private void initializeWithOwnSpring() {

        long start = System.currentTimeMillis();

        // Set the default locale used by Liferay. This locale is no longer set
        // at the VM level. See LEP-2584.

        String userLanguage = SystemProperties.get("user.language");
        String userCountry = SystemProperties.get("user.country");
        String userVariant = SystemProperties.get("user.variant");

        LocaleUtil.setDefault(userLanguage, userCountry, userVariant);

        // Set the default time zone used by Liferay. This time zone is no
        // longer set at the VM level. See LEP-2584.

        String userTimeZone = SystemProperties.get("user.timezone");

        TimeZoneUtil.setDefault(userTimeZone);

        // Shared class loader

        try {
            Thread currentThread = Thread.currentThread();

            PortalClassLoaderUtil.setClassLoader(currentThread.getContextClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Log4J

        if (GetterUtil.getBoolean(SystemProperties.get("log4j.configure.on.startup"), true)) {

            ClassLoader classLoader = InitUtil.class.getClassLoader();

            Log4JUtil.configureLog4J(classLoader.getResource("META-INF/portal-log4j.xml"));
            Log4JUtil.configureLog4J(classLoader.getResource("META-INF/portal-log4j-ext.xml"));
        }

        // Shared log

        try {
            LogFactoryUtil.setLogFactory(new Log4jLogFactoryImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuration factory

        ConfigurationFactoryUtil.setConfigurationFactory(new ConfigurationFactoryImpl());

        // Java properties

        JavaProps.isJDK5();

        String[] configLocations = { 
                "META-INF/test-data-source-spring.xml", 
                "META-INF/hibernate-spring.xml",
                "META-INF/util-spring.xml", 
                "META-INF/base-spring.xml", 
                "META-INF/management-spring.xml",

                "META-INF/lock-spring.xml", 
                "META-INF/document-library-spring.xml", 
                "META-INF/mail-spring.xml",
                "META-INF/scheduler-spring.xml", 
                "META-INF/counter-spring.xml", 
                "META-INF/portal-spring.xml",
                "META-INF/ext-spring.xml", 
                };

        new ClassPathXmlApplicationContext(configLocations);
        ConfigurationFactoryUtil.setConfigurationFactory(new ConfigurationFactoryImpl());

        System.out.println("Initialization took: " + (System.currentTimeMillis() - start) + " ms");
    }
}
