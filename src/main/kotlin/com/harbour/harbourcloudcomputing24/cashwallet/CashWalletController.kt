package com.harbour.harbourcloudcomputing24.cashwallet

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class CashWalletController {

    @PostMapping("/v1/wallet/transaction")
    fun createTransaction(@RequestBody request: TransactionRequest): GenericResponse {
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
