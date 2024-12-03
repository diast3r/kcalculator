package com.kcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //스케줄링을 해줌.
@SpringBootApplication
public class KcalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KcalculatorApplication.class, args);
	}

}
