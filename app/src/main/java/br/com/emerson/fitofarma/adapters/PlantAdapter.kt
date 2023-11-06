package br.com.emerson.fitofarma.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.emerson.fitofarma.databinding.PlantCardBinding.*
import br.com.emerson.fitofarma.domain.Plant
import com.bumptech.glide.Glide


class PlantAdapter(private val context: Context, private val plants: List<Plant>) : BaseAdapter() {
    override fun getCount(): Int {
        return plants.size
    }

    override fun getItem(position: Int): Plant {
        return plants[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val inflater = LayoutInflater.from(context)
        val binding = inflate(inflater, parent, false)

        binding.title.text = getItem(position).name
        binding.description.text = getItem(position).description
        Glide.with(context)
            .load(getItem(position).imageUrl)
            .into(binding.image)

        return binding.root
    }
}