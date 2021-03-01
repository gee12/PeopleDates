package com.gee12.peopledates.network.repo

import com.gee12.peopledates.network.api.PersonsApi
import com.gee12.peopledates.network.repo.BaseRemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PersonRemoteRepo @Inject constructor(
    private val personsApi: PersonsApi
    ) : BaseRemoteRepo() {

    suspend fun loadFileContent(path: String) = withContext(Dispatchers.IO) {
        personsApi.loadFileContent(path)
    }
}