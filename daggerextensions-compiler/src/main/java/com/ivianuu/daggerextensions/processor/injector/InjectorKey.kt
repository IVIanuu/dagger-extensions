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

package com.ivianuu.daggerextensions.processor.injector

import com.squareup.javapoet.ClassName

/**
 * @author Manuel Wrage (IVIanuu)
 */
data class InjectorKey(
    val baseType: ClassName,
    val mapKey: ClassName
) {
   companion object {
       val DAGGER_SUPPORTED_TYPES = listOf(
           // activity
           InjectorKey(
               ClassName.get("android.app", "Activity"),
               ClassName.get("dagger.android", "ActivityKey")
           ),
           // broadcast receiver
           InjectorKey(
               ClassName.get("android.content", "BroadcastReceiver"),
               ClassName.get("dagger.android", "BroadcastReceiverKey")
           ),
           // content provider
           InjectorKey(
               ClassName.get("android.content", "ContentProvider"),
               ClassName.get("dagger.android", "ContentProviderKey")
           ),
           // fragment
           InjectorKey(
               ClassName.get("android.app", "Fragment"),
               ClassName.get("dagger.android", "FragmentKey")
           ),
           // service
           InjectorKey(
               ClassName.get("android.app", "Service"),
               ClassName.get("dagger.android", "ServiceKey")
           ),
           // support fragments
           InjectorKey(
               ClassName.get("android.support.v4.app", "Fragment"),
               ClassName.get("dagger.android.support", "FragmentKey")
           )
       )
   }
}