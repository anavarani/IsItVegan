package com.varani.isitvegan

/**
 * Created by Ana Varani on 04/04/2023.
 */
val Any.TAG: String
    get() {
        val name = javaClass.simpleName
        return if (name.length <= 23) name else name.substring(0, 23)
    }