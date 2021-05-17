package ru.shiryaev.mathstatisticscalculator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.shiryaev.mathstatisticscalculator.data.RowVariationRange
import ru.shiryaev.mathstatisticscalculator.databinding.ItemVariationDataBinding

class VariationsDataAdapter(
    private val listVariationData: List<RowVariationRange>
) : RecyclerView.Adapter<VariationsDataAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemBinding = ItemVariationDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if(position == 0) {
            holder.bind()
        } else {
            holder.bind(listVariationData[position - 1])
        }
    }

    override fun getItemCount(): Int = listVariationData.size

    inner class Holder(private val itemViewBinding: ItemVariationDataBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind() {
            with(itemViewBinding) {
                valueTv.text = "Xi"
                frequencyTv.text = "Ni"
                relativeFrequencyTv.text = "Ni/N"
                accumulatedFrequencyTv.text = "Накопленная частость"
            }
        }

        fun bind(rowVariationData: RowVariationRange) {
            with(itemViewBinding) {
                valueTv.text = if(rowVariationData.interval.max - rowVariationData.interval.min == 1) {
                    rowVariationData.interval.min.toString()
                } else "${rowVariationData.interval.min}-${rowVariationData.interval.max}"
                frequencyTv.text = rowVariationData.frequency.toString()
                relativeFrequencyTv.text = rowVariationData.relativeFrequency.toString()
                accumulatedFrequencyTv.text = rowVariationData.accumulatedFrequency.toString()
            }
        }
    }
}