package ru.sysout.spring_multimodule.api.animalclient

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestClient


@Configuration
class RestClientConfig @Autowired constructor(
    @Value("\${animalclient.url}") private val url: String
) {

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .requestFactory(HttpComponentsClientHttpRequestFactory())
            .baseUrl(url)
            .build()
    }
}