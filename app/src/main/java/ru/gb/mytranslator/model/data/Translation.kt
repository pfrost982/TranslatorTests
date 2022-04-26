package ru.gb.mytranslator.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translation(
    @SerializedName("text")
    @Expose
    val translation: String?
)
