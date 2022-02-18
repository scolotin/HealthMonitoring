package com.scolotin.healthmonitoring.di.modules

import com.scolotin.healthmonitoring.repository.FirestoreRepository
import com.scolotin.healthmonitoring.repository.Repository
import com.scolotin.healthmonitoring.ui.dialog.HealthDialogFragment
import com.scolotin.healthmonitoring.ui.main.MainAdapter
import com.scolotin.healthmonitoring.ui.main.MainViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {
    single { FirestoreRepository(firestore = get()) } bind Repository::class
    viewModel { MainViewModel(repository = get()) }
    factory { MainAdapter() }
    fragment { HealthDialogFragment() }
    factory { CompositeDisposable() }
}
