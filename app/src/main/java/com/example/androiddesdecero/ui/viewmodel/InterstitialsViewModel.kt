package com.example.androiddesdecero.ui.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.flow.MutableStateFlow

class InterstitialsViewModel : ViewModel() {


    private val interstitialAd =
        MutableStateFlow<InterstitialAd?>(null)

    /*
    pimer paso solicitar y cargar un anuncio desde los servidores
    de google admob
    para solicitar un anuncion utilizamo la clase InterstitialAd y el metodo
    estatico load() este metodo recibe 4 parametros
    1.-el contexto
    2.-el id del anuncio
    3.-AdRequest que representa una solicitud para cargar un nuevo anuncio
    4.-un callback que se utiliza para escuchar el resultado de la carga
     */
    fun solicitarAnuncio(contex: Context) {
        InterstitialAd.load(
            contex,
            "ca-app-pub-3940256099942544/1033173712",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    /*
                    se ejecta cuando existio un error al momento de cargar el anuncio
                     */
                    interstitialAd.value = null
                }

                override fun onAdLoaded(inter: InterstitialAd) {
                    /*
                    este método se ejecuta cuando se cargo correctamenten el anuncio,
                    este método ya nos entrega un objeto   InterstitialAd que representa el anuncio
                    cargado correctamente
                     */
                    interstitialAd.value = inter
                }
            }
        )
    }

    fun mostrarAnuncio(contex: Context){
        /*
        Para poder mostrar un anuncio utilizamos el método show este recibe como
        parametro una actividad
         */
        val activity = contex as?  Activity
        if(activity != null){
            interstitialAd.value?.fullScreenContentCallback=object :FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    /*
                    este método se ejecuta cuando el usuario cierra el anuncio por ejemplo
                    cuando preciona la x o cuando el anuncio ha finalizado
                     */
                    //le indicamos que no tenemos otro anuncio para mostrar
                    interstitialAd.value= null
                    solicitarAnuncio(contex)
                }
            }
            interstitialAd.value?.show(activity)
        }

    }
}