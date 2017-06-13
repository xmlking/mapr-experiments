package org.xmlking.mapr

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonProperty

// This annotation used to indicate *Gradle noArg plugin* to generate zero-argument constructor.
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Mapr

@Mapr
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class User (
        @get:JsonProperty("_id")
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

