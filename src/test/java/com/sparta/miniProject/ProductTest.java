package com.sparta.miniProject;

import com.sparta.miniProject.dto.ProductCreateRequestDTO;
import com.sparta.miniProject.dto.ProductCreateResponseDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @Autowired
    private DatabaseCleanup databaseCleanup;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }
    //상품 생성 테스트
    @Test
    public void createProduct() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO(
                        "상품1", 125500L, 20L))
                .when().post("/products").then().log().all().statusCode(200);
    }
    //상품 단건 조회 테스트
    @Test
    public void GetOneProductTest() {
        // 상품 생성
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO("상품1", 1000L, 15L))
                .when()
                .post("/products")
                .then()
                .statusCode(200);

        // 상품 조회
        RestAssured.given()
                .when()
                .get("/products/1")
                .then()
                .statusCode(200);
    }

    //상품 목록 조회 페이징 확인
    @Test
    public void GetProductListTest() {
        // given - 상품 3개 생성
        for (int i = 1; i <= 3; i++) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ProductCreateRequestDTO("상품" + i, 1000L * i, 10L))
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(200);
        }

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then().log().all()
                .statusCode(200);

        RestAssured.given().log().all()
                .param("page", 1)
                .param("size", 1)
                .when()
                .get("products").then()
                .log().all().statusCode(200);

    }
    //상품 수정 테스트
    @Test
    public void PatchProductTest() {
        // given - 상품 3개 생성
        for (int i = 1; i <= 3; i++) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ProductCreateRequestDTO("상품" + i, 1000L * i, 10L))
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(200);
        }
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new ProductCreateRequestDTO("수정된상품1", 110000000L, 1L))
                .when().patch("/products/1")
                .then().log().all().statusCode(200);
    }

    //상품 삭제 테스트 (softDelete)
    @Test
    public void DeleteProductTest() {
        // given - 상품 3개 생성
        for (int i = 1; i <= 3; i++) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ProductCreateRequestDTO("상품" + i, 1000L * i, 10L))
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(200);
        }
        RestAssured.given().log().all()
                .when().patch("/products/delete/1")
                .then().log().all().statusCode(200);
    }
}