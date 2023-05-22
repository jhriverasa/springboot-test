package com.springapitest.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//env
import io.github.cdimascio.dotenv.Dotenv;

import java.lang.System;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		// dotenv config
		Dotenv dotenv = Dotenv.configure().load();

		// Define properties based on .env
		System.setProperty("DBPASSWORD", dotenv.get("DBPASSWORD"));
		System.setProperty("DBUSERNAME", dotenv.get("DBUSERNAME"));
		System.setProperty("DBHOST", dotenv.get("DBHOST"));
		System.setProperty("DBPORT", dotenv.get("DBPORT"));
		System.setProperty("DBNAME", dotenv.get("DBNAME"));

		// Starts app
		SpringApplication.run(RestapiApplication.class, args);
	}

}
