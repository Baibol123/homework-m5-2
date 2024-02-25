package com.example.homework_m5_2.calculate

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.homework_m5_2.LoveModel
import com.example.homework_m5_2.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root

    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val love = arguments?.getSerializable(KEY, LoveModel::class.java)
        initViews(love)
        initClickers()
    }
    private fun initClickers() {
        with(binding) {
            btnTryAgain.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initViews(response: LoveModel?) {
        with(binding) {
            tvScore.text = response?.result.toString()
            resultCount.text = response?.percentage.toString()
        }
    }
    companion object {
        val KEY = "result"
    }
}