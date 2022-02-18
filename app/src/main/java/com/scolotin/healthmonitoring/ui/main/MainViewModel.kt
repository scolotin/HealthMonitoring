package com.scolotin.healthmonitoring.ui.main

import androidx.lifecycle.ViewModel
import com.scolotin.healthmonitoring.model.HealthData
import com.scolotin.healthmonitoring.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getData(): Flowable<List<HealthData>> {
        return repository.getData()
    }

    fun setData(data: HealthData): Completable {
        return repository.setData(data)
    }

}
