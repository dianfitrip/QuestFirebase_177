package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.myapplication.modeldata.Siswa
import com.example.myapplication.repository.RepositorySiswa

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<Siswa> = listOf()) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}
class HomeViewModel(private val repositorySiswa: RepositorySiswa): ViewModel() {
    var statusUiSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init{
        loadSiswa()
    }

    fun loadSiswa(){
        viewModelScope.launch {
            statusUiSiswa = StatusUiSiswa.Loading
            statusUiSiswa = try {
                StatusUiSiswa.Success(repositorySiswa.getDataSiswa())
            }catch (e:IOException){
                StatusUiSiswa.Error
            }
            catch (e:Exception){
                StatusUiSiswa.Error
            }
        }
    }

}