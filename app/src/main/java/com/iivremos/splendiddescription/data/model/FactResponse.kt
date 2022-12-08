package com.iivremos.splendiddescription.data.model

class FactResponse : ArrayList<FactResponseItem>()

data class FactResponseItem(
    val fact: String
)