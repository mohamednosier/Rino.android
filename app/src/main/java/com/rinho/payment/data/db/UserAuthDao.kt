package com.rinho.payment.data.db

import com.rinho.payment.models.UserAuth
import javax.inject.Inject

/**
 * Manage the [UserAuth] entity (table).
 */
class UserAuthDao @Inject constructor() {


    /**
     * insert an instance of user response into database.
     */
    fun insertOrUpdateUserAuth(userAuth: UserAuth) = insertOrUpdate(userAuth)


    /**
     * get the stored user from the db .
     * @return UserAuth the stored user
     */
    fun getUserAuth() = queryFirst<UserAuth>()


    /**
     * delete the user.
     */
    fun deleteUserAuth() = delete<UserAuth>()


    /**
     * get the user token
     */
    fun getUserToken() = getUserAuth()?.access_token


    /**
     * get the user token type
     */
//    fun getUserTokenType() = getUserAuth()?.tokenType
    fun getUserTokenType() = "Bearer "
}