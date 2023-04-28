package com.varani.data.network

import javax.inject.Qualifier

/**
 * Created by Ana Varani on 17/04/2023.
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val iivDispatchers: IivDispatchers)

enum class IivDispatchers {
    IO,
}