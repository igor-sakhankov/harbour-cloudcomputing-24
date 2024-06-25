package com.harbour.harbourcloudcomputing24.cashwallet

import com.amazonaws.services.sqs.AmazonSQS
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SqsController(private val sqsClient: AmazonSQS) {

    @PostMapping("/sqs")
    fun sendSqsMessage() {
        val eventsQueueUrl = "queue-url"
        val sendMessage = sqsClient.sendMessage(eventsQueueUrl, "my message")
        println("Message ID: ${sendMessage.messageId}")
        while (true) {
            val messages = sqsClient.receiveMessage(eventsQueueUrl).messages
            for (message in messages) {
                println("Message: ${message.body}")
                sqsClient.deleteMessage(eventsQueueUrl, message.receiptHandle)
            }
            Thread.sleep(1000)
        }

    }

    fun processTransaction(transactionId: String) {
        saveDataInDatabaseTransactionally(transactionId)
        sendDataToAnalytics(transactionId)
    }

    private fun sendDataToAnalytics(transactionId: String) {
        println("failure")
    }

    private fun saveDataInDatabaseTransactionally(transactionId: String) {
        TODO("Not yet implemented")
    }
}