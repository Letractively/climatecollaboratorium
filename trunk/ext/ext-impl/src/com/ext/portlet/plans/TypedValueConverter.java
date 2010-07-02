package com.ext.portlet.plans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TypedValueConverter {
    private TypedValueConverter() {
        // this is utility class
    }
    
    private static DateFormat defaultDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    private static final String MULTI_VALUED_SEPARATOR = "\\|";
    
    public static Object getValue(Class<?> clasz, String value, String defaultVal) {
        Object convertedVal = null;
        boolean useDefault = false;
        if (clasz == String.class) {
            if (value != null) {
                convertedVal = value;
            } else {
                useDefault = true;
            }
            convertedVal = defaultVal != null && useDefault ? defaultVal : convertedVal;
        }
        else if (clasz == Double.class) {
            if (value.trim().length() != 0) { 
                try {
                    convertedVal = Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    useDefault = true;
                }
            }
            else {
                useDefault = true;
            }
            convertedVal = defaultVal != null && useDefault ? Double.parseDouble(defaultVal) : convertedVal;
        }
        else if (clasz == Integer.class){
            if (value.trim().length() != 0) { 
                try {
                    convertedVal = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    useDefault = true;
                }
            }
            else {
                useDefault = true;
            }
            convertedVal = defaultVal != null && useDefault ? Integer.parseInt(defaultVal) : convertedVal;
        }
        else if (clasz == Date.class) {
            if (value.trim().length() != 0) {
                try {
                    convertedVal = defaultDateFormat.parse(value);
                }
                catch (ParseException e) {
                    useDefault = true;
                }
            }
            else {
                useDefault = true;
            }
            try {
                convertedVal = defaultVal != null && useDefault ? defaultDateFormat.parseObject(defaultVal) : convertedVal;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return convertedVal;
    }
    
    public static Object getValue(Class<?> clasz, String value) {
        return getValue(clasz, value, null);
    }
    
    public static Object[] getValues(Class<?> clasz, String value) {
        if (value == null) {
            return new Object[0];
        }
        String[] values = value.split(MULTI_VALUED_SEPARATOR);
        Object[] ret = new Object[values.length];
        
        for (int i=0; i < values.length; i++) {
            ret[i] = getValue(clasz, values[i]);
        }
        return ret;
    }

    public static String getString(Object val) {
        if (val == null) {
            return "";
        }
        if (val instanceof Date) {
            return defaultDateFormat.format(val);
        }
        return val.toString();
    }
    
    public static String getStringForMultipleValues(Object[] values) {
        if (values == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < values.length; i++) {
            sb.append(getString(values[i]));
            if (i < values.length -1) {
                sb.append(MULTI_VALUED_SEPARATOR);
            }
        }
        return sb.toString();
    }
    
 

}
