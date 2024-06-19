package com.harbour.harbourcloudcomputing24.cashwallet

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
class CourierData(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("CourierId")
    var courierId: String = "",

    @get:DynamoDbAttribute("Name")
    var name: String? = null
)