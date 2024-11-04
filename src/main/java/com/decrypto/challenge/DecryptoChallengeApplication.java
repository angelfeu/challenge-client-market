package com.decrypto.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties
public class DecryptoChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecryptoChallengeApplication.class, args);
	}

}
