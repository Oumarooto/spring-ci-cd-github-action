package com.maryam.spring_ci_cd_github_action;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringCiCdGithubActionApplicationTests {

	@Test
	void contextLoads() {
	}

}
