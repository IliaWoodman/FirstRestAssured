package yandex.translate.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import yandex.translate.EndPointUrlYandexTranslate;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class YandexTranslateTest {
    private static final String URL_KEY = "https://translate.yandex.net/api/v1.5/tr.json";
    private static final String API_KEY = "trnsl.1.1.20200402T172301Z.a33dd8696820daa1.087d5e0c619f5fa7af598e178334fccae576c195";
    @Test
    public void firstTest(){
        RestAssured.baseURI = URL_KEY;

        given()
 //               .header("User-Agent", "Mozilla")  // эти параметры по необходимости
  //              .header("secret_key", "superpassword")
                 .when() // отправляем запрос к baseURI  и добавляем Эндпоинты...в then задаем критерии проверки ответа
                        .get(EndPointUrlYandexTranslate.TRANSLATE.addPath(String.format("?key=%s&lang=%s&text=%s",API_KEY,"ru-en","Привет мир!")))
                 .then()
                        .statusCode(HTTP_OK)
                        .body("text", hasItem("Hello world!"))
                        .body("lang", equalTo("ru-en"))
                        .body("code", equalTo(200));
    }
    @Test
    public void secondTest(){
        RestAssured.baseURI = URL_KEY;

        given()
                .when()
                        .get(EndPointUrlYandexTranslate.TRANSLATE.addPath(String.format("?key=%s&lang=%s&text=%s",API_KEY,"en-ru","Hello world!")))
                .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .body("text", hasItem("Всем привет!"))
                        .body("lang",equalTo("en-ru"));

    }
    @Test
    public void thirdTest(){
        RestAssured.baseURI = URL_KEY;
        given()
                .when()
                    .get(EndPointUrlYandexTranslate.DEFINITION_LANGUAGE.addPath(String.format("?key=%s&text=%s",API_KEY,"Hello")))
                .then()
                    .statusCode(200)
                    .time(lessThan(4000L))
                    .contentType("application/json")
                    .body("code",equalTo(200),
                        "lang",equalTo("en"));

    }

    @Test
    public void fourthTest(){
        RestAssured.baseURI = URL_KEY;
        given()
                .when()
                    .get(EndPointUrlYandexTranslate.DEFINITION_LANGUAGE.addPath(String.format("?key=%s&text=%s",API_KEY,"こんにちは")))
                .then()
                    .statusCode(200)
                    .time(lessThan(4000L))
                    .contentType("application/json")
                    .body("code",equalTo(200),
                        "lang",equalTo("ja"));
    }

    @Test
    public void fifthTest(){
        RestAssured.baseURI = URL_KEY;
        given()
                .when()
                    .get(EndPointUrlYandexTranslate.GET_LIST_LANGUAGES.addPath(String.format("?key=%s",API_KEY)))
                .then()
                    .body("dirs",hasItem("az-ru"))
                    .body("dirs.collect{it.length() }.sum()",equalTo(800)); // проверяем что в массиве dirs 800 символов
    }

}
