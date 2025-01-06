# CKEditor Override Module

This module provides an override for CKEditor in Liferay. Follow the steps below to create a deployable version.

## Steps to Deploy

1. **Create a New Workspace for the Required Liferay Version**  
   - Open a command line and run:
     ```bash
     blade init
     ```
   - Enter `more` to see all the available versions.
   - Choose the number corresponding to the Liferay version you need. e.g. '1'
   - Press `Enter`.

2. **Navigate to the `modules` Folder**  
   - After initializing the workspace, move into the `modules` folder.

3. **Clone This Repository**  
   - Clone this repository into the `modules` folder.

4. **Edit the `SampleJournalEditorConfigContributor.java` File**  
   - Locate the file:  
     `modules/sample-journal-editor-config-contributor/src/main/java/sample/journal/editor/config/contributor/SampleJournalEditorConfigContributor.java`
   - Choose the configuration key appropriate for your Liferay version and comment out the other:
     ```java
     "editor.config.key=translateEditor" // Use this for version 7.3
     "editor.config.key=defaultTranslateEditor" // Use this for version 7.4+
     ```
   - Save your changes.

5. **Build the Module**  
   - From the `modules` folder, run the following command:
   ```bash
      blade gw clean build
   ```

6. **Find the Deployable Module**
   - After the build completes, the deployable .jar file can be found in:
   ```bash
   modules/sample-journal-editor-config-contributor/build/libs
   ```

7. **Deploy**
   - Copy sample.creditor.config.contributor-1.0.0.jar into the $LIFERAY_HOME/deploy folder
