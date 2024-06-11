package com.harbour.harbourcloudcomputing24.cashwalletclient

import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.core5.util.TimeValue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration
internal class CashWalletClientConfiguration {

    @Bean
    fun cashWalletClient(): RestClient {
        val httpClient: CloseableHttpClient = defineClient()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestClient.builder()
            .requestFactory(factory)
            .baseUrl("http://localhost:8181")
            .defaultHeader("X-requested-latency", "50000")
            .build()
    }

    private fun defineClient(): CloseableHttpClient {
        val requestConfig = RequestConfig.custom()
            .build()

        val retryStrategy = DefaultHttpRequestRetryStrategy(3, TimeValue.ofSeconds(1))

        val connectionManager = PoolingHttpClientConnectionManager().apply {
            maxTotal = 100
            defaultMaxPerRoute = 20
        }

        val httpClient: CloseableHttpClient = HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(connectionManager)
            .setRetryStrategy(retryStrategy)
            .build()
        return httpClient
    }
}

data class GenericResponse(
    val data: TransactionResponse?,
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