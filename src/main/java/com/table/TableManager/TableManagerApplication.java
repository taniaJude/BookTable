package com.table.TableManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.table.TableManager"})
public class TableManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TableManagerApplication.class, args);
	}

}
