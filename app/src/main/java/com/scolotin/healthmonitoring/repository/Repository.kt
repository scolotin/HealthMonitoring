package com.scolotin.healthmonitoring.repository

import com.scolotin.healthmonitoring.model.HealthData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface Repository {
    fun getData(): Flowable<List<HealthData>>
    fun setData(data: HealthData): Completable
}
