package com.example.countrysapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.countrysapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val countrysName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch{
                try{
                    val countrys = restCountrysApi.getCountryByName(countrysName)   //.execute.body()!! // синхронный запрос
                    val country = countrys[0]

                    binding.countryNameTextView.text = country.name
                    binding.capitalTextView.text = country.capital

                    binding.populationTextView.text = separateIntegerByComma(country.population)
                    binding.areaTextView.text = separateIntegerByComma(country.area)

                    binding.languageTextView.text = languagesToString(country.languages)

                    loadSvg(binding.countryFlagImageView, country.flag)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                } catch (e: Exception){
                    binding.statusText.text = "Страна не найдена"
                    binding.statusImageView.setImageResource(R.drawable.baseline_report_gmailerrorred_24)

                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}






