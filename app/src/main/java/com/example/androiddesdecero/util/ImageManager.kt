package com.example.androiddesdecero.util

import android.content.Context
import android.net.Uri
import coil.util.CoilUtils.result
import com.example.androiddesdecero.model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.File
import java.io.FileOutputStream
import java.util.UUID


class ImageManager {
    /*
    creamos una referencia de la base de datos que apunte al nodo product

    https://pruebas-a5600-default-rtdb.firebaseio.com/product/sdfsdds/
    id:ewrre
    name:"laptop"
    image:"dsf"
     */
    val reference = FirebaseDatabase.getInstance().getReference("product")

    fun uriToPath(context: Context, uri: Uri): String {
        /*
        abre el flujo de lectura para leer los bytes  que contiene la uri
         */
        val inputStream = context.contentResolver.openInputStream(uri)
        /*
        creamos un archivo que apunta al almacenamiento interno del dispositivo

         */
        val file = File(context.filesDir, "image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file.absolutePath

    }

    fun deleteImageLocal(image:String){
        val image =File(image)
        if(image.exists()){
            image.delete()
        }
    }

    fun insert(product: Product,producState:(ProductState<String>)->Unit) {
        val id = UUID.randomUUID().toString()
        producState(ProductState.Loading)
        reference.child(id).setValue(product.copy(id=id)).addOnSuccessListener {
            producState(ProductState.Success("registro realizado con exito"))
        }.addOnFailureListener {
            producState(ProductState.Error(it.message.toString()))
        }
    }

    fun getAllProduct(result:(ProductState<List<Product>>)->Unit){
        result(ProductState.Loading)
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(data: DataSnapshot) {
                if(data.exists()){
                    val lista = mutableListOf<Product>()
                    for(d in data.children){
                        val product = d.getValue(Product::class.java)
                        product?.let {
                            lista.add(it)
                            println(it)
                        }
                    }
                    result(ProductState.Success(lista))
                }else{
              result(ProductState.Error("No hay productos"))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                result(ProductState.Error(error.message))
            }

        })
    }

    fun updateProduct(product: Product,producState:(ProductState<String>)->Unit) {

        producState(ProductState.Loading)
        reference.child(product.id).setValue(product).addOnSuccessListener {
            producState(ProductState.Success("registro modificado con exito"))
        }.addOnFailureListener {
            producState(ProductState.Error(it.message.toString()))
        }
    }
}