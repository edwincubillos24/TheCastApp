package com.edwinacubillos.thecastapp.di

import com.edwinacubillos.thecastapp.data.server.RetrofitBuild
import com.edwinacubillos.thecastapp.data.server.WrappedResponseHandler
import com.edwinacubillos.thecastapp.R
import com.edwinacubillos.data.repository.CatsRepository
import com.edwinacubillos.data.repository.impl.CatsRepositoryImpl
import com.edwinacubillos.data.source.RemoteCatsDataSource
import com.edwinacubillos.framework.remote.api.MLService
import com.edwinacubillos.framework.remote.mappers.RemoteCatsMapper
import com.edwinacubillos.thecastapp.TheCastApp
import com.edwinacubillos.thecastapp.data.server.source.RemoteCatsImplDataSource
import com.edwinacubillos.thecastapp.ui.list.ListViewModel
import com.edwinacubillos.usescases.cats.GetCatsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun TheCastApp.initDI() {
    try {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@initDI)
            modules(listOf(appModule, dataModule, scopesModule))
        }
    } catch (e: Exception) {
        stopKoin()
        this.initDI()
    }
}

val appModule = module {
    single(named("x-api-key")) { androidApplication().getString(R.string.api_key) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single { RetrofitBuild(baseUrl = androidApplication().getString(R.string.base_url)) }
    factory { WrappedResponseHandler() }
    factory { RemoteCatsMapper() }
    factory<RemoteCatsDataSource> {
        RemoteCatsImplDataSource(
            mlService = get(),
            remoteCatsMapper = get(),
            wrappedResponseHandler = get()
        )
    }
    single { get<RetrofitBuild>().retrofit.create(MLService::class.java) }
}

val dataModule = module {
    factory<CatsRepository> { CatsRepositoryImpl(remoteCatsDataSource = get(), get(named("x-api-key"))) }
}

val scopesModule = module {

    viewModel { ListViewModel(GetCatsUseCase(catsRepository = get())) }

}
