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

package com.koma.cac.splash

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koma.authlibrary.AuthActivity
import com.koma.cac.MainActivity
import com.koma.cac.data.source.Repository
import com.koma.commonlibrary.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel constructor(
    private val repository: Repository,
    application: Application
) :
    AndroidViewModel(application) {
    private val _intent = MutableLiveData<Event<Intent>>()
    val intent: LiveData<Event<Intent>>
        get() = _intent

    fun start() {
        viewModelScope.launch {
            val intent = if (repository.isLogin()) {
                Intent(getApplication(), MainActivity::class.java)
            } else {
                Intent(getApplication(), AuthActivity::class.java)
            }
            delay(1500)
            _intent.postValue(Event(intent))
        }
    }
}
