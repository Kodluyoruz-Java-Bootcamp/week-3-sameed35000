package emlakcepte;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import emlakcepte.service.UserService;



@Configuration

public class BeanConfig {
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	

}
