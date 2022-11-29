package co.edu.icesi.dev.petscued.model

data class Comment (
    var id: String = "",
    var content: String = "",
    var userId: String = "",
    var publicationId : String = ""
)