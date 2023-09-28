package com.abi.pillsreminderabi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abi.pillsreminderabi.ui.theme.BabyBlue
import com.abi.pillsreminderabi.ui.theme.LightGreen
import com.abi.pillsreminderabi.ui.theme.RedOrange
import com.abi.pillsreminderabi.ui.theme.RedPink
import com.abi.pillsreminderabi.ui.theme.Violet

@Entity
data class HorarioPastilla(
    val nombrePastilla:String,
    val descripcionPastilla:String,
    val horariosGeneradosPastilla:String,
    val cantidadPastillasPorDia:String,
    val cantidadPastillasTotales:String,
    val diasPastillas:String,

    val timestamp: Long,
    val color:Int,

    @PrimaryKey val id:Int?=null

){
    companion object{
        val noteColors= listOf(RedOrange,LightGreen,Violet, BabyBlue, RedPink)
    }
}
class InvalidNoteException(message: String): Exception(message)