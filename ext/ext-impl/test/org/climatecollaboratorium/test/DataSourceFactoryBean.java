package org.climatecollaboratorium.test;

import com.liferay.portal.kernel.jndi.JNDIUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.util.Enumeration;
import java.util.Properties;

import javax.naming.InitialContext;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.beans.factory.config.AbstractFactoryBean;
public class DataSourceFactoryBean extends AbstractFactoryBean {

	public Class<?> getObjectType() {
		return DataSource.class;
	}

	public void setPropertyPrefix(String propertyPrefix) {
		_propertyPrefix = propertyPrefix;
	}

	protected Object createInstance() throws Exception {
		Properties properties = PropsUtil.getProperties(_propertyPrefix, true);
		/*

		String jndiName = properties.getProperty("jndi.name");

		if (Validator.isNotNull(jndiName)) {
			try {
				return JNDIUtil.lookup(new InitialContext(), jndiName);
			}
			catch (Exception e) {
				_log.error("Unable to lookup " + jndiName, e);
			}
		}
*/

		DataSource dataSource = new ComboPooledDataSource();
        BeanUtils.setProperty(dataSource, "driverClass", "com.mysql.jdbc.Driver");
        BeanUtils.setProperty(dataSource, "jdbcUrl", "jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false");
        BeanUtils.setProperty(dataSource, "user", "liferay");
        BeanUtils.setProperty(dataSource, "password", "liferay");
/*
		dataSource.
		Enumeration<String> enu =
			(Enumeration<String>)properties.propertyNames();

		while (enu.hasMoreElements()) {
			String key = enu.nextElement();

			String value = properties.getProperty(key);

			// Map org.apache.commons.dbcp.BasicDataSource to C3PO

			if (key.equalsIgnoreCase("driverClassName")) {
				key = "driverClass";
			}
			else if (key.equalsIgnoreCase("url")) {
				key = "jdbcUrl";
			}
			else if (key.equalsIgnoreCase("username")) {
				key = "user";
			}

			BeanUtils.setProperty(dataSource, key, value);
		}
*/
		if (_log.isDebugEnabled()) {
			SortedProperties sortedProperties = new SortedProperties(
				properties);

			_log.debug("Properties for prefix " + _propertyPrefix);

			sortedProperties.list(System.out);
		}

		return dataSource;
	}

	private static Log _log =
		 LogFactoryUtil.getLog(DataSourceFactoryBean.class);

	private String _propertyPrefix;

}