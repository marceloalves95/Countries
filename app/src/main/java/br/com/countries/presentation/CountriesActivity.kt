package br.com.countries.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.countries.R
import br.com.countries.databinding.ActivityCountriesBinding
import br.com.countries.extensions.views.observe
import br.com.countries.extensions.views.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountriesActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityCountriesBinding::inflate)
    private val layoutContainer by lazy {
        CountriesLayoutContainer(binding, viewModel)
    }
    private val viewModel: CountriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.loadCountries()
        setupObserves()
    }

    private fun setupObserves() = with(viewModel) {
        observe(state) {
            layoutContainer.setState(it)
        }
        observe(searchState) {
            layoutContainer.setSearchState(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchCountryByName(newText)
                return false
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.loadCountries()
                return true
            }

        })
        val closeButton: View? = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            viewModel.loadCountries()
        }

        return super.onCreateOptionsMenu(menu)
    }
}