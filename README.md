[acf]: https://github.com/aikar/commands "Annotation Command Framework"
[react]: https://github.com/reactor/reactor-core "ProjectReactor"
[gson]: https://github.com/google/gson "Google GSON"
[annot]: https://github.com/JetBrains/JetBrains.Annotations "JetBrains Annotations"
[lombok]: https://github.com/projectlombok/lombok "Lombok"
[apache]: https://github.com/apache/commons-lang "Apache Commons"
[log]: https://github.com/qos-ch/slf4j "SLF4J"
[paper]: https://github.com/PaperMC/Paper "Paper"
[adv]: https://github.com/KyoriPowered/adventure "Kyori Adventure"
[reflex]: https://github.com/ronmamo/reflections "Reflections API"

# FreedomNetworkSuite

This is a proof of concept for a new suite of non-plugin driven modules supported by a common library.
This is designed to encompass the ideologies of a Freedom server, while maintaining full customization through modules.
The modules are non-plugin driven, meaning that in no way are any modules an extension of the Bukkit plugin interface.
This is because the commons library is designed to be a JavaPlugin extension which utilizes each loaded module as a resource.
Any modules are by default an extension of the commons library. 

Modules are defined by a module.yml in the resources folder, which contains the path to the main class file, 
and a list of dependencies if it requires any other modules to run. 

This proof-of-concept also uses the following libraries:
 - Aikar's [acf] for command handling
 - [react] for subscribing to SQL database streams
 - [gson] for Json interpretation
 - [annot] for additional compiler annotations
 - [lombok] for boilerplate generation
 - [apache] for various utilities
 - [log] for logging
 - [paper] for the server implementation
 - [adv] for chat formatting
 - [reflex] for reflections
