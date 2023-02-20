package pl.mankevich.githubrepositorybrowserum.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mankevich.githubrepositorybrowserum.domain.usecase.FetchRepListUseCase
import javax.inject.Inject

@HiltViewModel
class GitRepListViewModel @Inject constructor(
    private val fetchRepListUseCase: FetchRepListUseCase
) : ViewModel() {


}