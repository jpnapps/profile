package com.jpn.feature.favorites.data.local.entity



//@Entity(tableName = "favorite_items")
data class FavoriteItemEntity(
    //@PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,  // can be URL or local URI string
    val description: String
)