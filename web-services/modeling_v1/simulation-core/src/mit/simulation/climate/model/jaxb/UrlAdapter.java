package mit.simulation.climate.model.jaxb;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UrlAdapter {
	@XmlElement(name="url")
	String url;

	public UrlAdapter() {

	}

	public UrlAdapter(URL url) {
		this.url = url.toString();
	}

	public static class Adapter extends XmlAdapter<UrlAdapter,URL> {

		@Override
		public UrlAdapter marshal(URL arg0) throws Exception {
			return new UrlAdapter(arg0);
		}

		@Override
		public URL unmarshal(UrlAdapter arg0) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
