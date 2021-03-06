package com.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.entity.UserDetail;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {

    @LocalServerPort
    public int testPort;

    @Autowired
    public TestRestTemplate testRestTemplate;

    @Test
    public void getMetaShouldReturnLiveData() {
        // full round trip with live data
        String url = testPort + "/posts/meta";
        assertThat(testRestTemplate.getForObject(url, String.class)).contains("users");
    }

    @Test
    public void patchPostShouldReturnLiveData() {
        // full round trip with live data
        String url = testPort + "/posts/4";
        UserDetail post = new UserDetail();
        post.setTitle("1800Flowers");
        post.setBody("1800Flowers");
        assertThat(testRestTemplate.patchForObject(url, post, String.class)).contains("1800Flowers");
    }

}
