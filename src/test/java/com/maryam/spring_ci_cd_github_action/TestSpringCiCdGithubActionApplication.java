package com.maryam.spring_ci_cd_github_action;

import org.springframework.boot.SpringApplication;

public class TestSpringCiCdGithubActionApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringCiCdGithubActionApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
