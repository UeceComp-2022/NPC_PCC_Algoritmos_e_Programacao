package br.uece.sgv;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;


/**
 * https://github.com/roddramos/hexagonal_architecture_java/tree/master/src
 */
@SpringBootApplication
public class Main {
	
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Bean
    public DataSource dataSource() {
    	
    	//configuração do banco de dados
        return DataSourceBuilder
                .create()
                .driverClassName("org.sqlite.JDBC")
                .url("jdbc:sqlite:veiculo.db")
                .build();
        
    }
}
