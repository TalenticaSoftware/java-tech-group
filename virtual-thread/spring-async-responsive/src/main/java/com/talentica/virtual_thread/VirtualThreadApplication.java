package com.talentica.virtual_thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VirtualThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadApplication.class, args);
	}

}
