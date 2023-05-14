[Aikar's ACF]: https://github.com/aikar/commands "Annotation Command Framework"
[Google GSON]: https://github.com/google/gson "Google GSON"
[Jetbrains Annotations]: https://github.com/JetBrains/JetBrains.Annotations "JetBrains Annotations"
[Lombok]: https://github.com/projectlombok/lombok "Lombok"
[Apache Commons]: https://github.com/apache/commons-lang "Apache Commons"
[SLF4J]: https://github.com/qos-ch/slf4j "SLF4J"
[Paper]: https://github.com/PaperMC/Paper "Paper"
[Kyori Adventure]: https://github.com/KyoriPowered/adventure "Kyori Adventure"
[Reflections API]: https://github.com/ronmamo/reflections "Reflections API"

#
![Header Image](https://media.discordapp.net/attachments/436759124953399296/1107175759941996544/20230514_002037_0000.png)

###
![GitHub labels](https://img.shields.io/github/labels/SimplexDevelopment/FreedomNetworkSuite/Help%20Wanted?style=for-the-badge)
![GitHub contributors](https://img.shields.io/github/contributors/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub issues](https://img.shields.io/github/issues/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub pull requests](https://img.shields.io/github/issues-pr/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)

###
![GitHub](https://img.shields.io/github/license/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub top language](https://img.shields.io/github/languages/top/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/SimplexDevelopment/FreedomNetworkSuite?include_prereleases&style=for-the-badge)
![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/SimplexDevelopment/FreedomNetworkSuite?style=for-the-badge)
![TFM Used](https://img.shields.io/badge/TFM%20Code%20Used-0%25-red?style=for-the-badge)

# FreedomNetworkSuite

This is a proof of concept for a new suite of non-plugin driven modules supported by a common library.
This is designed to encompass the ideologies of a Freedom server, while maintaining full customization through modules.
The modules are non-plugin driven, meaning that in no way are any modules an extension of the Bukkit plugin interface.
This is because the commons library is designed to be a JavaPlugin extension which utilizes each loaded module as a resource.
Any modules are by default an extension of the commons library. 

Modules are defined by a module.yml in the resources folder, which contains the path to the main class file, 
and a list of dependencies if it requires any other modules to run. 

This proof-of-concept also uses the following libraries:
 - [Aikar's ACF] for command handling
 - [Google GSON] for Json interpretation
 - [Jetbrains Annotations] for additional compiler annotations
 - [Lombok] for boilerplate generation
 - [Apache Commons] for various utilities
 - [SLF4J] for logging
 - [Paper] for the server implementation
 - [Kyori Adventure] for chat formatting
 - [Reflections API] for reflections
