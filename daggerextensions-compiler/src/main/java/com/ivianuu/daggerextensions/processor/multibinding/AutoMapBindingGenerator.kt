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

package com.ivianuu.daggerextensions.processor.multibinding

import com.ivianuu.daggerextensions.processor.util.toLowerCaseCamel
import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.lang.model.element.Modifier

/**
 * @author Manuel Wrage (IVIanuu)
 */
class AutoMapBindingGenerator(private val descriptor: AutoMapBindingDescriptor) {

    fun generate(): JavaFile {
        val module = TypeSpec.classBuilder(descriptor.moduleName)
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addAnnotation(Module::class.java)
            .addMethod(bindsMethod())

        return JavaFile.builder(descriptor.moduleName.packageName(), module.build())
            .build()
    }

    private fun bindsMethod(): MethodSpec {
        return MethodSpec.methodBuilder("bind${descriptor.itemName.simpleName()}")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addAnnotation(Binds::class.java)
            .addAnnotation(IntoMap::class.java)
            .addAnnotation(AnnotationSpec.get(descriptor.mapKey))
            .addParameter(descriptor.itemName, descriptor.itemName.simpleName().toLowerCaseCamel())
            .returns(descriptor.type)
            .build()
    }
}