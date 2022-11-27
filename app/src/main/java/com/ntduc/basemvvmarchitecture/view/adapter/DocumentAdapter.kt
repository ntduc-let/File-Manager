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

package com.ntduc.basemvvmarchitecture.view.adapter

import android.view.View
import com.ntduc.basemvvmarchitecture.R
import com.ntduc.basemvvmarchitecture.model.Document
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.ntduc.basemvvmarchitecture.view.viewholder.ItemDocumentViewHolder

class DocumentAdapter : BaseAdapter() {

  init {
    addSection(arrayListOf<Document>())
  }

  fun addDocumentList(documents: List<Document>) {
    sections().first().run {
      clear()
      addAll(documents)
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_document

  override fun viewHolder(layout: Int, view: View) = ItemDocumentViewHolder(view)
}
