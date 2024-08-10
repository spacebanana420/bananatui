# BananaTUI

BananaTUI is a simple TUI library written in both Scala and Java. With it, you can make option-based TUI interfaces for your programs.

BananaTUI only depends on a terminal environment that supports ANSI, and so it works on nearly all terminal environments and emulators.

To properly use this library on Windows, you most likely have to run `windows_enableANSI()` as seen in the "extra functionality" part of the documentation.

<p align="center">
<img src="image/2.png" width="500"/>
</p>

*BananaTUI being used to power [Tanuki Launcher](https://github.com/spacebanana420/tanuki)*

# How to use (Scala)

Download the library from the releases or from the git repository (`src`) and add the library source to your project's repository.

Import the following package:
```scala
import bananatui.*
```

Scala 3 is required for this library and Java 8 or newer.

# How to use (Java)

Download the library from the releases or from the git repository (`src-java`) and add the library source to your project's repository.

Import the following package:
```scala
import bananatui.base.*;
import bananatui.userinput.*;
import bananatui.extra.*;
```

Java 8 or newer is required for this library.

# Documentation (Scala)

All the library functions are documented below.

#### [Base functions](doc/base.md)
#### [User input functions](doc/userinput.md)
#### [Extra functionality](doc/extra.md)

# Documentation (Java)

Java documentation is coming soon as this rewrite is new.
