package co.edu.icesi.dev.petscued.model

import java.util.UUID

class Publication {

    companion object{
        const val MALE = "Macho"
        const val FEMALE = "Hembra"
        const val IN_ADOPTION = "En adopción"
        const val LOST = "Perdido"
        const val VACCINATED = "SÍ"
        const val NON_VACCINATED = "NO"
    }

    var id: UUID
    var name: String
    var breed: String
    var sex: String
    var owner: String
    var type: String
    var status: String
    var location: String
    var age: String
    var color: String
    var description: String
    var contactInformation: String
    var vaccinated: String

    constructor(
        id: UUID,
        name: String,
        breed: String,
        sex: String,
        owner: String,
        type: String,
        status: String,
        location: String,
        age: String,
        color: String,
        description: String,
        contactInformation: String,
        vaccinated: String
    ) {
        this.id = id
        this.name = name
        this.breed = breed
        this.sex = sex
        this.owner = owner
        this.type = type
        this.status = status
        this.location = location
        this.age = age
        this.color = color
        this.description = description
        this.contactInformation = contactInformation
        this.vaccinated = vaccinated
    }
}