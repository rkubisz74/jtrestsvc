package pl.rkdev.jtrestsvc.configuration.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("uat")
@Configuration
public class MySqlDataSource {

	@Bean
	public DataSource getMySqlDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jtrestsvc?serverTimezone=Europe/Warsaw");
		ds.setUsername("jtrestsvc_app");
		ds.setPassword("x2BfgPa5Txb99zSh");
		return ds;		
	}
}
