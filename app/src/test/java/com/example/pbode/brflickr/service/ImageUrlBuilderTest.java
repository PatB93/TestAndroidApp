package com.example.pbode.brflickr.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ImageUrlBuilderTest {

    private ImageUrlBuilder subject;

    @Before
    public void setup() {
        subject = new ImageUrlBuilder(1);
    }

    @Test
    public void buildNewImageRequest_farmEquals1_baseUrlBuiltForFarm1() {
        assertEquals(subject.getBaseUrlForFarm(1), "https://farm1.staticflickr.com");
    }
}