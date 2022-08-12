package br.com.devpass.launchlist.domain

data class LaunchesDTO(
    val docs: List<LaunchDTO>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int,
    val page: Int,
    val pagingCounter: Int,
    val prevPage: Int,
    val totalDocs: Int,
    val totalPages: Int
)

data class LaunchDTO(
    val auto_update: Boolean,
    val capsules: List<Any>,
    val cores: List<Core>,
    val crew: List<Any>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Int,
    val date_utc: String,
    val details: String,
    val failures: List<Any>,
    val fairings: Fairings,
    val flight_number: Int,
    val id: String,
    val launchpad: String,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<String>,
    val static_fire_date_unix: Int,
    val static_fire_date_utc: String,
    val success: Boolean,
    val tdb: Boolean,
    val upcoming: Boolean,
    val window: Int
)

data class Core(
    val core: String,
    val flight: Int,
    val gridfins: Boolean,
    val landing_attempt: Boolean,
    val landing_success: Boolean,
    val landing_type: String,
    val landpad: String,
    val legs: Boolean,
    val reused: Boolean
)

data class Fairings(
    val recovered: Boolean,
    val recovery_attempt: Boolean,
    val reused: Boolean,
    val ships: List<String>
)

data class Links(
    val article: String,
    val flickr: Flickr,
    val patch: Patch,
    val presskit: String,
    val reddit: Reddit,
    val webcast: String,
    val wikipedia: String,
    val youtube_id: String
)

data class Flickr(
    val original: List<String>,
    val small: List<Any>
)

data class Patch(
    val large: String,
    val small: String
)

data class Reddit(
    val campaign: String,
    val launch: String,
    val media: String,
    val recovery: Any
)