package com.scolotin.healthmonitoring.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.scolotin.healthmonitoring.databinding.FragmentHealthDialogBinding
import com.scolotin.healthmonitoring.model.HealthData
import com.scolotin.healthmonitoring.ui.main.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HealthDialogFragment : DialogFragment() {

    private var _binding: FragmentHealthDialogBinding? = null
    private val binding get() = _binding!!

    private val compositeDisposable: CompositeDisposable by inject()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHealthDialogBinding.inflate(inflater, container, false)

        binding.healthDialogSubmit.setOnClickListener {
            mainViewModel
                .setData(collectsHealthData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dismiss()
                }
                .addTo(compositeDisposable)
        }

        binding.healthDialogCancel.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun collectsHealthData(): HealthData =
        binding.run {
            HealthData(
                healthDialogDate.editText?.text.toString(),
                healthDialogTime.editText?.text.toString(),
                healthDialogSystolic.editText?.text.toString(),
                healthDialogDiastolic.editText?.text.toString(),
                healthDialogPulse.editText?.text.toString()
            )
        }

}
