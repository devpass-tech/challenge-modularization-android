package com.devpass.spaceapp.rocketdetails.domain.dto

import com.google.gson.annotations.SerializedName

data class RocketDetailDTO(
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val diameter: Diameter,
    val engines: Engines,
    val firstFlight: String,
    val firstStage: FirstStage,
    val flickr_images: List<String>,
    val height: Height,
    val id: String,
    val landingLegs: LandingLegs,
    val mass: Mass,
    val name: String,
    val payloadWeights: List<PayloadWeight>,
    val second_stage: SecondStage,
    val stages: Int,
    val sucessRatePCT: Int,
    val type: String,
    val wikipedia: String
)