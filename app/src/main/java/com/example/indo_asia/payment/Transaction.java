package com.example.indo_asia.payment;

import com.google.gson.annotations.SerializedName;

class Transaction {

    @SerializedName("message")
    private String mMessage;

    public String getMessage() {
        return mMessage;
    }
}
