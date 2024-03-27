# Banana TUI

Extra TUI functionality.

```scala
def printProgress(percentage: Byte, str: String = "", first: Boolean = true, usebar: Boolean = true)
```
Prints a progress status that can be updated without clearing the whole screen.

```percentage``` is your progress percentage from 0 to 100.

```str``` is an optional string you can add above the progress status.

```first``` should be set to true if this is your first progress print. For all other consecutive prints, it should be ```false``` instead. The difference is that the progress only updates itself if it's set to false, otherwise it just prints the new progress below the old one. Note that ```str``` should remain always the same for your progress calls.

```usebar``` toggles the use of a progress bar. If you disable it, the progress only shows the percentage.