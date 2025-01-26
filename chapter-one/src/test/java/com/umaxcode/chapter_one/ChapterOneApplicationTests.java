package com.umaxcode.chapter_one;

import com.umaxcode.chapter_one.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChapterOneApplicationTests {

    private final String BASE_PATH = "http://localhost:";
    private final String USERS_PATH = "/users";
    // You can equally use the test specific annotation @LocalServerPort
    @Value("${local.server.port}")
    private int port;

    @LocalServerPort
    private int tryPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void indexPageShouldReturnHeaderOneContent() {
        System.out.println(port);
        System.out.println(tryPort);
        assertThat(this.restTemplate.getForObject(BASE_PATH + port,
                String.class)).contains("Simple Users Rest Application");
    }

    @Test
    public void usersEndPointShouldReturnCollectionWithTwoUsers() {
        Collection<User> response = this.restTemplate.
                getForObject(BASE_PATH + port + USERS_PATH,
                        Collection.class);
        assertThat(response.size()).isEqualTo(2);
    }

    @Test
    public void userEndPointPostNewUserShouldReturnUser() throws
            Exception {
        User user = new User("dummy@email.com", "Dummy");
        User response = this.restTemplate.postForObject(BASE_PATH + port +
                USERS_PATH, user, User.class);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(user.getEmail());
        Collection<User> users = this.restTemplate.
                getForObject(BASE_PATH + port + USERS_PATH,
                        Collection.class);
        assertThat(users.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void userEndPointDeleteUserShouldReturnVoid() throws Exception {
        this.restTemplate.delete(BASE_PATH + port + USERS_PATH + "/first@gmail.com");
        Collection<User> users = this.restTemplate.
                getForObject(BASE_PATH + port + USERS_PATH,
                        Collection.class);
        assertThat(users.size()).isLessThanOrEqualTo(2);
    }

    @Test
    public void userEndPointFindUserShouldReturnUser() throws Exception {
        User user = this.restTemplate.getForObject(BASE_PATH + port + USERS_PATH + "/second@gmail.com", User.class);
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("second@gmail.com");
    }
}
