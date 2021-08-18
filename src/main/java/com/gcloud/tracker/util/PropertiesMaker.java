package com.gcloud.tracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class PropertiesMaker
 *
 * Provides Properties object from property file.
 *
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 16.08.2021
 */
public class PropertiesMaker {
    private static PropertiesMaker instance;
    private final Properties props;
    private final static Logger log = LoggerFactory.getLogger(PropertiesMaker.class);

    private PropertiesMaker(String resourceFile) {
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
     * @param resourceFile filename from java resources folder
     * @return java.util.Properties
     */
    public static Properties getProps(String resourceFile) {
        return getInstance(resourceFile).props;
    }

    private static PropertiesMaker getInstance(String source) {
        if (instance == null)
            instance = new PropertiesMaker(source);
        return instance;
    }
}
