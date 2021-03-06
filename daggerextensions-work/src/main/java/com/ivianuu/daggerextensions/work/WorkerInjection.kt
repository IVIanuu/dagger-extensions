/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.daggerextensions.work

import androidx.work.Worker
import dagger.MapKey
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds
import kotlin.reflect.KClass

@MapKey
annotation class WorkerKey(val value: KClass<out Worker>)

interface HasWorkerInjector {
    fun workerInjector(): AndroidInjector<Worker>
}

@Module
abstract class WorkerInjectionModule {
    @Multibinds
    abstract fun workerInjectorFactories(): Map<Class<out Worker>, AndroidInjector.Factory<out Worker>>
}

object WorkerInjection {

    fun inject(worker: Worker) {
        val hasWorkerInjector = findHasWorkerInjector(worker)
        val workerInjector = hasWorkerInjector.workerInjector()
        workerInjector.inject(worker)
    }

    private fun findHasWorkerInjector(worker: Worker): HasWorkerInjector {
        val applicationContext = worker.applicationContext

        if (applicationContext is HasWorkerInjector) {
            return applicationContext
        }

        throw IllegalArgumentException("no injector found for ${worker.javaClass.name}")
    }

}