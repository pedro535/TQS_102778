package pt.ua.tqs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PublicRestServicesTest {

    private String URL = "https://jsonplaceholder.typicode.com/todos";


    @Test
    @DisplayName("Test if the endpoint to list all Todos is available (status code 200)")
    void testAvailability() {
        given().get(URL)
            .then().assertThat().statusCode(200);
    }


    @Test
    @DisplayName("Test if when querying for Todo #4, the API returns an object with title “et porro tempora")
    void testQueryingTodo4() {
        int todoNum = 4;

        given().get(URL + "/" + todoNum)
            .then().assertThat().statusCode(200)
            .and().body("id", equalTo(4))
            .and().body("title", equalTo("et porro tempora"));
    }


    @Test
    @DisplayName("When listing all “todos”, you get id #198 and #199 in the results.")
    void testIfResultInLessThan2Secs() {
        given().get(URL)
            .then().assertThat().statusCode(200)
            .and().body("id", hasItems(198, 199));
    }


    @Test
    @DisplayName("Test if when listing all “todos”, you the results in less then 2secs")
    void testIf198And199Exist() {
        long seconds = 2;

        given().get(URL)
            .then().assertThat().statusCode(200)
            .and().time(lessThan(seconds), TimeUnit.SECONDS);
    }

}
