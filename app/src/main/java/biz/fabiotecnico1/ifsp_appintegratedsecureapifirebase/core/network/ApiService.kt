package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.AuthRequest
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.AuthResponse
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.ItemDto
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): retrofit2.Response<AuthResponse>

    @GET("items")
    suspend fun getItems(): retrofit2.Response<List<ItemDto>>

    @POST("items")
    suspend fun createItem(@Body item: ItemDto): retrofit2.Response<ItemDto>

    @PUT("items/{id}")
    suspend fun updateItem(
        @Path("id") id: String,
        @Body item: ItemDto
    ): retrofit2.Response<ItemDto>

    @DELETE("items/{id}")
    suspend fun deleteItem(@Path("id") id: String): retrofit2.Response<Unit>
}