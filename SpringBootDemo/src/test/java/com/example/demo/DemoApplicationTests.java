package com.example.demo;

import com.example.demo.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl() + "/users", HttpMethod.GET, entity, String.class);
        System.out.println("getAllUsers() response : " + response);

        assertNotNull(response.getBody());
    }

    @Test
    public void getUserById() {
        User user = restTemplate.getForObject(getBaseUrl() + "/users/1", User.class);
        System.out.println("getUserById() response : " + user);
        assertNotNull(user);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setFirstName("Rishi");
        user.setLastName("Bhalla");
        user.setEmail("test@gmail.com");

        ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl() + "/users", user, User.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
    }

    @Test
    public void updateUser() {
        int id = 1;
        User user = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        user.setFirstName("admin");
        user.setLastName("admin");

        restTemplate.put(getBaseUrl() + "/users/" + id, user);

        User updatedUser = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void deleteUser() {
        int id = 2;
        User user = restTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        assertNotNull(user);

        restTemplate.delete(getBaseUrl() + "/users/" + id);

        try {
            user = restTemplate.getForObject(getBaseUrl() + "/users" + id, User.class);
        } catch (HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }

    }
}

