package com.ajidres.movies.data.dbCloud

const val CLOUD_LOCATION="locations"

data class LocationCloudStore (
    val lat:Double,
    val long:Double
)

fun LocationCloudStore.toHasMap(): HashMap<String, Double> {
    return hashMapOf(
        "lat" to lat,
        "long" to long,
    )
}

fun MutableMap<String, Any>.toLocationCloudStore():LocationCloudStore{
    return LocationCloudStore(
        lat= this["lat"]!! as Double,
        long = this["long"]!! as Double
    )
}