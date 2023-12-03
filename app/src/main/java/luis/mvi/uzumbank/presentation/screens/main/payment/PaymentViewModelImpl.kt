package luis.mvi.uzumbank.presentation.screens.main.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData
import luis.mvi.uzumbank.domain.repository.payment.PaymentRepository
import javax.inject.Inject

/*
created by Xo'jaakbar on 19.09.2023 at 3:07
*/
@HiltViewModel
class PaymentViewModelImpl @Inject constructor(
    val repository: PaymentRepository,
) : ViewModel(), PaymentViewModel {

    override val categoryListLiveData = MutableLiveData<List<CategoryData>>()
    override val favouriteListLiveData = MutableLiveData<List<FavouritePaysData>>()
    override val rebuildLiveData = MutableLiveData<Boolean>()

    override fun rebuildCategoryList() {
        rebuildLiveData.value = !(rebuildLiveData.value?:false)
    }

    init {
       categoryListLiveData.value = repository.getCategoryList()
        favouriteListLiveData.value = repository.getFavouritePaysList()
    }


}