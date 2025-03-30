package com.synergy.backend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.synergy.backend.global.util.JwtBatchGenerator;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SynergyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynergyBackendApplication.class, args);
	}
	// @Autowired
	// private JwtBatchGenerator jwtBatchGenerator;
	// @PostConstruct
	// public void init() throws IOException {
	// 	jwtBatchGenerator.generateTokensToCsv();
	// }
}
