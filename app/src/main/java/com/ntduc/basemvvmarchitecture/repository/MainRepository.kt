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

package com.ntduc.basemvvmarchitecture.repository

import androidx.annotation.WorkerThread
import com.ntduc.basemvvmarchitecture.R
import com.ntduc.basemvvmarchitecture.mapper.ErrorResponseMapper
import com.ntduc.basemvvmarchitecture.model.*
import com.ntduc.basemvvmarchitecture.network.DisneyService
import com.ntduc.basemvvmarchitecture.persistence.PosterDao
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber

class MainRepository constructor(
    private val disneyService: DisneyService,
    private val posterDao: PosterDao
) : Repository {

    init {
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    fun loadDisneyPosters(
        onSuccess: () -> Unit,
    ) = flow {
        val posters: List<Poster> = posterDao.getPosterList()
        if (posters.isEmpty()) {
            // request API network request asynchronously.
            disneyService.fetchDisneyPosterList()
                // handles the success case when the API request gets a successful response.
                .suspendOnSuccess {
                    posterDao.insertPosterList(data)
                    emit(data)
                }
                /**
                 * handles error cases when the API request gets an error response.
                 * e.g., internal server error.
                 * maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper.
                 */
                .onError(ErrorResponseMapper) {
                    Timber.d("[Code: $code]: $message")
                }
                // handles exceptional cases when the API request gets an exception response.
                // e.g., network connection error.
                .onException {
                    Timber.d(message())
                }
        } else {
            emit(posters)
        }
    }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadDocuments(
        onSuccess: () -> Unit,
    ) = flow {
        val posters: List<Document> = getDocumentList()
        emit(posters)
    }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)

    private fun getDocumentList(): List<Document> {
        val documents = arrayListOf<Document>()
        documents.add(Document(id = 0, name = "All", src = R.drawable.ic_all_document_50dp))
        documents.add(Document(id = 1, name = "PDF", src = R.drawable.ic_pdf_50dp))
        documents.add(Document(id = 2, name = "XLS", src = R.drawable.ic_xls_50dp))
        documents.add(Document(id = 3, name = "PPT", src = R.drawable.ic_ppt_50dp))
        documents.add(Document(id = 4, name = "TXT", src = R.drawable.ic_txt_50dp))
        documents.add(Document(id = 5, name = "DOC", src = R.drawable.ic_doc_50dp))
        documents.add(Document(id = 6, name = "WPS", src = R.drawable.ic_wps_50dp))

        return documents
    }

    @WorkerThread
    fun loadMedias(
        onSuccess: () -> Unit,
    ) = flow {
        val posters: List<Media> = getMediaList()
        emit(posters)
    }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)

    private fun getMediaList(): List<Media> {
        val medias = arrayListOf<Media>()
        medias.add(Media(id = 0, name = "Image", src = R.drawable.ic_image_50dp))
        medias.add(Media(id = 1, name = "Video", src = R.drawable.ic_video_50dp))
        medias.add(Media(id = 2, name = "Audio", src = R.drawable.ic_audio_50dp))

        return medias
    }

    @WorkerThread
    fun loadApps(
        onSuccess: () -> Unit,
    ) = flow {
        val posters: List<App> = getAppList()
        emit(posters)
    }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)

    private fun getAppList(): List<App> {
        val apps = arrayListOf<App>()
        apps.add(App(id = 0, name = "Install App", src = R.drawable.ic_app_50dp))
        apps.add(App(id = 1, name = "APK", src = R.drawable.ic_apk_50dp))

        return apps
    }
}
