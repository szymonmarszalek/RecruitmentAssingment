package com.example.recruitmentassingment.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recruitmentassingment.R
import com.example.recruitmentassingment.ViewModels.searchNumberViewModel
import com.example.recruitmentassingment.databinding.FragmentResultBinding
import com.example.recruitmentassingment.databinding.FragmentSearchNumberBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: searchNumberViewModel by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result,container,false)
        val textViewResult = view.findViewById<TextView>(R.id.textViewResult)
        viewModel.currentNumbers.observe(viewLifecycleOwner, Observer {
            textViewResult.text = it.toString()
        })
        return view
    }

}
