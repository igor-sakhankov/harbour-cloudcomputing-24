package com.harbour.harbourcloudcomputing24.cashwallet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DynamoDbController(private val courierDataRepository: CourierDataRepository) {
    @GetMapping("/courier/{courierId}")
    fun getCourierData(@PathVariable("courierId") courierId: String): CourierData? {
        return courierDataRepository.findByCourierId(courierId)
    }
}