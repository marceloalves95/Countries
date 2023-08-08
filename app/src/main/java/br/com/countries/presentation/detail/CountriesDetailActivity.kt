package br.com.countries.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.countries.databinding.ActivityCountriesDetailBinding
import br.com.countries.extensions.views.extra
import br.com.countries.extensions.views.onBackButtonPressed
import br.com.countries.extensions.views.viewBinding
import br.com.countries.presentation.detail.model.CountriesModel

class CountriesDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityCountriesDetailBinding::inflate)
    private val layoutContainer by lazy {
        CountriesDetailLayoutContainer(binding)
    }
    private val countries by extra<CountriesModel>(COUNTRIES)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        layoutContainer.setupCountries(countries)
    }

    private fun setupToolbar() {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackButtonPressed()
            }
        }
    }

    companion object {
        private const val COUNTRIES = "countries"
        fun newInstance(
            context: Context,
            countriesModel: CountriesModel
        ): Intent {
            val intent = Intent(context, CountriesDetailActivity::class.java).apply {
                putExtra(COUNTRIES, countriesModel)
            }
            return intent
        }
    }
}