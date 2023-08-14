package br.com.countries.presentation

import androidx.test.core.app.ActivityScenario.launch
import br.com.countries.R
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import org.junit.Test

class CountriesActivityTest {

    @Test
    fun shouldOpenScreenAndClickedCardAndBackButton() {
        launch(CountriesActivity::class.java)
        clickListItem(R.id.rvCountries, 0)
        sleep(2000)
        clickBack()
    }

    @Test
    fun shouldSearchCountries(){
        launch(CountriesActivity::class.java)
        clickOn(R.id.action_search)
        writeTo(R.id.action_search, "Brazil")
        sleep(2000)
    }

}