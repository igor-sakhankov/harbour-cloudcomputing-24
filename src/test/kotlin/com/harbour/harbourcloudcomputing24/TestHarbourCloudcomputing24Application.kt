package com.harbour.harbourcloudcomputing24

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<HarbourCloudcomputing24Application>().with(TestcontainersConfiguration::class).run(*args)
}
