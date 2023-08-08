package br.com.countries.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.countries.databinding.CountriesListItemBinding
import br.com.countries.domain.models.Countries
import br.com.countries.extensions.views.clickWithDebounce
import br.com.countries.presentation.detail.CountriesDetailActivity
import br.com.countries.presentation.detail.model.CountriesModel
import com.bumptech.glide.Glide

class CountriesAdapter(
    private val listCountries: List<Countries>
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    inner class CountriesViewHolder(private val binding: CountriesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countries: Countries) = with(binding) {
            Glide.with(acivCountriesFlag.context)
                .load(countries.flags?.png)
                .into(acivCountriesFlag)
            actvCountriesName.text = countries.name?.common
            setupCountries(countries)
        }

        private fun setupCountries(countries: Countries){
            val model = CountriesModel(
                common = countries.name?.common,
                capital = countries.capital,
                region = countries.region,
                latitude = countries.latitude,
                googleMaps = countries.maps?.openStreetMaps,
                png = countries.flags?.png,
                area = countries.area,
                population = countries.population
            )
            openCountriesDetail(model)
        }

        private fun openCountriesDetail(countriesModel: CountriesModel) {
            binding.cvCountries.apply {
                clickWithDebounce {
                    val intent = CountriesDetailActivity.newInstance(
                        context = this.context,
                        countriesModel = countriesModel
                    )
                    startActivity(this.context, intent, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesViewHolder {
        return CountriesViewHolder(CountriesListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CountriesAdapter.CountriesViewHolder, position: Int) {
        holder.bind(listCountries[position])
    }

    override fun getItemCount() = listCountries.size
}