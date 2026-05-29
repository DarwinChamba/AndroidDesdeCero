package com.example.androiddesdecero.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.androiddesdecero.model.AlarmRepository
import com.example.androiddesdecero.model.MyNotification
import com.example.androiddesdecero.util.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class NotificationViewModel: ViewModel() {
private  val repository = AlarmRepository()
    private val  _notification= MutableStateFlow(MyNotification())
    val notification: StateFlow<MyNotification> get() = _notification

    fun setTitle(title:String){
        _notification.update{
            it.copy(title=title)
        }
    }

    fun setDescription(description: String){
        _notification.update {
            it.copy(description=description)
        }
    }

    fun setTime(time:Long){
        _notification.update {
            it.copy(time=time)
        }
    }

    fun createNotification(context: Context){
     repository.programarNotificacion(context,_notification.value)
        _notification.value = MyNotification()
    }

    fun enabledButton(): Boolean{
        val notification=_notification.value
        return notification.description.isNotEmpty() && notification.title.isNotEmpty()
    }




}