package com.ext.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;

public class FileUploadServlet extends HttpServlet  {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		InputStream is = null;
    	try {
    		if (ServletFileUpload.isMultipartContent(request)) {
    			ServletFileUpload fileUpload = new ServletFileUpload();
    			FileItemIterator items = fileUpload.getItemIterator(request);
    	    
    	    
    			while (items.hasNext()) {
    				FileItemStream item = items.next();

    				
    				if (!item.isFormField() && item.getContentType().contains("image")) {
        				// currently we support only images
    					long imageId = CounterLocalServiceUtil.increment(Image.class.getName());
    					//
    					//
    					is = item.openStream();
    					
    					Image img = ImageLocalServiceUtil.getImage(IOUtils.toByteArray(is));
    					
    					img.setImageId(imageId);
    					img.setModifiedDate(new Date());
    					ImageLocalServiceUtil.addImage(img);
    					// return JSON with image id
    					response.getWriter().append("{\"imageId\": " + img.getPrimaryKey() + "}");
    					response.getWriter().close();
    				}
    			}
    		}
    	}
    	catch (IOException e) {
    		throw new ServletException(e);
    	} catch (FileUploadException e) {
    		throw new ServletException(e);
		} catch (SystemException e) {
    		throw new ServletException(e);
		}
    	finally  {
    		if (is != null) {
    			try {
    				is.close();
    			}
    			catch (IOException e) {
    				// it's bad but ignore that
    			}
    		}
    	}
    	
    }
}
