<a name="readme-top"></a>

[![Stargazers][stars-shield]][stars-url] 
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer">
    <img
      src="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/blob/master/app/src/main/res/drawable/ic_launcher.png?raw=true"
      alt="Logo"
      width="80"
      height="80"
    />
  </a>

  <h3 align="center">MVVM-Clean-Architecture-Crypto-Analyzer</h3>

  <p align="center">
    This app show list of gainers in 3 major crypto analytics sites.
    <br />
    <br />
    <a href="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer">View Demo</a>
    ·
    <a href="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/issues">Report Bug</a>
    ·
    <a href="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/issues"
      >Request Feature</a
    >
  </p>
</div>

## About The Project 

An Android application use API and parse HTML to display
popular crypto list. Built with Clean architecture and Jetpack component
(Jetpack Compose, MVVM Clean, Kotlin, HILT, ROOM DB, Retrofit2, Coroutines,
Kotlin Flow and many more..). It was made to describe the latest android
development trend.

<p dir="auto"><strong>App features:</strong></p>
<ul dir="auto">
  <li>List of popular coins from 3 major crypto analytics sites:</li>
  <ul dir="auto">
    <li>
      <strong>Dextools.io</strong> - Uses dextools API to get gainers ,losers
      and hotpairs list.
    </li>
    <li>
      <strong>Coingecko.com</strong> - Parse coingecko site to get gainers
      ,losers and New coins list.
    </li>
    <li>
      <strong>Coinmarketcap.com</strong> - Uses coinmarketcap API to get
      gainers,losers,trending,most visited coin list.
    </li>
  </ul>
  <li>Export data to excell.</li>
</ul>

## Screenshots 

<img src="https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/blob/master/Screenshot.png?raw=true" alt="drawing" style="width:200px;"/>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Architecture 

Uses concepts of MVVM Clean Architecture. 
- Your code will be even more easily testable than with plain MVVM.
- Your code will be further decoupled.
- The project will be even easier to maintain.
- Your team can add new features even more quickly.

<ul dir="auto">
  <li>
    <strong>view</strong> - It uses all the components and classes related to
    Android Framework. It gets the data from domain layer and shows on UI.
  </li>
  <li>
    <strong>viewmodel</strong> - The domain layer contains the UseCases that
    encapsulate a single and very specific task that can be performed. This task
    is part of the business logic of the application.
  </li>
  <li>
    <strong>model</strong> - Handles data interacting.
    <ul dir="auto">
      <li>
        <strong>data</strong> - The data layer implements the repository
        pattern. This layer provide a single source of truth for data.
      </li>
      <li>
        <strong>remote</strong> - Handles data interacting with the network.
      </li>
    </ul>
  </li>
</ul>

### Built With

<ul dir="auto">
  <li>
    <a href="https://kotlinlang.org/" rel="nofollow">Kotlin</a> - Kotlin is
    Google's preferred language for Android app development.
  </li>
  <li>
    <a href="https://github.com/Kotlin/kotlinx.coroutines">Kotlin Coroutines</a>
    - A coroutine is a concurrency design pattern that you can use on Android to
    simplify code that executes asynchronously
  </li>
  <li>
    <a
      href="https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/"
      rel="nofollow"
      >Flow API</a
    >
    - Flow is used to pass (send) a stream of data that can be computed
    asynchronously
  </li>
  <li>
    <a
      href="https://developer.android.com/training/dependency-injection/hilt-android"
      rel="nofollow"
      >Dagger-Hilt</a
    >
    - for dependency injection.
  </li>
  <li>
    <a
      href="https://developer.android.com/topic/libraries/architecture"
      rel="nofollow"
      >Android Architecture Components</a
    >
    <ul dir="auto">
      <li>
        <a
          href="https://developer.android.com/topic/libraries/architecture/viewmodel"
          rel="nofollow"
          >ViewModel</a
        >
        - Stores UI-related data that isn't destroyed on UI changes.
      </li>
      <li>
        <a
          href="https://developer.android.com/guide/navigation/navigation-getting-started"
          rel="nofollow"
          >Navigation</a
        >
        - Used to navigate between fragments.
      </li>
      <li>
        <a href="https://developer.android.com/jetpack/compose" rel="nofollow"
          >Compose</a
        >
        - Jetpack Compose is Android’s recommended modern toolkit for building
        native UI.
      </li>
    </ul>
  </li>
  <li>
    <a href="https://github.com/material-components/material-components-android"
      >Material-Components</a
    >
    - Material design components like ripple animation, cardView.
  </li>
  <li>
    <a href="https://github.com/square/retrofit">Retrofit</a> - Used for REST
    api communication.
  </li>
  <li>
    <a href="http://square.github.io/okhttp/" rel="nofollow">OkHttp</a> - HTTP
    client that's efficient by default: HTTP/2 support allows all requests to
    the same host to share a socket
  </li>
  <li>
    <a href="https://github.com/square/moshi">Gson</a> - Used to convert Java
    Objects into their JSON representation and vice versa.
  </li>
  <li>
    <a href="https://github.com/chrisbanes/accompanist/blob/main/coil/README.md"
      >Coil</a
    >
    - An image loading library for Android backed by Kotlin Coroutines
  </li>
  <li>
    <a href="https://github.com/chrisbanes/accompanist/blob/main/coil/README.md"
      >Jsoup</a
    >
    - Used for parse HTML pages
  </li>
  <li>
    <a href="https://github.com/chrisbanes/accompanist/blob/main/coil/README.md"
      >Apache POI</a
    >
    - Export to excel files
  </li>
</ul>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Installation 

1. Clone this repository
2. Build the application

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License 

Distributed under the MIT License. See `LICENSE` for more
information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact 

Sepehr Nouzarian - sepehr.android@yahoo.com

Project Link: [https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer](https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]:https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]:https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/graphs/contributors 
[stars-shield]:https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/stargazers
[issues-shield]:https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/issues
[license-shield]:https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]:https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/blob/master/LICENSE.txt
[linkedin-shield]:https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/sepehr-nozaryian-4856ba64/
[product-screenshot]: https://github.com/sepehrsun/MVVM-Clean-Architecture-Crypto-Analyzer/blob/master/Screenshot.png?raw=true
