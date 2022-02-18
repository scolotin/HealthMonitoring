package com.scolotin.healthmonitoring.di.modules

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val networkModule = module {
    single { provideCollectionReference() }
}

private fun provideCollectionReference(): CollectionReference =
    Firebase.firestore.collection("healthData")
