[Google GSON]: https://github.com/google/gson "Google GSON"

[Jetbrains Annotations]: https://github.com/JetBrains/JetBrains.Annotations "JetBrains Annotations"

[Lombok]: https://github.com/projectlombok/lombok "Lombok"

[Apache Commons]: https://github.com/apache/commons-lang "Apache Commons"

[SLF4J]: https://github.com/qos-ch/slf4j "SLF4J"

[Paper]: https://github.com/PaperMC/Paper "Paper"

[Kyori Adventure]: https://github.com/KyoriPowered/adventure "Kyori Adventure"

[ClassGraph]: https://github.com/classgraph/classgraph "ClassGraph"

[TotalFreedomMod]: https://github.com/AtlasMediaGroup/TotalFreedomMod "TotalFreedomMod"

#####

<p align="center">
  <img src="https://simplexdev.app/images/fns-content/fnslogo-icon.png" width=175 height=175 alt="FNS Logo">
</p>

###

[<img src="https://img.shields.io/static/v1?label=%20&message=Help%20Wanted&color=red&style=for-the-badge">](https://discord.gg/4PdtmrVNRx)
![GitHub contributors](https://img.shields.io/github/contributors/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
[<img src="https://img.shields.io/github/issues/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge">](https://github.com/SimplexDevelopment/FreedomNetworkSuite/issues)
[<img src="https://img.shields.io/github/issues-pr/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge">](https://github.com/SimplexDevelopment/FreedomNetworkSuite/pulls)
![GitHub last commit](https://img.shields.io/github/last-commit/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![Codacy grade](https://img.shields.io/codacy/grade/176b8003312c4602afb9be7706aef146?style=for-the-badge)

###

[<img src="https://img.shields.io/static/v1?label=Roadmap&message=Google%20Docs&color=4285F4&style=for-the-badge&logo=googledrive">](https://docs.google.com/document/d/197fwNo076RsCiPW6e6QWaGEzTGnDcRuf5FBA6lNeiPE)
[<img src="https://img.shields.io/github/license/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge">](https://github.com/SimplexDevelopment/FreedomNetworkSuite/blob/kitchen-sink/LICENSE.md)
![GitHub top language](https://img.shields.io/github/languages/top/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge&logo=github)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/SimplexDevelopment/FreedomNetworkSuite?include_prereleases&style=for-the-badge&logo=github)
![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![TFM Used](https://img.shields.io/static/v1?label=TFM%20Code%20Used&message=0%25&color=red&style=for-the-badge&logo=tensorflow)

#

<p align="center">
  <img src="https://simplexdev.app/images/fns-content/fnslogo.png" alt="FNS Banner">
</p>

#

This project is a collection of plugins supported by a common library designed for creative-based freedom servers (servers which grant generous amounts of permission nodes to all players.)
This is designed to encompass the ideologies of a Freedom server, while maintaining full customization through modules.
Most modules are designed to be either optional or interchangable, except Patchwork (API) and Datura (Data Manager). These two are required to run any of the other modules.
This is NOT a ground up rewrite of [TotalFreedomMod]. This is a completely new project designed to be entirely ambiguous.
<br>
<br>
Honorable mention:
<br>
[<img src="https://img.shields.io/static/v1?label=Plex&message=A%20New%20Freedom%20Plugin&color=4285F4&style=flat-square&logo=plex)">](https://github.com/plexusorg/Plex)

This plugin suite also uses the following libraries:

- [Google GSON] for Json interpretation
- [Jetbrains Annotations] for additional compiler annotations
- [Lombok] for boilerplate generation
- [Apache Commons] for various utilities
- [SLF4J] for logging
- [Paper] for the server implementation
- [Kyori Adventure] for chat formatting
- [ClassGraph] for runtime class searching functionality

# Developers

[<img src="https://img.shields.io/static/v1?label=Team%20Lead&message=Patches&color=blueviolet&style=for-the-badge&logo=intellijidea">](https://github.com/Paldiu)
<br />
[<img src="https://img.shields.io/static/v1?label=Project%20Advisor&message=Video&color=blueviolet&style=for-the-badge&logo=intellijidea">](https://github.com/VideoGameSmash12)
<br />
[<img src="https://img.shields.io/static/v1?label=Developer&message=Allink&color=blueviolet&style=for-the-badge&logo=intellijidea">](https://github.com/allinkdev)
<br />
[<img src="https://img.shields.io/static/v1?label=Developer&message=EnZaXD&color=blueviolet&style=for-the-badge&logo=intellijidea">](https://github.com/FlorianMichael)
<br />
[<img src="https://img.shields.io/static/v1?label=Developer&message=Eva&color=blueviolet&style=for-the-badge&logo=intellijidea">](https://github.com/evallll)

# To Do List

Patchwork (API):

- [x] Logging System
- [x] SQL API
- [x] Economy API
- [x] Command API
- [x] Particle API
- [x] User API
- [x] Service API
- [x] Task API
- [x] Permissions API
- [x] Configuration API *Done...? Check with @allinkdev*
- [ ] Event API *(In Progress...)*

Datura (Data Manager):

- [x] Permission Handling
- [x] Permission Registration & Assignment
- [ ] SQL Data Handling *(In Progress...)*
- [ ] Configuration Implementations
- [ ] User Data Implementations *(In Progress...)*
- [x] Punishment Systems (e.x. Locker, Halter, Cager)

Fossil (Entertainment):

- [x] Economy Implementation
- [ ] Particle Implementation / Trails *(In Progress...)*
- [ ] Command Implementations *(In Progress...)*
- [ ] Implement a shop for the economy *(In Progress...)*
- [ ] Chat reaction / game system
- [ ] Jumppads *(In Progress...)*

Corvo (Scheduling and Listening Service):

- [ ] Service Implementation
- [ ] Service Handling
- [ ] Task Implementation
- [ ] Task Management
- [ ] Event (Project) Implementations
- [ ] Listener (Bukkit) Implementations

