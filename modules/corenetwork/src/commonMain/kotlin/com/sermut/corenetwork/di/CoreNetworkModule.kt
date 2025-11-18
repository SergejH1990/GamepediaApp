package com.sermut.corenetwork.di

import com.sermut.corenetwork.apiservice.ApiService
import com.sermut.corenetwork.client.KtorClient
import org.koin.dsl.module

fun GetCoreNetworkModule() = module {
    single { ApiService(httpClient = KtorClient.GetInstance()) }
}