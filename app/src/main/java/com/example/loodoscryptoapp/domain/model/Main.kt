package com.example.loodoscryptoapp.domain.model

import com.google.gson.annotations.SerializedName


data class Main (

  @SerializedName("id"                               ) var id                           : String? = null,
  @SerializedName("symbol"                           ) var symbol                       : String? = null,
  @SerializedName("name"                             ) var name                         : String? = null,
  @SerializedName("image"                            ) var image                        : String? = null,
  @SerializedName("current_price"                    ) var currentPrice                 : Double?    = null,
)