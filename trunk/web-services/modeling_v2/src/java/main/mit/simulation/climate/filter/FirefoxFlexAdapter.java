package mit.simulation.climate.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Fixes two known issues:
 * <ul>
 * <li>Intel Mac Firefox 3 accept header incorrectly prepends "HTTP Accept=" to the accept header
 * string, causing Jersey to break
 * <li>Flex HTTPService cannot handle non-200 response codes from the server
 * </ul>
 */
public class FirefoxFlexAdapter implements Filter {

    private static Logger logger = Logger.getLogger(FirefoxFlexAdapter.class);

    /**
     * Default constructor.
     */
    public FirefoxFlexAdapter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        dumpRequest(req);
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {

            public String getHeader(String name) {
                if ("accept".equals(name)) {
                    return firefoxAcceptHeaderFix(super.getHeader(name));
                }
                return super.getHeader(name);
            }

            public Enumeration getHeaders(String name) {
                if ("accept".equals(name)) {
                    final Enumeration acceptenum = super.getHeaders("accept");
                    return new Enumeration() {

                        @Override
                        public boolean hasMoreElements() {
                            return acceptenum.hasMoreElements();
                        }

                        @Override
                        public Object nextElement() {
                            Object o = acceptenum.nextElement();
                            if (o instanceof String) {
                                return firefoxAcceptHeaderFix((String)o);
                            } else {
                                return o;
                            }

                        }

                    };
                }
                return super.getHeaders(name);
            }

        };

        chain.doFilter(wrapper, response);
    }

    private String firefoxAcceptHeaderFix(String header) {

        return header.replaceAll("^\\s*HTTP\\sAccept\\=", "");
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void dumpRequest(HttpServletRequest req) {
		logger.info("Request for "+req.getRequestURL());
		logger.info("Request type "+req.getMethod());
        for (Enumeration e = req.getHeaderNames();e.hasMoreElements();) {
            String elt = e.nextElement().toString();
			logger.info(elt+" = "+req.getHeader(elt));
        }

    }

}
