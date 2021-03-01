package com.gee12.peopledates.ui.person

import androidx.lifecycle.*
import com.gee12.peopledates.BuildConfig
import com.gee12.peopledates.HtmlParser
import com.gee12.peopledates.data.db.DatabaseRepo
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.data.model.Person
import com.gee12.peopledates.dummy.DummyContent
import com.gee12.peopledates.network.repo.PersonRemoteRepo
import kotlinx.coroutines.launch
import com.gee12.peopledates.network.Result
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val repository: DatabaseRepo,
    private val remoteRepository: PersonRemoteRepo
): ViewModel() {

    private val _mutablePersonsList = MutableLiveData<List<Person>>(emptyList())
    private val _mutableErrorMessage = MutableLiveData<String>()
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)
//    private val _mutableGroupId = MutableLiveData<String>()
//    private val _mutablePersonId = MutableLiveData<Int>()
    private val _mutablePerson = MutableLiveData<Person>()
    private val _mutableGroup = MutableLiveData<Group>()

    val personsList: LiveData<List<Person>> get() = _mutablePersonsList
    val errorMessage: LiveData<String> get() = _mutableErrorMessage
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
    val person: LiveData<Person> get() = _mutablePerson
    val group: LiveData<Group> get() = _mutableGroup


    fun setPersonId(id: Int) {
        viewModelScope.launch {
            id.let {
                repository.getPerson(it)?.let {
                    person -> _mutablePerson.value = person
                }
            }
        }
    }

    fun loadPersons(groupId: Int) {
        val isLoadingNow = (loadingState.value ?: false)
        if (!isLoadingNow) {
            viewModelScope.launch {
                _mutableLoadingState.value = true

                var group = repository.getGroup(groupId)
                if (BuildConfig.DEBUG) {
                    group = DummyContent.GROUP
                }
                if (group == null) {
                    _mutablePersonsList.value = emptyList()
//                    _mutableErrorMessage.value = "Не удалось найти группу"
                    _mutableLoadingState.value = false
                    return@launch
                }
                _mutableGroup.value = group!!

                // DummyContent.ITEMS
                val localData = repository.getPersons(group)
                if (localData.isNotEmpty()) {
                    // обновляем список в UI данными из БД
                    _mutablePersonsList.value = localData
                }

//                val pathToRecord = Utils.parseUri(group.url)?.path
                if (group.path == null) {
                    _mutablePersonsList.value = emptyList()
                    _mutableErrorMessage.value = "Не удалось получить путь к группе в Url"
                    _mutableLoadingState.value = false
                    return@launch
                }

                val htmlContentResult = remoteRepository.loadFileContent(group.path!!)
//                if (htmlContentResult.isSuccessful) {
                when (htmlContentResult) {
                    is Result.Success -> {
                        // получаем html-файл
                        val htmlContent = htmlContentResult.data!!
                        // парсим таблицу
                        val remoteData = HtmlParser.parsePersonsFromHtmlTable(htmlContent, group)
                        // сохраняем в БД и получаем данные уже с заполненным id
                        val localData = repository.savePersons(remoteData, group)
                        // обновляем список в UI актуальными данными
                        _mutablePersonsList.value = localData
                    }
                    is Result.Error -> {
                        _mutableErrorMessage.value = htmlContentResult.exception.message
                        println(htmlContentResult.exception)
                    }
                }

                _mutableLoadingState.value = false
            }
        }
    }
}