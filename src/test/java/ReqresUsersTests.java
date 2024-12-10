import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Проверка ручек из категории Users")
public class ReqresUsersTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    @DisplayName("Проверка одного пользователя (#2)")
    public void singleUserTest() {
        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
        .when()
            .get("/users/2")
        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("data.id", is(2))
            .body("data.email", is("janet.weaver@reqres.in"))
            .body("data.first_name", is("Janet"))
            .body("data.last_name", is("Weaver"))
            .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"));
        // @formatter:on
    }

    @Test
    @DisplayName("Проверка несуществующего пользователя")
    public void invalidSingleUserTest() {
        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
        .when()
            .get("/users/23")
        .then()
            .log().status()
            .log().body()
            .statusCode(404);
        // @formatter:on
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void createUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .post("/users")
        .then()
            .log().status()
            .log().body()
            .statusCode(201)
            .body("name", is("morpheus"))
            .body("job", is("leader"));
        // @formatter:on
    }

    @Test
    @DisplayName("Обновление параметров пользователя (метод PUT)")
    public void updateSingleUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .put("/users/2")
        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", is("morpheus"))
            .body("job", is("zion resident"));
        // @formatter:on
    }

    @Test
    @DisplayName("Обновление всех параметров пользователя (метод PATCH)")
    public void updateFieldsSingleUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .patch("/users/2")
        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", is("morpheus"))
            .body("job", is("zion resident"));
        // @formatter:on
    }

    @Test
    @DisplayName("Обновление параметра имени пользователя")
    public void updateFieldNameSingleUserTest() {
        String data = "{ \"name\": \"neo\" }";

        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .patch("/users/2")
        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("name", is("neo"));
        // @formatter:on
    }

    @Test
    @DisplayName("Обновление параметра занятия пользователя")
    public void updateFieldJobSingleUserTest() {
        String data = "{ \"job\": \"plumber\" }";

        // @formatter:off
        given()
            .log().uri()
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .patch("/users/2")
        .then()
            .log().status()
            .log().body()
            .statusCode(200)
            .body("job", is("plumber"));
        // @formatter:on
    }
}
