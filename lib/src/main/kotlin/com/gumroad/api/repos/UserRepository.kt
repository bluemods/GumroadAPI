package com.gumroad.api.repos

import com.gumroad.api.results.UserResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages users
 */
interface UserRepository {

    /**
     * Retrieve the data of the authenticated user.
     */
    @GET("user")
    fun getUser(): Call<UserResult>
}