# LibGDX utilities

This repository contains most of my libraries aimed at improving LibGDX framework. While they used to be kept in multiple separate repositories, this setup is much easier to maintain for the contributor(s) and, well, makes it harder to overlook some of my less popular libraries.

## Projects

### gdx-kiwi
[Kiwi](https://github.com/czyzby/gdx-lml/tree/master/kiwi) is a Guava-inspired set of utilities for pretty much any LibGDX-based application. It makes it easier to use LibGDX collections, assets and its API in general.

### gdx-lml
[LML](https://github.com/czyzby/gdx-lml/tree/master/lml) (*LibGDX Marker Language*) allows to parse HTML-like templates with FreeMarker-inspired macros into Scene2D actors. Since making your UI in Java can become unreadable and tedious thanks to this language's verbosity, LML can be a useful alternative. Especially since LML templates can be modified or reloaded without having to recompile the whole application. Comes with extra support for managing preferences, assets and internationalization.

#### gdx-lml-vis
[VisUI](https://github.com/kotcrab/VisEditor/wiki/VisUI) is a superb library, extending Scene2D with additional widgets and a modern skin. [LML Vis](https://github.com/czyzby/gdx-lml/tree/master/lml-vis) allows to parse LML templates into VisUI widgets, instead of standard Scene2D ones and extends the syntax with ways to construct the new actors.

### gdx-autumn
[Autumn](https://github.com/czyzby/gdx-lml/tree/master/autumn) is a dependency injection mechanism with component scanning. Using a set of annotations and class scanners, it allows you to build your application without singletons, global variables or even direct calls to constructors.

#### gdx-autumn-android
[Autumn Android](https://github.com/czyzby/gdx-lml/tree/master/autumn/natives/android) provides class scanner for Android applications.

#### gdx-autumn-fcs
[Autumn FCS](https://github.com/czyzby/gdx-lml/tree/master/autumn/natives/fcs) provides class scanner for desktop applications using lightweight [`fast-classpath-scanner`](https://github.com/lukehutch/fast-classpath-scanner).

#### gdx-autumn-gwt
[Autumn GWT](https://github.com/czyzby/gdx-lml/tree/master/autumn/natives/gwt) provides class scanner for GWT applications.

### gdx-autumn-mvc
[Autumn MVC](https://github.com/czyzby/gdx-lml/tree/master/mvc) is a model-view-controller framework on top of LibGDX. It uses **Autumn** to manage components and **LML** as view templates. Makes it easier to maintain assets, internationalization, preferences, music, screen transitions, and so on. While other libraries are rather general-purpose, this one forces its structure upon your application - but should still be worth it, considering the amount of things it handles for you.

### gdx-websocket
[LibGDX web sockets library](https://github.com/czyzby/gdx-lml/tree/master/websocket) aims to extend the default `Net` implementations with cross-platform client-side web sockets. Rather than being a huge framework with serialization and server-side libraries, this set of libraries offers a simple and somewhat low level, yet pretty powerful client networking API.

#### gdx-websocket-common
[Common web sockets library](https://github.com/czyzby/gdx-lml/tree/master/websocket/natives/common) contains web socket natives for desktop and Android applications using [nv-websocket-client](https://github.com/TakahikoKawasaki/nv-websocket-client) library.

#### gdx-websocket-gwt
[GWT web sockets library](https://github.com/czyzby/gdx-lml/tree/master/websocket/natives/gwt) contains web socket natives for GWT applications.

## Dependencies

All libraries follow the same schema:
```
    compile "com.github.czyzby:lib-name:$libVersion.$gdxVersion"
```
`lib-name` is the name of the library (one of the ones listed above). `libVersion` follows `MAJOR.MINOR` schema and is the actual version of the library. `gdxVersion` is the version of the LibGDX library used to build the archive. For example, this is a valid LML dependency (although it might be out of date by now!): `'com.github.czyzby:gdx-lml:1.5.1.9.2'`. To find out the current version ID and GWT definition, check out the specific library's `README` file or [Maven Central](http://search.maven.org/#search|ga|1|g%3A%22com.github.czyzby%22).

## Working with the sources

Clone this repository. The whole setup is Gradle-based, with very similar structure to default LibGDX projects generated with `gdx-setup`. Note that Gradle wrapper is not included in the root project, so you should have Gradle installed locally.

The project requires some additional "secret" properties, used for archives signing and logging to Maven Central. While most likely you will not need these functionalities, Gradle still forces you to provide these properties. So, make sure to include a `gradle.properties` file in root folder or - even better - in your Gradle home folder:
```
signing.keyId= 
signing.password= 
signing.secretKeyRingFile= 

ossrhUsername= 
ossrhPassword= 
```

Before pulling any requests, make sure your code is formatted with `eclipse-formatter.xml` (or its equivalent for other IDE). Note that this is *not* the official LibGDX code formatter, as I'm not really a huge fan of its setup.

### Useful Gradle tasks
- `gradle eclipse` - generates Eclipse project structure.
- `gradle idea` - generates IntelliJ project structure.
- `gradle build install` - builds the libraries' archives and pushes them to Maven Local.
- `gradle installAll` - same as the previous one, but the tasks are always invoked in the correct order. Use when changing libraries' versions to avoid missing artifacts errors.
- `gradle uploadArchives` - pushes the archives to Maven Central. Requires proper `gradle.properties` with signing and logging data.
- `gradle clean` - removes built archives.

To run a task on a specific library, proceed task name with the project ID. For example, `gradle kiwi:build` will build archives of Kiwi library.
