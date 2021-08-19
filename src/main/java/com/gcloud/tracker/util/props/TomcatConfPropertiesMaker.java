package com.gcloud.tracker.util.props;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Class TomcatConfPropertiesMaker
 *
 * Provides Properties object from property file.
 * Put your properties file into /tomcat/conf/ directory!
 *
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 19.08.2021
 */
public class TomcatConfPropertiesMaker {
    private static TomcatConfPropertiesMaker instance;
    private final Properties props;
    private final static Logger log = LoggerFactory.getLogger(TomcatConfPropertiesMaker.class);

    private TomcatConfPropertiesMaker(String resourceFile) {
        props = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("catalina.base")
                .concat(File.separator).concat("conf").concat(File.separator).concat(resourceFile))) {
            props.load(fileInputStream);
        } catch (FileNotFoundException fe) {
            log.error(String.format("Can't find %s!", resourceFile), fe);
        } catch (IOException ioe) {
            log.error("IO error!", ioe);
        }
    }

    /**
     * @param resourceFile filename from /tomcat/conf/ directory.
     * @return java.util.Properties
     */
    public static Properties getProps(String resourceFile) {
        return getInstance(resourceFile).props;
    }

    private static TomcatConfPropertiesMaker getInstance(String source) {
        if (instance == null)
            instance = new TomcatConfPropertiesMaker(source);
        return instance;
    }
}

