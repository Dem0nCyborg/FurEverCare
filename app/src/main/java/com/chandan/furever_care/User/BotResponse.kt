package com.chandan.furever_care.User

import com.chandan.furever_care.User.Constants.OPEN_SEARCH

object BotResponse {

    fun basicResponse(_message : String) : String{
        val random = (0..2).random()
        val random0 = (0..45).random()
        val message = _message.toLowerCase()

        return when {

            message.contains("hello") ->{
                when(random){
                    0-> "Hello there..!"
                    1-> "Sup"
                    2-> "Namastey 🙏"
                    else -> "error"
                }
            }

            message.contains("names") && message.contains("pet") ->{
                when(random0){
                    0 -> "Buddy"
                    1 -> "Luna"
                    2 -> "Charlie"
                    3 -> "Bella"
                    4 -> "Max"
                    5 -> "Lucy"
                    6 -> "Cooper"
                    7 -> "Daisy"
                    8 -> "Rocky"
                    9 -> "Sadie"
                    10 -> "Oliver"
                    11 -> "Molly"
                    12 -> "Leo"
                    13 -> "Coco"
                    14 -> "Bentley"
                    15 -> "Lily"
                    16 -> "Toby"
                    17 -> "Roxy"
                    18 -> "Milo"
                    19 -> "Zoe"
                    20 -> "Bear"
                    21 -> "Chloe"
                    22 -> "Bailey"
                    23 -> "Sophie"
                    24 -> "Duke"
                    25 -> "Lola"
                    26 -> "Jack"
                    27 -> "Maggie"
                    28 -> "Teddy"
                    29 -> "Ruby"
                    30 -> "Winston"
                    31 -> "Rosie"
                    32 -> "Zeus"
                    33 -> "Lulu"
                    34 -> "Oscar"
                    35 -> "Zara"
                    36 -> "Charlie"
                    37 -> "Gracie"
                    38 -> "Loki"
                    39 -> "Mia"
                    40 -> "Raja"
                    41 -> "Chitra"
                    42 -> "Simba"
                    43 -> "Lakshmi"
                    44 -> "Raj"
                    45 -> "Golu"
                    else -> "Unknown"
                }
            }

            message.contains("search") -> {
                OPEN_SEARCH
            }



            else-> {



                when(random){
                    0->"I don't understand...!"
                    1->"I don't Know"
                    2->"Cannot help with that..!"
                    else->"error"
                }
            }
        }

    }


}