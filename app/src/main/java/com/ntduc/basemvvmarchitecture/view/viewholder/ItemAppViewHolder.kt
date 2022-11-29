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

package com.ntduc.basemvvmarchitecture.view.viewholder

import android.view.View
import androidx.core.view.ViewCompat
import com.ntduc.basemvvmarchitecture.databinding.ItemAppBinding
import com.ntduc.basemvvmarchitecture.model.App
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class ItemAppViewHolder(view: View) : BaseViewHolder(view) {

    private lateinit var data: App
    private val binding: ItemAppBinding by bindings()

    override fun bindData(data: Any) {
        if (data is App) {
            this.data = data
            drawItemUI()
        }
    }

    private fun drawItemUI() {
        binding.apply {
            ViewCompat.setTransitionName(binding.itemContainer, data.name)
            app = data
            executePendingBindings()
        }
    }

    override fun onClick(p0: View?) {

    }
//    PosterDetailActivity.startActivity(context, binding.itemContainer, data)

    override fun onLongClick(p0: View?) = false
}
