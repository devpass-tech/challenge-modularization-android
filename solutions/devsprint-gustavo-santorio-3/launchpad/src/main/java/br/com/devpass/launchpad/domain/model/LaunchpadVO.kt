package br.com.devpass.launchpad.domain.model

data class LaunchpadVO(
    val name: String,
    val fullName: String,
    val region: String,
    val launchAttempts: String,
    val launchSuccesses: String
)
