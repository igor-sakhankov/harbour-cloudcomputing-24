package com.harbour.harbourcloudcomputing24

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class HarbourCloudcomputing24ApplicationTests {

    @Test
    fun contextLoads() {
    }

}
