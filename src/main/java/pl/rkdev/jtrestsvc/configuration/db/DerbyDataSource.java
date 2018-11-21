package pl.rkdev.jtrestsvc.configuration.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Profile("development")
@Configuration
public class DerbyDataSource {

	@Bean
	public DataSource embeddedDerbyDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.DERBY)
				.setScriptEncoding("UTF-8")
				.addScript("db/sql/create-db.sql")
				.addScript("db/sql/insert-data.sql")
				.build();		
	}
}
