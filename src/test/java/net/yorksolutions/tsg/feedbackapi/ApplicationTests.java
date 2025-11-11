package net.yorksolutions.tsg.feedbackapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

//TODO might need to remove this @Disabled once db is setup
@Disabled("Skipping until database configuration is stable")

class ApplicationTests {
    @Test
    void contextLoads() {
    }
}

