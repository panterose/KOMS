package org.panterose.oms.web


import org.panterose.oms.model.Order
import org.panterose.oms.model.Product
import org.panterose.oms.model.User
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class WelcomeHandler {
    fun welcome(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("index")
}

@Component
class UserHandler() {
    val users = mutableMapOf<String, User>()
    fun find(name: String) : User? = users[name];

    fun all(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/users/index", mapOf("users" to users.values))
    fun addPage(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/users/add")
    fun add(serverRequest: ServerRequest) = serverRequest.body(BodyExtractors.toFormData())
            .map {
                val formData = it.toSingleValueMap()
                val name = formData["name"]!!
                users[name] = User(
                        name = name,
                        password = formData["password"]!!,
                        company = formData["company"]!!)
            }
            .then(all(serverRequest))
    fun view(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/users/view", mapOf("user" to find(serverRequest.queryParam("id").get())))
}

@Component
class ProductHandler {
    val products = mutableMapOf<Int, Product>()

    fun all(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/products/index", mapOf("products" to products.values))
    fun addPage(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/products/add")
    fun add(serverRequest: ServerRequest) = serverRequest.body(BodyExtractors.toFormData())
            .map {
                val formData = it.toSingleValueMap()
                val id = formData["id"]!!.toInt()
                products[id] = Product(
                        id = id,
                        price = formData["price"]!!.toDouble(),
                        description = formData["description"]!!)
            }
            .then(all(serverRequest))
    fun view(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/products/view", products[serverRequest.queryParam("id").get().toInt()])
}

@Component
class OrderHandler {
    val orders = mutableMapOf<Int, Order>()

    fun all(serverRequest: ServerRequest) = ok().contentType(MediaType.TEXT_HTML).render("/order/all", orders)
}