package com.isekario.warfare4x.util.assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.isekario.warfare4x.util.Util.getAssetsDir;
import static com.isekario.warfare4x.util.Util.getAssetsLevelsDir;

public final class AssetManager
{
    private final String assets = getAssetsDir();
    private final String levels = getAssetsLevelsDir().substring(assets.length());

    /**
     * Saves the string content to a file in the corresponding asset directory
     * @param content - String data to save in file
     * @param assetDirectory - directory path to save file
     * @param fileName - File name to save
     * @throws URISyntaxException - Wrong URI
     * @throws IOException - IO issues
     */
    public void saveStringToAsset(StringBuilder content, String assetDirectory, String fileName) throws URISyntaxException, IOException {
        URL u = this.getClass().getResource(assetDirectory + "/" + fileName);
        File file;
        boolean fileCreated = true;

        if(u != null) {
            file = new File(u.toURI());
        }
        else {
            URL path = this.getClass().getResource(assetDirectory);
            assert path != null;
            File parent = new File(path.toURI());
            file = new File(parent, fileName);
            fileCreated = file.createNewFile();
        }

        if(fileCreated) {
            FileWriter writer = new FileWriter(file);
            writer.append(content, 0, content.length());
            writer.close();
        } else {
            throw new FileNotFoundException("The file failed to create");
        }
    }

    /**
     * Checks the assets directories and adds the missing ones
     * Used for self generating files
     * @throws URISyntaxException - Wrong URI
     * @throws IOException - IO issues
     */
    public void assetsDirCheck() throws URISyntaxException, IOException {
        dirCheck("/", assets);
        dirCheck(assets, levels);
    }

    /**
     * Checks if asset folder exists
     * Creates assets folder if non-existent
     * @throws URISyntaxException - Wrong URI
     * @throws IOException - IO issues
     */
    private void dirCheck(String pathToDir, String dirToCheck) throws URISyntaxException, IOException {
        URL u = this.getClass().getResource(pathToDir + dirToCheck);

        if(u == null)
        {
            u = this.getClass().getResource(pathToDir);
            assert u != null;
            Path source = Paths.get(u.toURI());
            Path newFolder = Paths.get(source.toAbsolutePath() + dirToCheck + "/");
            Files.createDirectories(newFolder);
        }
    }
}
