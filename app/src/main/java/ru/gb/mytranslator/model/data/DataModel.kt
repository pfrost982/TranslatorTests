package ru.gb.mytranslator.model.data

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class DataModel(
    @SerializedName("text")
    @Expose
    val text: String?,
    @SerializedName("meanings")
    @Expose
    val meanings: List<Meanings>?
)
