package com.example.loodoscryptoapp.domain.model

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("asset_id"                         ) var asset_id                     : String? = null,
    @SerializedName("id_icon"                          ) var id_icon                      : String? = null,
    @SerializedName("name"                             ) var name                         : String? = null,
    @SerializedName("price_usd"                        ) var price_usd                    : Double? = null,
)