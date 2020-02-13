package com.mailsender.mailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mailsender")
public class MailsenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailsenderApplication.class, args);
	}

}
