package com.gee12.peopledates.data.db

import android.content.Context
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.data.model.Person
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class DatabaseRepo @Inject constructor(context: Context) {

//    private val dataBase = PersonsDatabase.create(context)
    val dataBase = PersonsDatabase.create(context)

    suspend fun getGroups() = withContext(Dispatchers.IO) {
        dataBase.groupsDao.getAll().map(::toGroup)
    }

    suspend fun getGroup(id: Int) = withContext(Dispatchers.IO) {
        dataBase.groupsDao.getById(id)?.let { toGroup(it) }
    }

    suspend fun addGroup(group: Group) = withContext(Dispatchers.IO) {
        dataBase.groupsDao.insert(toGroupEntity(group))
    }

    suspend fun addGroup(name: String, url: String) = withContext(Dispatchers.IO) {
        addGroup(Group(null, name, url))
    }

    suspend fun updateGroup(id: Int, name: String, url: String) = withContext(Dispatchers.IO) {
        dataBase.groupsDao.update(GroupEntity(id, name, url))
    }

    suspend fun getPerson(id: Int) = withContext(Dispatchers.IO) {
        dataBase.personsDao.getById(id)?.let {
                getGroup(it.groupId)?.let { group -> toPerson(it, group)
            }
        }
    }

    suspend fun getPersons(group: Group) = withContext(Dispatchers.IO) {
        dataBase.personsDao.getByGroupId(group.id!!).map { toPerson(it, group) }
    }

    suspend fun savePersons(persons: List<Person>, group: Group): List<Person> = withContext(
        Dispatchers.IO
    ) {
        dataBase.personsDao.replaceAllByGroup(persons.map(::toPersonEntity), group.id!!)
                .map { toPerson(it, group) }
    }

    private fun toGroup(entity: GroupEntity) = Group(
        id = entity.id,
        name = entity.name,
        url = entity.url,
    )

    private fun toGroupEntity(group: Group) = GroupEntity(
        id = group.id,
        name = group.name,
        url = group.url
    )

    private fun toPerson(entity: PersonEntity, group: Group) = Person(
        id = entity.id,
        group = group,
        name = entity.name,
        dateBirth = Date(entity.dateBirth),
        dateDeath = entity.dateDeath?.let { Date(entity.dateDeath) },
        prof = entity.prof,
        info = entity.info,
        imageUrl = entity.imageUrl
//        null,
    )

    private fun toPersonEntity(person: Person) = PersonEntity(
        id = person.id,
        groupId = person.group.id!!,
        name = person.name,
        dateBirth = person.dateBirth.time,
        dateDeath = person.dateDeath?.time,
        prof = person.prof,
        info = person.info,
        imageUrl = person.imageUrl
    )
}