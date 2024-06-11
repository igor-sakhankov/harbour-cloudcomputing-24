package com.harbour.harbourcloudcomputing24.cashwalletclient

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class CashWalletClientController(
    private val cashWalletClient: RestClient,
    private val cbFactory: CircuitBreakerFactory<*, *>
) {

    @PostMapping("/client/transaction")
    fun createTransaction(@RequestBody request: TransactionRequest): ResponseEntity<GenericResponse> {
        val toEntity = cashWalletClient.post()
            .uri("http://localhost:8181/v1/wallet/transaction")
            .body(request)
            .retrieve().toEntity(GenericResponse::class.java)

        print("Transaction created")
        return toEntity
    }

    @PostMapping("/client/transaction/cb")
    fun createTransactionWithCB(@RequestBody request: TransactionRequest): ResponseEntity<GenericResponse> {
        cbFactory.create("cash-wallet-client").run {
            {
                cashWalletClient.post()
                    .uri("http://localhost:8181/v1/wallet/transaction")
                    .body(request)
                    .retrieve().toEntity(GenericResponse::class.java)
            }
        }
        return cashWalletClient.post()
            .uri("http://localhost:8181/v1/wallet/transaction")
            .body(request)
            .retrieve().toEntity(GenericResponse::class.java)
    }
}