/*
 * Copyright 2020 komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.koma.cac

import android.app.Application
import com.koma.cac.di.AppComponent
import com.koma.cac.di.DaggerAppComponent
import com.koma.cac.di.RepositoryModule
import com.koma.commonlibrary.di.ApplicationModule

class CACApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule())
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
