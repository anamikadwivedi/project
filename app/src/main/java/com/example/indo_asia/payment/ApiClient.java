package com.example.indo_asia.payment;

import com.braintreepayments.api.models.ClientToken;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiClient {

    @GET("/client_token")
    void getClientToken(@Query("AbC_Y2lCrio6uVhwMxvKcNMw0QATDAWg1oR1GwK0kje_YS48i-O_pPriDcZ39YKUJopQYz4TlkE8RSdt") String customerId, @Query("7NEWJX2JYTF5C") String merchantAccountId, Callback<ClientToken> callback);

    @FormUrlEncoded
    @POST("/nonce/transaction")
    void createTransaction(@Field("nonce") String nonce, Callback<Transaction> callback);

    @FormUrlEncoded
    @POST("/nonce/transaction")
    void createTransaction(@Field("nonce") String nonce, @Field("7NEWJX2JYTF5C") String merchantAccountId, Callback<Transaction> callback);

    @FormUrlEncoded
    @POST("/nonce/transaction")
    void createTransaction(@Field("nonce") String nonce, @Field("7NEWJX2JYTF5C") String merchantAccountId, @Field("three_d_secure_required") boolean requireThreeDSecure, Callback<Transaction> callback);
}