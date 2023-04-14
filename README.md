# FreedomNetworkSuite

This is a proof of concept for a new suite of non-plugin driven modules supported by a common library.
This is designed to encompass the ideologies of a Freedom server, while maintaining full customization through modules.
The modules are non-plugin driven, meaning that in no way are any modules an extension of the Bukkit plugin interface.
This is because the commons library is designed to be a JavaPlugin extension which utilizes each loaded module as a resource.
Any modules are by default an extension of the commons library. 

Modules are defined by a module.yml in the resources folder, which contains the path to the main class file, 
and a list of dependencies if it requires any other modules to run. 

This proof-of-concept also uses the following libraries:
 - Aikar's Annotation Command Framework for command handling
 - ProjectReactor for subscribing to SQL database streams
 - Google GSON for Json interpretation
 - Jetbrains Annotations for additional compiler annotations
 - Lombok for boilerplate generation
 - Apache Commons for various utilities
 - SLF4J for logging
 - Paper for the server implementation
 - Kyori Adventure for chat formatting
 - Reflections API for reflections
