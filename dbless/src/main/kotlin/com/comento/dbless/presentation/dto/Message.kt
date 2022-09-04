package com.comento.dbless.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

// 1. Schema Title
@Schema(title = "[Message] 메세지 DTO") // 1. Schema title
data class Message(
    @Schema(title = "메세지 ID", example = "id1") val id: String, // 2.Schema
    @Schema(title = "메세지 text", example = "good") val text: String?
)