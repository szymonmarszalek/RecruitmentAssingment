package com.example.recruitmentassingment.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.recruitmentassingment.R
import com.example.recruitmentassingment.databinding.FragmentSearchNumberBinding

import com.example.recruitmentassingment.ViewModels.searchNumberViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class searchNumberFragment : Fragment() {
    private var _binding: FragmentSearchNumberBinding? = null
    private val binding get() = _binding!!
    private val viewModel : searchNumberViewModel by activityViewModels()
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
    ): View {
        _binding = FragmentSearchNumberBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goResultButton.setOnClickListener {
            val listOfNumbers = view.findViewById<EditText>(R.id.editTextViewNumbers).text.toString().split(",")
            checkNumbers(listOfNumbers)
            findNavController().navigate(R.id.action_searchNumberFragment_to_resultFragment)
        }
    }
    //Funkcja główna, sprawdzająca warunki i dalsze etapy
    private fun checkNumbers(numbersList: List<String>) {
        //początkowy warunek, czy wpisane znaki są cyframi
        if(isNumberList(numbersList)){
            setErrorOnScreen()
        }
        else {
            if ((numbersList.size < 3) || (checkOddEven(numbersList) == 2)) {
                setErrorOnScreen()
            } else {
                saveNumber(getAloneNumber(numbersList, checkOddEven(numbersList)))
            }
        }
    }
    //Funkcja zapisuje wartość odstającą
    private fun saveNumber(numbersList: Int){
        viewModel.setNumber(numbersList.toString())
    }
    private fun setErrorOnScreen(){
        viewModel.setNumber("Blad")
    }
    //Sprawdzanie, czy liczba liczb parzystych i nieparzystych jest w porządku - 1:n lub m:1
    private fun checkOddEven(numbersList: List<String>): Int {
        var odd = 0
        var even = 0
        numbersList.forEach{
            if(it.toInt() % 2 == 0){
                even++
            }
            else{
                odd++
            }
        }
        return if(((odd == 1) &&( even != 1))){
            1
        } else if ((odd != 1) && (even ==1)){
            0
        } else{
            2
        }
    }
    //funkcja wyznaczająca odstającą liczbę
    private fun getAloneNumber(numbersList: List<String>, oddOrEven: Int): Int {
        var aloneNumber = 0
        numbersList.forEach{
            //oddOrEven to liczba zwraca przez funkcje CheckOddEven
            if((it.toInt() + oddOrEven) % 2 == 0){
                aloneNumber = it.toInt()
            }
        }
        return aloneNumber
    }
    // funkcja sprawdzająca czy znak jest cyfrą
    fun isNumber(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (ex: NumberFormatException) {
            false
        }
    }
    //funkcja sprawdzająca czy każdy z elementów listy jest cyfrą
    fun isNumberList(numbersList: List<String>): Boolean {
        var isNotNumeric = false
        numbersList.forEach{
            if(!isNumber(it)){
                isNotNumeric = true
            }
        }
        return isNotNumeric
}
}