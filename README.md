# <u>**The cursed meme fork eddition**</u> made by a stupid lefeon

Here's my playground of NTM modding: This mod does not focus on porting stuff from 1.7.10, but rather throw in bunch of exclusive contents to make some flavor.<br>
This mod aims on making frequent updates, <i>in exchange for less reliability and stability</i>. This is a local project not focused on production.<br>
<b>Downloads can be found [here](https://github.com/abysschroma/NTM-but-uncomfortable/releases).</b>

If you've found a bug or feature requests, feel free to [open an issue](https://github.com/abysschroma/NTM-but-uncomfortable/issues/new).<br>

It is forked from Extended Edition made by [Alcater](https://github.com/Alcatergit/Hbm-s-Nuclear-Tech-GIT)<br>
which is forked from the fork made by [TheOriginalGolem](https://github.com/TheOriginalGolem/Hbm-s-Nuclear-Tech-GIT)<br>
which is forked from the port made by [Drillgon200](https://github.com/Drillgon200/Hbm-s-Nuclear-Tech-GIT)<br>
which is ported from the original mod made by [HBMTheBobcat](https://github.com/HbmMods/Hbm-s-Nuclear-Tech-GIT).

![2025-01-05_11 50 16](https://github.com/user-attachments/assets/db2d0f36-9a31-4647-9ba3-45f6beebdd5b)

# Build
- As mentioned in [abysschroma](https://github.com/abysschroma), I modified the build to make it barely functional from completely non-functional. I updated dependencies that no longer existed and fixed the decomp workspace to allow debugging the mod using `:runClient` and `:runServer`.
- The build is still very buggy and has frequent build failures. Refer to the Build QA section on how to fix the most common issues.

# Build Setup
1.  Ensure you are using Java 8 + Gradle 7.
2.  Place the Computronics (deobf dev jar) in the same folder as the project (the project's parent folder). You can download it here: [download](https://wiki.vexatos.com/wiki:computronics).
3.  Load the project.

4.  The initial load should work, and all dependencies should download.

> [!NOTE]
> If you want to use other mods during debugging for testing, please read "Build QA > Other mods cause crashes if I put them into the run/mods folder."

# Build QA
### Can I use OptiFine?
> No, OptiFine is closed source and does not work in any decomp workspace. It is notorious for causing incompatibilities and errors, especially in a development environment.

### LeafiaGls.java:19: error: cannot find symbol
> [!IMPORTANT]
> This fix is for the IntelliJ IDE. It may or may not work in other IDEs.

````java
LeafiaGls.java:19: error: cannot find symbol

public class LeafiaGls extends GlStateManager {
       ^
  symbol:   constructor GlStateManager()
  location: class GlStateManager
````

Do this:
1.  Run `gradle clean`.
2.  Run `gradle clean --refresh-dependencies`.
3.  Go to `com.leafia.transformer.LeafiaGls` and click "Download Sources." This magically fixes the error; I'm not sure why, but it seems to be some kind of environment issue. Downloading sources runs many tasks, including mappings and setting up the MCP environment.

### Other mods cause crashes if I put them into the `run/mods` folder
-   Access transformers are broken for some reason (that's why the build is only barely functional).
-   That's why I've bundled the `at.cfg` files of mods you might want to use for testing (JEI and OpenComputers).
-   If you want to test with a mod that isn't bundled, you need to extract the access transformer file from the mod's `.jar` file. This file should be in `META-INF` and will end in `.cfg`.
-   Then, manually place these files in the project's `/Assets/` directory. Edit the `build.gradle` file at the following lines and add your access transformer:
````gradle
accessTransformers(
                    file("${projectDir}/Assets/jei_at.cfg"),
                    file("${projectDir}/Assets/oc_at.cfg"))
````

### Minecraft screen is black on startup
-   Somehow, the OpenGL context gets messed up sometimes (this could be window mode related).

> If you are in windowed mode:
> Just resize the window, for example, press the maximize button.
I don't know of any fix for fullscreen mode or if this event happens in fullscreen mode.

