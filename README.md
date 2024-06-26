# Github-Repository-Browser-UM
This app allows find repositories for certain user and show detailed information of choosen repository

## Tech stack

- Kotlin
- Kotlin Coroutines/flow

- AndroidX Jetpack Compose (Toolbar, Scaffold, LazyColumn, Row, Column, Card, NavHost)
- AndroidX Navigation
- AndroidX Paging 3 lib

- Apollo Kotlin (GraphQL)
- Github API v4
- Hilt
- MVVM, MVI, Clean Architecture

- Android Web links

## Github API Token

To get this app working, you need to get an Github API Token
and include it in the `local.properties` file as follows:

```
GIT_TOKEN = insert_your_api_key_here
```

## Description
Screens: 

1. List of user owned repositories by the specified user login, which was mentioned in input field before.

2. Detailed information of choosen repository. Detailed information include: name, owner login, description, number of commits, number of issues.

Also this app can be launched via web link, for example after clicking on https://github.com/qqvladik/Github-Repository-Browser-UM it will open detailed screen with information for Github Repository Browser UM.
The same way will work for user repositories after clicking on web link for user, f.e. https://github.com/qqvladik.

p.s. web links don't work for android 12+ by default. For using this you need to choose link from list in "Open by default" in settings of this android app.

Pagination Github API is cursor-based.

Uladzislau Mankevich
git repository - https://github.com/qqvladik/Github-Repository-Browser-UM.git
