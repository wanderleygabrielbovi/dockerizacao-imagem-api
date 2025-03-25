package umfg.infra.repository

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import umfg.DatabaseFactory
import umfg.application.payloads.CreateUserPayload

class UserRepository {

    private val database = DatabaseFactory.connect()

    object UserTable : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 50)
        val age = integer("age")
        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(UserTable)
        }
    }

    suspend fun create(payload: CreateUserPayload): Int {
        return newSuspendedTransaction(Dispatchers.IO) {
            UserTable.insert {
                it[name] = payload.name
                it[age] = payload.age
            }[UserTable.id]
        }
    }

    suspend fun findById(id: Int): User? {
        return newSuspendedTransaction(Dispatchers.IO) {
            UserTable.selectAll()
                .where { UserTable.id eq id }
                .map {
                    User(
                        id = it[UserTable.id],
                        name = it[UserTable.name],
                        age = it[UserTable.age]
                    )
                }.singleOrNull()
        }
    }

        suspend fun findAll(): List<User> {
            return newSuspendedTransaction(Dispatchers.IO) {
                UserTable.selectAll()
                    .map {
                        User(
                            id = it[UserTable.id],
                            name = it[UserTable.name],
                            age = it[UserTable.age]
                        )
                    }
            }
        }

        suspend fun updateById(id: Int, updateUser: CreateUserPayload): Int {
            return newSuspendedTransaction(Dispatchers.IO) {
                UserTable.update ({ UserTable.id eq id }) {
                    it[name] = updateUser.name
                    it[age] = updateUser.age
                }
            }
        }

    suspend fun deleteUser(id: Int): {
        return newSuspendedTransaction(Dispatchers.IO) {
            UserTable.
        }
    }


}