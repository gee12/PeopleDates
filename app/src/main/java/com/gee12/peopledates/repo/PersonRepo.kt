package com.gee12.peopledates.repo

import com.gee12.peopledates.data.db.DatabaseRepo
import com.gee12.peopledates.network.repo.PersonRemoteRepo

class PersonRepo(
    private val dbRepo: DatabaseRepo,
    private val remoteRepo: PersonRemoteRepo
) {
}