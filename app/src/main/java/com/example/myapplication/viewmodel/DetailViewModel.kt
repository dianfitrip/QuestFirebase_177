package com.example.myapplication.viewmodel




sealed interface StatusUIDetail {
    data class Success(val satusiswa: Siswa?) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}


class DetailViewModel(savedStateHandle: SavedStateHandle,private val repositorySiswa: RepositorySiswa):ViewModel() {

