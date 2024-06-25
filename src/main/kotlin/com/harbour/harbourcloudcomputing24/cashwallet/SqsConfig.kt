package com.harbour.harbourcloudcomputing24.cashwallet

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SqsConfig {

  @Bean
  fun amazonSQSWorker(
    @Value("\${aws.region}") region: String
  ): AmazonSQS {
    return AmazonSQSAsyncClientBuilder.standard()
      .withRegion(region)
      .build()
  }
}
