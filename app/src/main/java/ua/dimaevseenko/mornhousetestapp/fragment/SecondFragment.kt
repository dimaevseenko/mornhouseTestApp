package ua.dimaevseenko.mornhousetestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import ua.dimaevseenko.mornhousetestapp.R
import ua.dimaevseenko.mornhousetestapp.appComponent
import ua.dimaevseenko.mornhousetestapp.databinding.FragmentSecondBinding
import ua.dimaevseenko.mornhousetestapp.db.FactDB
import ua.dimaevseenko.mornhousetestapp.model.Fact
import javax.inject.Inject

class SecondFragment @Inject constructor(): Fragment(){

    companion object{
        const val TAG = "SecondFragment"
    }

    private lateinit var binding: FragmentSecondBinding

    private lateinit var fact: Fact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.bind(inflater.inflate(R.layout.fragment_second, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            fact = it.getParcelable<Fact>("fact")!!
        }
        updateView()
    }

    private fun updateView(){
        binding.requestNumber.text = "Request: ${fact.factId}"
        binding.numberFact.text = fact.fact
    }

    override fun toString(): String {
        return TAG
    }
}