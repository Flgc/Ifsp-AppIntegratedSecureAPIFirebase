package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network

package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("/auth/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @GET("/items")
    suspend fun getItems(): Response<List<ItemDto>>

    @POST("/items")
    suspend fun createItem(@Body item: ItemDto): Response<ItemDto>

    @PUT("/items/{id}")
    suspend fun updateItem(@Path("id") id: String, @Body item: ItemDto): Response<ItemDto>

    @DELETE("/items/{id}")
    suspend fun deleteItem(@Path("id") id: String): Response<Unit>
}