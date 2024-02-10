package com.mohassan.homecompass.core.utils

import android.annotation.SuppressLint
import com.mohassan.homecompass.core.utils.Constants.OPEN_GOOGLE
import com.mohassan.homecompass.core.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

object BotResponse {

    @SuppressLint("SimpleDateFormat")
    fun basicResponses(userMessage: String): String {

        val random = (0..2).random()
        val message = userMessage.lowercase(Locale.ROOT)

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") || message.contains("hi") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Hi"
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "Fine thanks"
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("what") && message.contains("time") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            // speak arabic?
            message.contains("speak") && message.contains("arabic") -> {
                "للاسف لا استطيع التحدث بالعربيه الان، لكني تحت التطوير وقد ادعم اللغه العربيه قريبا"
            }

            //Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search for") || message.contains("search about") -> {
                OPEN_SEARCH
            }

            //How are you?
            message.contains("hungry") || message.contains("food") -> {
                when (random) {
                    0 -> "Go to food section in main screen"
                    1 -> "I'm sad to hear that, go to food section in main screen"
                    2 -> "See the the suitable restaurant in the main screen"
                    else -> "error"
                }
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "What you mean?"
                    else -> "error"
                }
            }
        }
    }
}