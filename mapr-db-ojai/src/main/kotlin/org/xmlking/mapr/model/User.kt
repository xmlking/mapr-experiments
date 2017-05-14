package org.xmlking.mapr.model

data class User(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: String?
)

enum class Role {
    STAFF,
    USER
}