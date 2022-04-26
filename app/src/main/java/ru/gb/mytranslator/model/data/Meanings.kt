package ru.gb.mytranslator.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meanings(
    @SerializedName("translation")
    @Expose
    val translation: Translation?,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String?
)
