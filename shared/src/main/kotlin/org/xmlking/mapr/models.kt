package org.xmlking.mapr

data class User(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String?,
        val role: Role = Role.USER,
        val age: Int?
)

enum class Role {
    STAFF,
    USER
}