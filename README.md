# Android Startup Sync IDE Plugin

This IDE plugin allows disabling the Android Sync on project load. This is mainly in response to the Google issue [b/299322773](https://issuetracker.google.com/issues/299322773).

## Usage
Once this plugin is installed, it can be found as a setting under the IntelliJ setting panel (`Build, Execution, Deployment -> Build Tools -> Android Startup Sync`).

![img](./img/Frybits-sync-setting.png)

This setting prevents Android projects from starting a Gradle Sync when the project has loaded on IDE start.

## Build
To build and use this plugin locally, you can run the following gradle command:
```
./gradlew runIde
```

With this command, an instance of Android Studio should start, with the setting disabled.

## Status
This project is currently being published in the Alpha repository of Jetbrains Marketplace (https://plugins.jetbrains.com/plugins/alpha/list).

## License
```
   Copyright 2025 Pablo Baxter

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
