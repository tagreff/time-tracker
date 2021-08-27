package com.gcloud.tracker.action;

import com.gcloud.tracker.util.PropertiesMaker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class CreateUserActionTest {
    private static final Properties props = PropertiesMaker.getProps("test.properties");
    String URL = props.getProperty("url");

    @Ignore
    @Test
    public void doGetTest() throws IOException {
        // Given
        String name = RandomStringUtils.randomAlphabetic( 8 );
        HttpUriRequest request = new HttpGet( URL + "createUser/" + name);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Ignore
    @Test
    public void doPostTest() throws IOException {
        // Given
        String name = RandomStringUtils.randomAlphabetic( 8 );
        HttpUriRequest request = new HttpPost( URL + "createUser/" + name);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

}