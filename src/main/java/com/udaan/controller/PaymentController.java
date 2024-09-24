package com.udaan.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final RazorpayClient razorpayClient;

    @Autowired
    public PaymentController(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/createOrder")
    public Map<String, Object> createOrder(@RequestParam double amount) {
        Map<String, Object> response = new HashMap<>();
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int) (amount) *100); // amount in the smallest currency unit (paise)
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");

            Order order = razorpayClient.orders.create(orderRequest);
            response.put("orderId", order.get("id"));
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));
            response.put("status", order.get("status"));
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
