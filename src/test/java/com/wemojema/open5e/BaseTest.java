package com.wemojema.open5e;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class BaseTest {

    public static String resource(String resourcePath) {
        try {
            if (!Objects.requireNonNull(resourcePath, "Resource Path cannot be null").startsWith("/"))
                resourcePath = "/" + resourcePath;
            return Files.readString(Paths.get(BaseTest.class.getResource(resourcePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
