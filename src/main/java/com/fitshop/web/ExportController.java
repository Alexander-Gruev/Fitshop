package com.fitshop.web;

import com.fitshop.model.view.OrderViewModel;
import com.fitshop.service.OrderService;
import com.fitshop.service.impl.CSVExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {

    private final static String ORDERS_FILE_NAME = "orders";

    private final CSVExporter csvExporter;
    private final OrderService orderService;

    @GetMapping("/orders/csv")
    public ResponseEntity<StreamingResponseBody> exportOrdersToCSV(HttpServletResponse response) {
        List<OrderViewModel> orders = orderService.getAllOrders();
        StreamingResponseBody responseBody = csvExporter.export(orders, OrderViewModel.class, ORDERS_FILE_NAME, response);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
