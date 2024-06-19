package com.harbour.harbourcloudcomputing24.cashwallet

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest
import java.util.Optional

@Repository
class CourierDataRepository(private val dbClient: DynamoDbEnhancedAsyncClient) {
    private lateinit var courierDataTable: DynamoDbAsyncTable<CourierData?>

    @PostConstruct
    fun initialize() {
        courierDataTable =
            dbClient.table(
                "courier-data",
                TableSchema.fromBean(CourierData::class.java)
            )
    }

    fun findByCourierId(courierId: String): CourierData?{
        return courierDataTable.getItem(Key.builder().partitionValue(courierId).build()).join()
    }

    fun update(courierId: String, name: String?) {
        val data = CourierData()
        data.courierId = courierId
        data.name = name

        courierDataTable
            .updateItem(
                UpdateItemEnhancedRequest.builder(CourierData::class.java)
                    .ignoreNulls(true)
                    .item(data)
                    .build()
            )
            .join()
    }
}
