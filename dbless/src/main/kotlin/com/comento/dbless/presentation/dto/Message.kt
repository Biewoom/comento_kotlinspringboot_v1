package com.comento.dbless.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "[Message] 메세지 DTO")
data class Message(
    @Schema(title = "메세지 ID", example = "id1") val id: String,
    @Schema(title = "메세지 text", example = "good") val text: String?
)
