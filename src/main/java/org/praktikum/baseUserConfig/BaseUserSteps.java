package org.praktikum.baseUserConfig;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.pojo.UserRequest;
import static io.restassured.RestAssured.given;


public class BaseUserSteps extends BaseUserRequest {
    private static final String BASE_PATH_DELETE = "/api/auth/user";
    private static final String BASE_PATH_LOGIN = "/api/auth/login";
    @Step("Авторизация клиента. Email: {userRequest.email}")
    public ValidatableResponse loginUser(UserRequest userRequest) {
        return given().spec(getDefaultRequestSpec()).body(userRequest).post(BASE_PATH_LOGIN).then();
    }

    @Step("Удаление пользователя. Email: {token}")
    public ValidatableResponse deleteUser(String token) {
        return given().spec(getDefaultRequestSpec()).header("Authorization", token).delete(BASE_PATH_DELETE).then();
    }
}
