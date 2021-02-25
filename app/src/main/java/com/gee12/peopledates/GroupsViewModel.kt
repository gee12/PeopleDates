package com.gee12.peopledates

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gee12.peopledates.data.db.DatabaseRepository
import com.gee12.peopledates.data.model.Group
import com.gee12.peopledates.dummy.DummyContent
import com.gee12.peopledates.ui.group.GroupDialogState
import com.gee12.peopledates.ui.login.LoginFormState
import kotlinx.coroutines.launch

class GroupsViewModel(
    private val repository: DatabaseRepository
) : ViewModel() {
    private val _mutableGroupsList = MutableLiveData<List<Group>>(emptyList())
    val groupsList: LiveData<List<Group>> get() = _mutableGroupsList
    
    private val _mutableGroup = MutableLiveData<Group>()
    val group: LiveData<Group> get() = _mutableGroup
    
    private val _mutableSuccessMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> get() = _mutableSuccessMessage
    
    private val _mutableErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _mutableErrorMessage
    
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    private val _groupDialogState = MutableLiveData<GroupDialogState>()
    val groupDialogState: LiveData<GroupDialogState> = _groupDialogState


    fun setGroupId(id: Int) {
        viewModelScope.launch {
            id.let {
                repository.getGroup(it)?.let {
                        group -> _mutableGroup.value = group
                }
            }
        }
    }

    fun groupDataChanged(name: String, url: String) {
        if (name.isBlank()) {
            _groupDialogState.value = GroupDialogState(nameError = R.string.empty_field)
        } else if (!Utils.isValidUrl(url)) {
            _groupDialogState.value = GroupDialogState(urlError = R.string.invalid_url)
        } else if (Utils.parseUri(url)?.lastPathSegment == null) {
            _groupDialogState.value = GroupDialogState(urlError = R.string.invalid_url_without_page)
        } else {
            _groupDialogState.value = GroupDialogState(isDataValid = true)
        }
    }

/*    fun checkGroupExists(id: String, name: String, url: String) {
        if () {
            _groupDialogState.value = GroupDialogState(nameError = R.string.group_name_exists)
        } else if () {
            _groupDialogState.value = GroupDialogState(urlError = R.string.group_url_exists)
        } else {
            _groupDialogState.value = GroupDialogState(isDataValid = true)
        }
    }*/

    fun addGroup(name: String, url: String) {
        viewModelScope.launch {
            _mutableLoadingState.value = true

//            val id = Utils.parseUri(url)?.lastPathSegment ?: return@launch
            if (repository.addGroup(name, url) >= 0) {
                _mutableSuccessMessage.value = "Группа создана"
            } else {
                _mutableErrorMessage.value = "Группа не создана"
            }
            _mutableLoadingState.value = false

//            loadGroups()
        }
    }

    fun updateGroup(id: Int, name: String, url: String) {
        viewModelScope.launch {
            _mutableLoadingState.value = true

//            if (repository.updateGroup(id, name, url) != null) {
            repository.updateGroup(id, name, url)
//                _mutableSuccessMessage.value = "Группа обновлена"
//            } else {
//                _mutableErrorMessage.value = "Группа не обновлена"
//            }
            _mutableLoadingState.value = false

//            loadGroups()
        }
    }

    fun loadGroups() {
        val isLoadingNow = (loadingState.value ?: false)
        if (!isLoadingNow) {
            viewModelScope.launch {
                _mutableLoadingState.value = true

                // TODO: убрать
                repository.addGroup(DummyContent.GROUP)

                val localData = repository.getGroups()
                if (localData.isNotEmpty()) {
                    // обновляем список в UI данными из БД
                    _mutableGroupsList.value = localData
                } else {
                    _mutableErrorMessage.value = "Группы не созданы"
                }

                _mutableLoadingState.value = false
            }
        }
    }
}