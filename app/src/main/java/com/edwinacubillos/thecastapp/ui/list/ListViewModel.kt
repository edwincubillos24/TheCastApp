package com.edwinacubillos.thecastapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinacubillos.domain.remote.ResourceRemote
import com.edwinacubillos.domain.remote.WrappedResponse
import com.edwinacubillos.domain.remote.data.LocalCat
import com.edwinacubillos.usescases.cats.GetCatsUseCase
import kotlinx.coroutines.launch

class ListViewModel(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {

    private val _catsLoaded : MutableLiveData<ResourceRemote<WrappedResponse<List<LocalCat>>>?> = MutableLiveData()
    val catsLoaded: LiveData<ResourceRemote<WrappedResponse<List<LocalCat>>>?> = _catsLoaded

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getCats() {
        viewModelScope.launch{
            viewModelScope.launch(coroutineContext) {
                _isLoading.postValue(true)
                _catsLoaded.postValue(getCatsUseCase())
                _isLoading.postValue(false)
            }
        }
    }
}
