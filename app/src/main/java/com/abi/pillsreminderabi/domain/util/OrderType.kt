package com.abi.pillsreminderabi.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
