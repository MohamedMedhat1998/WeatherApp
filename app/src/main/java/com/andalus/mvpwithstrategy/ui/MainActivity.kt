package com.andalus.mvpwithstrategy.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andalus.mvpwithstrategy.R
import com.andalus.mvpwithstrategy.data.WeatherDatabase
import com.andalus.mvpwithstrategy.data.WeatherEntity
import com.andalus.mvpwithstrategy.model.WeatherModel
import com.andalus.mvpwithstrategy.utils.NetworkStates
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private lateinit var model: WeatherModel
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = WeatherDatabase.getInstance(this).getDao()
        presenter = MainActivityPresenter(this)
        model = WeatherModel(NetworkStates(this), dao, presenter)
        presenter.model = model
        btnSearch.setOnClickListener {
            presenter.onSearchButtonClicked(getCityName())
        }
    }

    override fun showMessage(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()
    }

    override fun displayData(data: WeatherEntity) {
        tvCity.text = "${data.city},${data.country}"
        tvDescription.text = data.description
        val temp = "%.2f".format(data.temp - 273.15)
        tvTemp.text = "$temp°c"
        Glide.with(this).load(data.icon).placeholder(android.R.drawable.presence_offline).into(ivIcon)

    }

    override fun getCityName(): String {
        return etSearch.text.toString()
    }

    override fun hideIndicator() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideData() {
        tvCity.visibility = View.INVISIBLE
        tvDescription.visibility = View.INVISIBLE
        tvTemp.visibility = View.INVISIBLE
        ivIcon.visibility = View.INVISIBLE
    }

    override fun showData() {
        tvCity.visibility = View.VISIBLE
        tvDescription.visibility = View.VISIBLE
        tvTemp.visibility = View.VISIBLE
        ivIcon.visibility = View.VISIBLE
    }

}
