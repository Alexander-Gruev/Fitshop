package com.fitshop.service;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface Exporter {

    StreamingResponseBody export(List<?> data, Class<?> targetType, String fileName, HttpServletResponse response);

}
