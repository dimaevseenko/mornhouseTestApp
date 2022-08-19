package ua.dimaevseenko.mornhousetestapp.fragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ua.dimaevseenko.mornhousetestapp.R
import ua.dimaevseenko.mornhousetestapp.databinding.RecyclerViewItemBinding
import ua.dimaevseenko.mornhousetestapp.model.Fact

class FactsRecyclerAdapter @AssistedInject constructor(
    @Assisted("facts")
    private val facts: List<Fact>,
    private val context: Context
): RecyclerView.Adapter<FactsRecyclerAdapter.ViewHolder>() {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facts[position])
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position%2==0)
            1
        else
            0
    }

    interface Listener{
        fun onClick(fact: Fact)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = RecyclerViewItemBinding.bind(view)

        fun bind(fact: Fact){
            if(itemViewType == 0)
                binding.root.setBackgroundColor(Color.parseColor("#ECECEC"))
            else
                binding.root.setBackgroundColor(Color.WHITE)

            binding.factId.text = "Request: ${fact.factId}"
            binding.fact.text = fact.fact

            binding.root.setOnClickListener { listener?.onClick(fact) }
        }
    }

    @AssistedFactory
    interface Factory{
        fun createAdapter(
            @Assisted("facts")
            facts: List<Fact>
        ): FactsRecyclerAdapter
    }
}