package com.abi.pillsreminderabi.domain.util

sealed class HorarioOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): HorarioOrder(orderType)
    class Date(orderType: OrderType): HorarioOrder(orderType)
    class Color(orderType: OrderType): HorarioOrder(orderType)

    fun copy(orderType: OrderType): HorarioOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}