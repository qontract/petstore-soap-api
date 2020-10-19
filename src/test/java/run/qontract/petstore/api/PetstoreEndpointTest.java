package run.qontract.petstore.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import run.qontract.test.QontractJUnitSupport;

public class PetstoreEndpointTest extends QontractJUnitSupport {
    static ConfigurableApplicationContext context;

    @BeforeAll
    public static void setUp() {
        System.setProperty("host", "localhost");
        System.setProperty("port", "8080");

        context = SpringApplication.run(PetstoreApplication.class);
    }

    @AfterAll
    public static void tearDown() {
        if(context != null)
            context.stop();
    }
}