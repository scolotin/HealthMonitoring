package com.scolotin.healthmonitoring.repository

import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObjects
import com.scolotin.healthmonitoring.model.HealthData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class FirestoreRepository(
    private val firestore: CollectionReference
) : Repository {

    override fun getData(): Flowable<List<HealthData>> =
        Flowable.create({ emitter ->
            firestore
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    }
                    if (snapshot != null && !snapshot.isEmpty) {
                        emitter.onNext(snapshot.toObjects())
                    }
                }
        }, BackpressureStrategy.BUFFER)

    override fun setData(data: HealthData): Completable =
        Completable.create { emitter ->
            firestore
                .document()
                .set(data)
                .addOnSuccessListener {
                    emitter.onComplete()
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }

}
