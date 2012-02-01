import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	static {
		System.err.println("SIM-LISTENER: static initialiser called");
	}


	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.err.println("SIM-SESSION-LISTENER:Session created "+arg0.getSession().getId());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.err.println("SIM-SESSION-LISTENER:"+arg0.getSession().getId());

	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.err.println("SIM-SESSION-LISTENER(added binding):"+arg0.getName());

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.err.println("SIM-SESSION-LISTENER(removed binding):"+arg0.getName());

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.err.println("SIM-SESSION-LISTENER(replace binding):"+arg0.getName());

	}

}
