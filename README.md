# SmartOtter Project Template

This project is adapted from the Duke project and given a unique personality: a *Smart Otter*. Instructions on how to use it are provided below.

## Setting Up in IntelliJ

**Prerequisites:** JDK 17 and the latest version of IntelliJ.

1. Open IntelliJ.

   * If you are not on the welcome screen, click **File → Close Project** to close any existing project first.
2. Open the project:

   1. Click **Open**.
   2. Select the project directory and click **OK**.
   3. Accept all default prompts.
3. Configure the project to use **JDK 17** (not other versions) as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).

   * In the same dialog, set the **Project language level** field to the **SDK default** option.
4. Locate the file:

   ```
   src/main/java/com/oadultradeepfield/smartotter/SmartOtter.java
   ```

   Right-click it and choose **Run 'SmartOtter.main()'**.

   * If the editor shows compile errors, try restarting IntelliJ.
   * If everything is set up correctly, you should see output like this:

   ```
   ____________________________________________________________
   Hello! I'm SmartOtter 🦦📚
   I can help you with anything,
   as long as you give me fish 😋
   ____________________________________________________________
   ```

**Warning:** Keep the `src/main/java` folder as the root for Java files (do not rename these folders or move Java files outside this path), as some tools (e.g., Gradle) expect this standard structure.
