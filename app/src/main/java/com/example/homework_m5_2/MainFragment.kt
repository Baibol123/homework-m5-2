package com.example.homework_m5_2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.homework_m5_2.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            calculateBtn.setOnClickListener {
                RetrofitService().api.getPercentage(
                    etFname.text.toString(),
                    etSname.text.toString()

                )
                    .enqueue(object : Callback<LoveModel> {
                        override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                            val loveData: LoveModel? = response.body()
                            val bundle = Bundle().apply {
                                putSerializable(ResultFragment.KEY, loveData)
                            }
                            findNavController().navigate(
                                R.id.action_mainFragment_to_resultFragment2,
                                bundle
                            )
                            clearEditTexts()
                        }

                        override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                            Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG)

                        }
                    })
            }
            etFname.setOnEditorActionListener { _, actionId, _ -> handleEditor(actionId) }
            etSname.setOnEditorActionListener { _, actionId, _ -> handleEditor(actionId) }
        }
    }

    private fun clearEditTexts() {
        binding.etFname.setText("")
        binding.etSname.setText("")
    }
    private fun handleEditor(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            hideSoft()
            return true
        }
        return false
    }
    private fun hideSoft() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}