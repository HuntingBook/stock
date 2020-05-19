package com.caroline.smc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import com.caroline.smc.controller.UserController;
import com.caroline.smc.entity.User;
import com.caroline.smc.service.impl.UserService;

//@WebAppConfiguration
@RunWith(SpringRunner.class)
@RestClientTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;
	

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void listUsers() throws Exception {
        this.mvc.perform(get("/api/users").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
    
	@Test
	public void getUserById() throws Exception {
		User user = new User();
		user.setId(1l);
		user.setName("Caroline");
		Optional<User> userOptional = Optional.of(user);
		

        this.server.expect(requestTo("/api/users"))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        
    	Optional<User> greeting = this.userService.getUserById(1l);
        assertThat(greeting).isEqualTo("hello");

//		given(this.userService.getUserById(1l)).willReturn(userOptional);
//		this.mvc.perform(get("/api/users/1").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
//				.andExpect(content().string("Honda Civic"));
	}
}
