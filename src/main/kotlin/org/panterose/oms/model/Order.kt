package org.panterose.oms.model

data class Order(val id: Int, val cutomerId: Int, val produceId: Int, val quantity: Int, val amount: Double)