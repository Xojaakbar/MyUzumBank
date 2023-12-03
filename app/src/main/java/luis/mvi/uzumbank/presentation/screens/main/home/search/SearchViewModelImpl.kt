package luis.mvi.uzumbank.presentation.screens.main.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 11.11.2023 at 1:01
*/
@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val navigator: AppNavigator
):SearchViewModel,ViewModel() {
    override fun back() {
        viewModelScope.launch { navigator.back() }
    }

}