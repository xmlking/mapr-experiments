package org.xmlking.mapr

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mapr.db.MapRDB
import com.mapr.db.Table
import com.mapr.db.exceptions.DBException
import org.ojai.DocumentStream
import org.ojai.store.QueryCondition.Op.EQUAL
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.IOException
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Repository
class UserRepository(@Value("\${user.table.name}") val userTableName: String,
                     val dbHelper: MapRJsonDBHelper,
                     val objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private lateinit var userTable: Table

    fun initData() {
        if (count().block() == 0) {
            val usersResource = ClassPathResource("data/users.json")
            val users: List<User> = objectMapper.readValue(usersResource.inputStream)
            users.forEach { create(it).block() }
            logger.info("Users data initialization complete")
        }
    }

    @PostConstruct
    fun init() {
        try {
            userTable = dbHelper.getTable(userTableName)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    @PreDestroy
    fun destroy() {
        println("in UserRepository cleanUp")
    }

    fun count() : Mono<Int> {
        return Mono.just(userTable.find().count())
    }

    fun list(): Flux<User> {
        val userList = ArrayList<User>()
        userTable.find().use { documentStream: DocumentStream ->
            documentStream.mapTo(userList) { it.toJavaBean(User::class.java) }
        }
        return Flux.fromIterable(userList)
    }

    fun listLazy(): Flux<User> {
        val rs = userTable.find()

        return Flux.generate({ rs.iterator() }) { s, o ->

            if (s.hasNext()) {
                o.next(s.next().toJavaBean(User::class.java));
            }
            else {
                o.complete()
                rs.close()
            }
            s
        }
    }

    fun findAllByFirstNameLike(letter: String): Flux<User> {
        val condition = MapRDB.newCondition()
                .or()
                .`is`("firstName", EQUAL, letter)
                .close()
                .build();
        val userList = ArrayList<User>()
        userTable.find(condition).use { documentStream: DocumentStream ->
            documentStream.mapTo(userList) { it.toJavaBean(User::class.java) }
        }
        return Flux.fromIterable(userList)
    }

    fun findOne(id: String): Mono<User> {
        val user = try { userTable.findById(id).toJavaBean(User::class.java) } catch (e: DBException) { null }
        return Mono.just(user)
    }

    fun create(user: User): Mono<User> {
        val document = MapRDB.newDocument(user)
        userTable.insertOrReplace(document)
        userTable.flush()
        return Mono.just(document.toJavaBean(User::class.java))
    }

    fun delete(userId: String): Mono<User> {
        val userDocument = userTable.findById(userId)
        if(userDocument != null) {
            userTable.delete(userDocument)
            userTable.flush()
        }
        return Mono.just(userDocument?.toJavaBean(User::class.java))
    }

    fun update(user: User): Mono<User> {
        val userDocument = userTable.findById(user.id)
        if (userDocument != null) {
            val document = MapRDB.newDocument(user)
            userTable.insertOrReplace(document)
            userTable.flush()
        }
        return Mono.just(userDocument?.toJavaBean(User::class.java))
    }

}
