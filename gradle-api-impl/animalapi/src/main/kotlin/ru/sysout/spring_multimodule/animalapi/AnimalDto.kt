package ru.sysout.spring_multimodule.animalapi

data class AnimalsDto(val  description: String, val animals: List<AnimalDto>)
data class AnimalDto(val id: String, val name: String)