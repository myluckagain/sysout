package ru.sysout.spring_multimodule.api.animalclient

import org.springframework.http.MediaType
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

import ru.sysout.spring_multimodule.animalapi.AnimalsDto

class AnimalClient(
    val restClient: RestClient,
) {
    fun getAnimals(): AnimalsDto? {
        return restClient.get()
            .uri("/animals")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body<AnimalsDto>()
    }
}