package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.DessertUiState
import com.example.dessertclicker.model.Dessert

class DessertViewModel : ViewModel() {

    private var desserts: List<Dessert> = emptyList()

    var uiState = DessertUiState()
        private set

    fun initializeDesserts(dessertsList: List<Dessert>) {
        desserts = dessertsList
        uiState = uiState.copy(
            currentDessertImageId = desserts.first().imageId,
            currentDessertPrice = desserts.first().price
        )
    }

    fun onDessertClicked() {
        val newDessertsSold = uiState.dessertsSold + 1
        val newRevenue = uiState.revenue + uiState.currentDessertPrice
        val nextDessert = determineDessertToShow(desserts, newDessertsSold)

        uiState = uiState.copy(
            dessertsSold = newDessertsSold,
            revenue = newRevenue,
            currentDessertImageId = nextDessert.imageId,
            currentDessertPrice = nextDessert.price
        )
    }

    private fun determineDessertToShow(desserts: List<Dessert>, dessertsSold: Int): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}