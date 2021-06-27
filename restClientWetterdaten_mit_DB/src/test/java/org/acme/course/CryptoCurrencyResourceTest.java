package org.acme.course;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CryptoCurrencyResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/client")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}