package org.xmlking.mapr

import org.xmlking.mapr.repository.UserRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer(val userRepository: UserRepository) {

    @PostConstruct
    fun init() {
        userRepository.initData()
    }
}