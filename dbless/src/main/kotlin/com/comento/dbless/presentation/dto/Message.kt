package com.comento.dbless.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

// 1. Schema Title
@Schema(title = "[Message] 메세지 DTO")
data class Message(
    val id: String,
    val text: String?
)