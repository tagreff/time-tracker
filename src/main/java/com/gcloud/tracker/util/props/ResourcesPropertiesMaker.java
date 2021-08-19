package com.gcloud.tracker.util.props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class ResourcesPropertiesMaker
 *
 * Provides Properties object from property file
 * located in java resources directory.
 *
 * @author Oleksandr Storozhuk
 * @version 0.0.2
 * created on 16.08.2021
 */
public class ResourcesPropertiesMaker {
    private static ResourcesPropertiesMaker instance;
    private final Properties props;
    private final static Logger log = LoggerFactory.getLogger(ResourcesPropertiesMaker.class);

    private ResourcesPropertiesMaker(String resourceFile) {
        props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceFile)) {
            props.load(resourceStream);
        } catch (FileNotFoundException fe) {
            log.error(String.format("Can't find %s!", resourceFile), fe);
        } catch (IOException ioe) {
            log.error("IO error!", ioe);
        }
    }

    /**
     * @param resourceFile filename from java resources folder.
     * @return java.util.Properties
     */
    public static Properties getProps(String resourceFile) {
        return getInstance(resourceFile).props;
    }

    private static ResourcesPropertiesMaker getInstance(String source) {
        if (instance == null)
            instance = new ResourcesPropertiesMaker(source);
        return instance;
    }
}