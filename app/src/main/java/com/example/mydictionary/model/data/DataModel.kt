package com.example.mydictionary

import com.example.mydictionary.model.data.Meanings
import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)