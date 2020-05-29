package br.com.agrosoftware.agrosoftware.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.agrosoftware.agrosoftware.services.DBService;

@Configuration
@Profile(value = {"dev", "test"})
public class TestConfig {
    
    @Autowired
    private DBService dbService;
    
    @Bean
    public boolean instantiateDatabase() throws Exception {
        dbService.instantiateDatabase();
        return true;
    }

}
