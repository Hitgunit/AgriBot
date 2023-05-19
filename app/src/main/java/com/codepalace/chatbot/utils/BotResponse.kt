package com.codepalace.chatbot.utils

import com.codepalace.chatbot.utils.Constants.OPEN_GOOGLE
import com.codepalace.chatbot.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("lanza") && message.contains("moneda") -> {
                val r = (0..1).random()
                val result = if (r == 0) "cara" else "águila"

                "Lanze una moneda y resulto en $result"
            }

            //Math calculations
            message.contains("resuelve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Lo siento, no pude resolverlo"
                }
            }

            //Hello
            message.contains("hola") || message.contains("onda") || message.contains("holi") || message.contains("tal") -> {
                when (random) {
                    0 -> "Hola compañero"
                    1 -> "Que onda"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }

            //How are you?
            message.contains("estas") || message.contains("estás")-> {
                when (random) {
                    0 -> "Estoy bien, muchas gracias"
                    1 -> "Excelente y tu que tal?"
                    2 -> "Muy bien, gracias!"
                    else -> "error"
                }
            }

            message.contains("excelente") || message.contains("bien") || message.contains("perfecto")-> {
                when (random) {
                    0 -> "Me alegro"
                    1 -> "Muy bien"
                    2 -> "Bien, asi debe de ser siempre"
                    else -> "error"
                }
            }

            message.contains("mal") || message.contains("jodido") || message.contains("triste")-> {
                when (random) {
                    0 -> "Lamento que estes asi, recuerda que siempre hay un mañana mejor"
                    1 -> "Lamento escuchar eso, espero y te sientas mejor"
                    2 -> ":("
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("día") && message.contains("hoy") || message.contains("hora")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "Prueba otra cosa"
                    1 -> "Prueba preguntando algo diferente"
                    2 -> "No entendi, pregunta algo diferente"
                    3 -> "Lo siento, no entendi tu pregunta"
                    else -> "error"
                }
            }
        }
    }
}