package com.edwinacubillos.thecastapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.thecastapp.server.catsrepository.CatsRepository
import com.edwinacubillos.thecastapp.server.model.CatList
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val catsRepository = CatsRepository()

    private val _catsLoaded : MutableLiveData<CatList> = MutableLiveData()
    val catsLoaded: LiveData<CatList> = _catsLoaded

    fun getCats() {
        viewModelScope.launch{
            val catList = catsRepository.getCats()
            _catsLoaded.postValue(catList)
        }
    }
}
