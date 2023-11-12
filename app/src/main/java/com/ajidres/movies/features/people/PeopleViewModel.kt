package com.ajidres.movies.features.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.PeopleResponseUI
import com.ajidres.movies.domain.useCases.people.PeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PeopleViewModel @Inject constructor(private val peopleUseCase: PeopleUseCase): ViewModel() {

    private var _peopleDataApi = MutableLiveData<ResultEndpoints<PeopleResponseUI>>()
    val peopleDataApi: LiveData<ResultEndpoints<PeopleResponseUI>> get() = _peopleDataApi

    private var _peopleDataDb = MutableLiveData<PeopleResponseUI>()
    val peopleDataDb: LiveData<PeopleResponseUI> get() = _peopleDataDb

    fun fetchPeople() {
        viewModelScope.launch {
            peopleUseCase.invokeFetchPeople(1).collect {
                _peopleDataApi.value = it
            }
        }
    }

    fun getPeopleDb(){
        viewModelScope.launch {
            peopleUseCase.fetchPeopleData().collect {
                _peopleDataDb.value = it
            }
        }
    }

    fun savePeople(data: PeopleResponseUI) {
        viewModelScope.launch {
            peopleUseCase.savePeopleData(data)
        }
    }
}