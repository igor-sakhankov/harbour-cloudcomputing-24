package com.harbour.harbourcloudcomputing24.cashwallet

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.assertj.core.api.Assertions.assertThat


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashWalletControllerTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun createTransaction() {
        assertThat(
            restTemplate.postForEntity(
                "http://localhost:$port/v1/wallet/transaction",
                TransactionRequest(100.0, "USD", "Hello, World", "user-1"),
                GenericResponse::class.java
            ).statusCode.is2xxSuccessful
        )
    }
}