package com.ntduc.basemvvmarchitecture.view.ui.details.defaultphonecard

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ntduc.basemvvmarchitecture.R
import com.ntduc.basemvvmarchitecture.databinding.ActivityDefaultPhoneCardDetailBinding
import com.ntduc.basemvvmarchitecture.extensions.applyMaterialTransform
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.intentOf
import com.skydoves.whatif.whatIfNotNullAs

class DefaultPhoneCardDeltailActivity :
    BindingActivity<ActivityDefaultPhoneCardDetailBinding>(R.layout.activity_default_phone_card_detail) {

//    private val posterId: Long by bundle(EXTRA_POSTER_ID, -1)
//    private val posterName: String by bundleNonNull(EXTRA_POSTER_NAME)
//    private val viewModel: PosterDetailViewModel by viewModel { parametersOf(posterId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        applyMaterialTransform("posterName")
        super.onCreate(savedInstanceState)
    }

    companion object {

        fun startActivity(context: Context?, startView: View) {
            context.whatIfNotNullAs<Activity> {
                it.intentOf<DefaultPhoneCardDeltailActivity> {
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        it,
                        startView,
                        "Default Phone Card Detail"
                    )
                    startActivity(it, options.toBundle())
                }
            }
        }
    }
}