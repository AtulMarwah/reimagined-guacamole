/*package com.spring.boot.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequestWrapper;

public class MultipleReadHttpRequest extends HttpServletRequestWrapper {
    private ByteArrayOutputStream cachedContent;

    public MultipleReadHttpRequest(HttpServletRequest request) throws IOException {
        // Read the request body and populate the cachedContent
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
		return null;
        // Create input stream from cachedContent
        // and return it
    }

    @Override
    public BufferedReader getReader() throws IOException {
		return null;
        // Create a reader from cachedContent
        // and return it
    }
}*/