package com.jessica.codefellowship;

import com.jessica.codefellowship.applicationUsers.ApplicationUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodefellowshipApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	ApplicationUserController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testLogin() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login", String.class)).contains("login");
	}

	@Test
	public void testSignup() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/signup", String.class)).contains("signup");
	}
}

