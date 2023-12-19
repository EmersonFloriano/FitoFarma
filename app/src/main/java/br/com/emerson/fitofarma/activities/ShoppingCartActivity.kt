package br.com.emerson.fitofarma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.emerson.fitofarma.R
import br.com.emerson.fitofarma.adapters.PlantAdapter
import br.com.emerson.fitofarma.database.RoomHelper
import br.com.emerson.fitofarma.databinding.ActivityShoppingCartBinding
import br.com.emerson.fitofarma.domain.Plant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingCartActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        ActivityShoppingCartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.backButton.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        scope.launch {
            val dao = RoomHelper.getInstance(this@ShoppingCartActivity).plantDao()
            val plants = dao.findAllFilteredByInCart()
            print("test $plants")

            runOnUiThread {
                val listView = binding.plantsListView
                val adapter = PlantAdapter(this@ShoppingCartActivity, plants)
                listView.adapter = adapter
                adapter.updatePlantListVisibility(binding)
            }
        }
    }
}