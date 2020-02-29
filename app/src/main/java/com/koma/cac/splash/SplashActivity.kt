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

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.koma.cac.CACApplication
import com.koma.cac.R
import com.koma.cac.databinding.ActivitySplashBinding
import com.koma.commonlibrary.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private lateinit var interstitialAd: InterstitialAd

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.FullScreenTheme)

        initAd()

        observeData()
    }

    private fun initAd() {
        MobileAds.initialize(this)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = getString(R.string.interstitial_ad_unit_id)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
            }

            override fun onAdLoaded() {
            }

            override fun onAdFailedToLoad(i: Int) {
            }
        }
    }

    private fun observeData() {
        viewModel = ViewModelProvider(
            this,
            (applicationContext as CACApplication).appComponent.getViewModelFactory()
        ).get(SplashViewModel::class.java)
        viewModel.intent.observe(this) {
            val intent = it.getContentIfNotHandled()
            intent?.run {
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
        viewModel.start()
    }

    override fun onResume() {
        super.onResume()

        binding.adView.resume()
    }

    override fun onPause() {
        super.onPause()

        binding.adView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.adView.destroy()
    }

    override fun getLayoutId() = R.layout.activity_splash
}
