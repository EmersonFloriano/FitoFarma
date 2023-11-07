package br.com.emerson.fitofarma.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.emerson.fitofarma.R
import br.com.emerson.fitofarma.activities.PlantDetailsActivity
import br.com.emerson.fitofarma.databinding.CatalogActivityBinding
import br.com.emerson.fitofarma.databinding.PlantCardBinding.*
import br.com.emerson.fitofarma.domain.Plant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


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
            .error(R.drawable.image_not_supported)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.image)

        binding.cardView.setOnClickListener {
            val intent = Intent(context, PlantDetailsActivity::class.java)
            val bundle = Bundle()

            bundle.putLong("id", getItem(position).id)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        return binding.root
    }

    fun updatePlantListVisibility(binding: CatalogActivityBinding) {
        if (plants.isEmpty()) {
            binding.emptyListText.visibility = View.VISIBLE
            binding.plantsAvailableText.visibility = View.GONE
            binding.plantsListView.visibility = View.GONE
        } else {
            binding.emptyListText.visibility = View.GONE
            binding.plantsAvailableText.visibility = View.VISIBLE
            binding.plantsListView.visibility = View.VISIBLE
        }
    }
}