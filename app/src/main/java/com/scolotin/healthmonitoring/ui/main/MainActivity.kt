package com.scolotin.healthmonitoring.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scolotin.healthmonitoring.databinding.ActivityMainBinding
import com.scolotin.healthmonitoring.ui.dialog.HealthDialogFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val mainAdapter: MainAdapter by inject()
    private val compositeDisposable: CompositeDisposable by inject()
    private val healthDialogFragment: HealthDialogFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupKoinFragmentFactory()

        binding.fab.setOnClickListener {
            healthDialogFragment.show(supportFragmentManager, "healthDialog")
        }

        binding.healthList.apply {
            adapter = mainAdapter
        }

        mainViewModel
            .getData()
            .subscribeOn(Schedulers.io())
            .subscribe {
                mainAdapter.submit(it)
            }
            .addTo(compositeDisposable)

    }

}
