package org.fierg.camel.utils

import org.fierg.model.dto.GameDTO

object Utils {
    fun gameStringFromDTO(gameDTO: GameDTO) : String {
        val sb = StringBuilder()
        sb.append("${gameDTO.row1!![0]} + ${gameDTO.row1!![1]} = ${gameDTO.row1!![2]}\n")
        sb.append("${gameDTO.row2!![0]} + ${gameDTO.row2!![1]} = ${gameDTO.row2!![2]}\n")
        sb.append("${gameDTO.row3!![0]} + ${gameDTO.row3!![1]} = ${gameDTO.row3!![2]}\n")
        return sb.toString()
    }
}