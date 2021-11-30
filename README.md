
# Sky Sports Compose News App

This is a basic android application that displays a static list of sky news articles from a locally stored asset file. 


### Setup the project in Android Studio
This project requires the latest stable version of Android Studio (Arctic Fox) to run. You can get the latest version of Arctic Fox from https://developer.android.com/studio. You will also need to install git on your computer. You can download and install git from https://git-scm.com/downloads. 

After installing Android Studio and git, run Android studio and follow the steps below to get the project started:

 1. On the "Welcome to Android Studio" Screen, click on the "Get from VCS" button.
 2. Paste https://github.com/DhanashreeBhaskar09/SkySportsCompose.git into the `URL` section and click on the `Clone` button. 
 3. After cloning the project, wait for android studio to download all the project dependencies and get the workspace ready for further development. 
 
Please see https://developer.android.com/codelabs/kotlin-android-training-install-studio#0 for more information on setting up Android Studio for the first time

### Project structure
The project is structured into a `UI` and a `data` layer and follows the MVVM architectural pattern. Both layers are written with the Kotlin programming language, which has been  Google's official programming language for the Android platform since 2017.  
More on MVVM and recommended android app architectures [here](https://developer.android.com/jetpack/guide) 

The `data` layer is responsible for fetching and preparing news articles from a data source (a local asset file in this case). The `UI` layer on the other hand is responsible for rendering the news articles loaded by the `data` layer onto the screen. 

The UI layer is written completely in Jetpack compose. Jetpack compose is a new declarative way of writing user interface components for Android. It is very similar to React and Flutter. 
More on Jetpack compose [here](https://developer.android.com/jetpack/compose) 

The data layer is also written using Coroutines, which is a new framework of libraries for writing asynchronous code in a "synchronous" fashion in Kotlin, eliminating the need for the classic callbacks in most cases. 
More on Kotlin Coroutines from [here](https://kotlinlang.org/docs/coroutines-overview.html) 
