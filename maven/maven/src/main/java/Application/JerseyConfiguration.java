package Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("API")
@Configuration
public class JerseyConfiguration extends ResourceConfig {
	
	public JerseyConfiguration() {
		register(FilieresRessources.class);
	}
}