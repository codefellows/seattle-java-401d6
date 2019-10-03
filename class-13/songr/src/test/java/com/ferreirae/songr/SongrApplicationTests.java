package com.ferreirae.songr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SongrApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	// makes sure that your testing is set up correctly
	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetHomepageIsOkAndContainsHomeH1() throws Exception{
		this.mockMvc.perform(get("/")).andDo(print())

				.andExpect(view().name("home"))
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("<h1>Home</h1>")));
	}

	// set up with https://www.baeldung.com/integration-testing-in-spring


}
