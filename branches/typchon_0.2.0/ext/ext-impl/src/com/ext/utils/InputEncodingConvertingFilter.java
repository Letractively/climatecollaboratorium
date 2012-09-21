package com.ext.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import edu.emory.mathcs.backport.java.util.Collections;

public class InputEncodingConvertingFilter implements Filter {
	
	private final static Map<String, String> conversionMap = new HashMap<String, String>();
	static {
		conversionMap.put("â", "'");
		conversionMap.put("â", "\"");
		conversionMap.put("â", "\"");
		
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if (arg0 instanceof HttpServletRequest) {
			
			HttpServletRequest req = (HttpServletRequest) arg0;
			Map<String, String[]> newParameters = new HashMap<String, String[]>(req.getParameterMap());
			boolean badDetected = false;
			
			if (req.getMethod().equals("POST")) {
				// iterate over parameters and check if they contain invalid characters
				for (Object key: req.getParameterMap().keySet()) {
					Object valueObj = req.getParameterMap().get(key);
					String[] valuesToConvert = null;
					if (valueObj.getClass().isAssignableFrom(String.class)) {
						// do convert
						String valueStr = (String) valueObj;
						valuesToConvert = new String[] { valueStr };
					}
					else if (valueObj.getClass().isAssignableFrom(String[].class)) {
						// do convert for every value
						valuesToConvert = (String[]) valueObj;
					}
					boolean badCharacterDetected = false;
					for (int i=0; i < valuesToConvert.length; i++) {
						String newVal = convertBadCharacters(valuesToConvert[i]);
						if (newVal != null) {
							badCharacterDetected = true;
							valuesToConvert[i] = newVal;
						}
					}
					if (badCharacterDetected) {
						badDetected = true;
						newParameters.put(key.toString(), valuesToConvert);
					}
				}
			}

			if (badDetected) {
				arg2.doFilter(new ParameterAlteringRequestWrapper(req, newParameters), arg1);
				return;
			}
		}
		arg2.doFilter(arg0, arg1);

	}

	private String convertBadCharacters(String string) {
		String newString = null;
		for (Map.Entry<String, String> charMapping: conversionMap.entrySet()) {
			if (string.indexOf(charMapping.getKey()) >= 0) {
				if (newString == null) newString = string;
				newString = newString.replaceAll(charMapping.getKey(), charMapping.getValue());
			}
		}
		return newString;
	}
		
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	public static class ParameterAlteringRequestWrapper extends HttpServletRequestWrapper {
		private Map<String, String[]> parameters = null;
		
		@Override
		public String getParameter(String name) {
			if (parameters != null) {
				String[] val = parameters.get(name);
				if (val != null && val.length > 0) return val[0];
			}
			return super.getParameter(name);
		}

		@Override
		public Map getParameterMap() {
			return parameters != null ? parameters : super.getParameterMap();
		}

		@Override
		public Enumeration getParameterNames() {
			return parameters != null ? Collections.enumeration(parameters.keySet()) : super.getParameterNames();
		}

		@Override
		public String[] getParameterValues(String name) {
			return parameters != null ? parameters.get(name) : super.getParameterValues(name);
		}

		
		public ParameterAlteringRequestWrapper(HttpServletRequest request, Map<String, String[]> parameters) {
			super(request);
			this.parameters = parameters;
		}
		
		
		
	}

}
