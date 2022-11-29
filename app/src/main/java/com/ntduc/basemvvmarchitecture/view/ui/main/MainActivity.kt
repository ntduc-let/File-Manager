/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
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

package com.ntduc.basemvvmarchitecture.view.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ntduc.basemvvmarchitecture.R
import com.skydoves.bindables.BindingActivity
import com.ntduc.basemvvmarchitecture.databinding.ActivityMainBinding
import com.ntduc.basemvvmarchitecture.extensions.applyExitMaterialTransform
import com.ntduc.basemvvmarchitecture.view.adapter.AppAdapter
import com.ntduc.basemvvmarchitecture.view.adapter.DocumentAdapter
import com.ntduc.basemvvmarchitecture.view.adapter.MediaAdapter
import com.ntduc.basemvvmarchitecture.view.ui.details.defaultphonecard.DefaultPhoneCardDeltailActivity
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)

        binding {
            mainContent.documentAdapter = DocumentAdapter()
            mainContent.mediaAdapter = MediaAdapter()
            mainContent.appAdapter = AppAdapter()
            mainContent.viewModel = getViewModel()
        }

        binding {
            (mainContent.rcvDocument.layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0) 2 else 1
                    }
                }
        }

        binding.mainContent.defaultPhoneCard.setOnClickListener {
            DefaultPhoneCardDeltailActivity.startActivity(this, binding.mainContent.defaultPhoneCard)
        }
    }
}
