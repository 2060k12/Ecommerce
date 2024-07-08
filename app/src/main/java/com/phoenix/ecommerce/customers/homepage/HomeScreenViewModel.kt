package com.phoenix.ecommerce.customers.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.remote.repository.ProductsRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {


    // initializing products repository
    private val productRepository = ProductsRepository()

    // livedata for our computer, mobile and watch list
    val mobileList get() =  productRepository.mobileList
    val computerList get() =  productRepository.computerList
    val watchList get() =  productRepository.watchList
    val productList get() =  productRepository.productList
    val offerList get() =  productRepository.offerList


    // function to get allProducts from firestore database
    // getAllProduct is a suspend function in ProductRepository
    fun getAllProducts(){
        viewModelScope.launch {
            productRepository.getAllProducts()
        }
    }

    // function to get allComputer from firestore database
    // getAllComputer is a suspend function in ProductRepository
    fun getAllComputers() {
        viewModelScope.launch {
            productRepository.getALlComputers()
        }
    }

    // function to get allMobile from firestore database
    // getAllMobile is a suspend function in ProductRepository
    fun getAllMobiles() {
        viewModelScope.launch {
            productRepository.getAllMobiles()
        }
    }

    // function to get allWatch from firestore database
    // getAllWatch is a suspend function in ProductRepository
    fun getAllWatches() {
        viewModelScope.launch {
            productRepository.getAllWatches()
        }
    }


    fun getAllOffers(){
        viewModelScope.launch {

            productRepository.getAllOffers()
        }
    }

}