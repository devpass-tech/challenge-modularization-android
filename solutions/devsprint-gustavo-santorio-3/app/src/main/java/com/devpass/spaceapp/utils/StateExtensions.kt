package com.devpass.spaceapp.utils

fun Boolean.getStatus() : String =
    if(this)
        "sucesso"
    else
        "falha"