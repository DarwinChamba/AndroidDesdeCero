package com.example.androiddesdecero.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.androiddesdecero.model.Product
import com.example.androiddesdecero.util.ImageManager
import com.example.androiddesdecero.util.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProductViewModel : ViewModel() {
    val imageManager = ImageManager()

    private val _product = MutableStateFlow(Product())
    val product: StateFlow<Product> get() = _product

    private val _uriImage = MutableStateFlow<Uri?>(null)
    val uriImage: StateFlow<Uri?> get() = _uriImage

    private val _result = MutableStateFlow<ProductState<String>>(ProductState.Idle)
    val  result : StateFlow<ProductState<String>> get() = _result

    init {
      getAllProduct()
    }

    fun getAllProduct(){
        imageManager.getAllProduct{

        }
    }

    fun setName(name: String) {
        _product.update {
            it.copy(name = name)
        }
    }

    fun setImage(uri: Uri) {
        _uriImage.value = uri
    }

    fun insertProduct(context: Context){
        _uriImage.value?.let {uri->
            val ruta =imageManager.uriToPath(context,uri)
            _product.update {
                it.copy(image = ruta)
            }
        }

        imageManager.insert(_product.value){
            _result.value = it
        }
        reset()
    }

    fun reset(){
        _result.value = ProductState.Idle
        _uriImage.value = null
        _product.value = Product()
    }




}