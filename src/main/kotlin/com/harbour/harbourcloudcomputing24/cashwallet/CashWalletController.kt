package com.harbour.harbourcloudcomputing24.cashwallet

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class CashWalletController {

    @PostMapping("/v1/wallet/transaction")
    fun createTransaction(@RequestHeader("X-requested-latency", required = false) latency:Long?, @RequestBody request: TransactionRequest): GenericResponse {
        if(latency != null) Thread.sleep(latency)
        return GenericResponse(TransactionResponse(UUID.randomUUID().toString(), "SUCCESS"))
    }
}

data class GenericResponse(
    val data: Any?,
    val error: String? = null
)

data class TransactionResponse(
    val transactionId: String,
    val status: String,
)

data class TransactionRequest(
    val amount: Double,
    val currency: String,
    val description: String,
    val userId: String,
)
