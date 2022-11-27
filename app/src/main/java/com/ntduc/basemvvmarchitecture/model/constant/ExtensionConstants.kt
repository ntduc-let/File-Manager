package com.ntduc.basemvvmarchitecture.model.constant

import com.ntduc.basemvvmarchitecture.R
import java.io.File

object ExtensionConstants {

    val PDF = arrayOf(
        "pdf"
    )

    val XLS = arrayOf(
        "xls",
        "xlsx"
    )

    val PPT = arrayOf(
        "ppt",
        "pptx"
    )

    val TXT = arrayOf(
        "txt"
    )

    val DOC = arrayOf(
        "doc",
        "docx"
    )

    val WPS = arrayOf(
        "wps"
    )

    val ALL_DOCUMENT = arrayListOf(
        *PDF,
        *XLS,
        *PPT,
        *TXT,
        *DOC,
        *WPS
    )

    val VIDEO = arrayOf(
        "mp4"
    )

    val IMAGE = arrayOf(
        "jpeg",
        "jpg",
        "png",
        "psd",
        "eps",
        "gif"
    )

    val AUDIO = arrayOf(
        "mp3",
        "m4a"
    )

    val APK = arrayOf(
        "apk"
    )

    fun getTypeFile(path: String): FileType {
        val extension = File(path).extension
        return when {
            PDF.contains(extension) -> FileType.PDF
            XLS.contains(extension) -> FileType.XLS
            PPT.contains(extension) -> FileType.PPT
            TXT.contains(extension) -> FileType.TXT
            DOC.contains(extension) -> FileType.DOC
            WPS.contains(extension) -> FileType.WPS
            VIDEO.contains(extension) -> FileType.VIDEO
            APK.contains(extension) -> FileType.APK
            IMAGE.contains(extension) -> FileType.IMAGE
            AUDIO.contains(extension) -> FileType.AUDIO
            else -> FileType.OTHER
        }
    }

    fun getIconFile(path: String): Int {
        val file = File(path)
        if (file.isDirectory) return R.drawable.ic_folder_50dp

        val extension = file.extension
        return when {
            PDF.contains(extension) -> R.drawable.ic_pdf_50dp
            XLS.contains(extension) -> R.drawable.ic_xls_50dp
            PPT.contains(extension) -> R.drawable.ic_ppt_50dp
            TXT.contains(extension) -> R.drawable.ic_txt_50dp
            DOC.contains(extension) -> R.drawable.ic_doc_50dp
            WPS.contains(extension) -> R.drawable.ic_wps_50dp
            VIDEO.contains(extension) -> R.drawable.ic_video_50dp
            APK.contains(extension) -> R.drawable.ic_apk_50dp
            IMAGE.contains(extension) -> R.drawable.ic_image_50dp
            AUDIO.contains(extension) -> R.drawable.ic_audio_50dp
            else -> R.drawable.ic_file_50dp
        }
    }
}