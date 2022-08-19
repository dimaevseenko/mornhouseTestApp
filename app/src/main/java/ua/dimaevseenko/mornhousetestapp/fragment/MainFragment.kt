package ua.dimaevseenko.mornhousetestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.dimaevseenko.mornhousetestapp.R
import ua.dimaevseenko.mornhousetestapp.appComponent
import ua.dimaevseenko.mornhousetestapp.databinding.FragmentMainBinding
import ua.dimaevseenko.mornhousetestapp.db.FactDB
import ua.dimaevseenko.mornhousetestapp.model.Fact
import ua.dimaevseenko.mornhousetestapp.network.Server
import ua.dimaevseenko.mornhousetestapp.network.request.RFact
import javax.inject.Inject

class MainFragment @Inject constructor(): Fragment(), FactsRecyclerAdapter.Listener{

    companion object{
        const val TAG = "MainFragment"
    }

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var rFact: RFact

    @Inject
    lateinit var secondFragment: SecondFragment

    @Inject
    lateinit var server: Server

    @Inject
    lateinit var factDB: FactDB

    @Inject
    lateinit var recyclerAdapterFactory: FactsRecyclerAdapter.Factory
    private lateinit var adapter: FactsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getFact.setOnClickListener { getFact() }
        binding.getRandomFact.setOnClickListener { getRandomFact() }

        updateRecycler()
    }

    private fun getFact(){
        server.getFact(binding.editTextNumber.text.toString()){ fact->
            CoroutineScope(Dispatchers.Default).launch { factDB.getFactDao().insertFact(fact) }
            openSecondFragment(fact)
        }
    }

    private fun getRandomFact(){
        server.getRandomFact{ fact->
            CoroutineScope(Dispatchers.Default).launch { factDB.getFactDao().insertFact(fact) }
            openSecondFragment(fact)
        }
    }

    private fun openSecondFragment(fact: Fact){
        secondFragment.arguments = Bundle().apply {
            putParcelable("fact", fact)
        }
        parentFragmentManager.beginTransaction().replace(R.id.mainContainer, secondFragment, SecondFragment.TAG).commit()
    }

    private fun updateRecycler(){
        CoroutineScope(Dispatchers.Default).launch {
            val facts = factDB.getFactDao().getFacts().reversed()
            launch(Dispatchers.Main) {
                adapter = recyclerAdapterFactory.createAdapter(facts)
                binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.historyRecyclerView.adapter = adapter
                adapter.listener = this@MainFragment
            }
        }
    }

    override fun onClick(fact: Fact) {
        openSecondFragment(fact)
    }

    override fun toString(): String {
        return TAG
    }
}