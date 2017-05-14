package org.xmlking.mapr.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mapr.db.MapRDB
import com.mapr.db.Table
import org.xmlking.mapr.model.Role
import org.xmlking.mapr.model.User
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import org.xmlking.mapr.util.*
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import java.io.IOException
import java.util.ArrayList
import javax.annotation.PostConstruct


@Repository
class UserRepository(val env: Environment,
                     val dbHelper: MapRJsonDBHelper) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var template: Table? = null
    fun initData() {
//        if (count().block() == 0L) {
//            val usersResource = ClassPathResource("data/users.json")
//            val users: List<User> = objectMapper.readValue(usersResource.inputStream)
//            users.forEach { save(it).block() }
//            logger.info("Users data initialization complete")
//        }
    }

    @PostConstruct
    fun getTableHandler() {
        try {
            template = dbHelper.getTable(env.getProperty("user.table.name"))
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    fun list(): List<User> {
        val userList = ArrayList<User>()
        val rs = template!!.find()
        val itrs = rs.iterator()
        while (itrs.hasNext()) {
            userList.add(itrs.next().toJavaBean(User::class.java))
        }
        rs.close()
        return userList
    }

    operator fun get(id: String): User {
        val user = template!!.findById(id).toJavaBean(User::class.java)
        return user
    }

    fun create(user: User): String {
        val document = MapRDB.newDocument(user)
        template!!.insertOrReplace(document)
        template!!.flush()
        return "user created successfully"
    }

    fun delete(userId: String): User? {
        val userDocument = template!!.findById(userId)
        if(userDocument != null) {
            template!!.delete(userDocument)
            template!!.flush()
            return userDocument.toJavaBean(User::class.java)
        }
        return null;
    }

    fun update(user: User): String? {
        val userDocument = template!!.findById(user.id)
        if (userDocument != null) {
            val document = MapRDB.newDocument(user)
            template!!.insertOrReplace(document)
            template!!.flush()
            return "Update Success"
        } else {
            return null
        }
    }

}
