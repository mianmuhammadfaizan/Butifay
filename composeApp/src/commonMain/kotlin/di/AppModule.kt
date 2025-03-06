package di

import data.MyRepository
import domain.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screen.info.DetailViewModel
import screen.login_signup.LoginSignUpViewModel
import screen.splash.HomeViewModel

val appModule = module {
    single<Repository> { MyRepository() }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { LoginSignUpViewModel() }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}