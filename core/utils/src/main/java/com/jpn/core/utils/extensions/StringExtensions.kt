package com.jpn.core.utils.extensions

import com.jpn.core.utils.security.CryptoUtils


fun String.toEncryptString(): String {
    return CryptoUtils.encrypt(this)
}

fun String.toDecryptString(): String {
    return CryptoUtils.decrypt(this)
}