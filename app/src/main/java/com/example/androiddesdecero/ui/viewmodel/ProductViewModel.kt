package com.example.androiddesdecero.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androiddesdecero.model.Product
import com.example.androiddesdecero.util.ImageManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProductViewModel: ViewModel() {
    val imageManager = ImageManager()

    private val _product = MutableStateFlow(Product())
    val product: StateFlow<Product> get() = _product


    fun setName(name:String){
        _product.update {
            it.copy(name=name)
        }
    }
    fun setImage(){

    }
}