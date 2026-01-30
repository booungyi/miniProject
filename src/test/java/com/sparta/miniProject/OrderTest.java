package com.sparta.miniProject;

import com.sparta.miniProject.domain.product.Product;
import com.sparta.miniProject.dto.OrderCreateRequestDTO;
import com.sparta.miniProject.dto.ProductCreateRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class OrderTest {
    @Autowired
    private DatabaseCleanup databaseCleanup;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }
    //주문 생성 테스트
    @Test
    public void orderCreateTest() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO("상품1", 10000L, 100L))
                .when().post("/products").then().log().all().statusCode(200);

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new OrderCreateRequestDTO(1L, 20L))
                .when().get("/orders")
                .then().log().all().statusCode(200);
    }

    //주문 단건 조회 테스트
    @Test
    public void OrederGetTest() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO("상품1", 10000L, 100L))
                .when().post("/products").then().log().all().statusCode(200);

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new OrderCreateRequestDTO(1L, 20L))
                .when().post("/orders")
                .then().log().all().statusCode(200);

        RestAssured.given().log().all()
                .when().get("/orders")
                .then().log().all().statusCode(200);

    }

    //주문 삭제
    @Test
    public void orderPatchTest(){
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO("상품1", 10000L, 100L))
                .when().post("/products").then().log().all().statusCode(200);

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new OrderCreateRequestDTO(1L, 20L))
                .when().post("/orders")
                .then().log().all().statusCode(200);

        RestAssured.given().log().all()
                .when().patch("/orders/1")
                .then()
                .log().all().statusCode(200);

    }
}
