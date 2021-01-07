package com.ecommerce.springbootecommerce.controller;

import com.ecommerce.springbootecommerce.constants.ResponseCode;
import com.ecommerce.springbootecommerce.constants.WebConstants;
import com.ecommerce.springbootecommerce.exception.OrderCustomException;
import com.ecommerce.springbootecommerce.exception.ProductCustomException;
import com.ecommerce.springbootecommerce.model.PlaceOrder;
import com.ecommerce.springbootecommerce.model.Product;
import com.ecommerce.springbootecommerce.repository.CartRepository;
import com.ecommerce.springbootecommerce.repository.OrderRepository;
import com.ecommerce.springbootecommerce.repository.ProductRepository;
import com.ecommerce.springbootecommerce.response.Order;
import com.ecommerce.springbootecommerce.response.ProductResponse;
import com.ecommerce.springbootecommerce.response.ServerResponse;
import com.ecommerce.springbootecommerce.response.ViewOrderResponse;
import com.ecommerce.springbootecommerce.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private OrderRepository ordRepo;

    @Autowired
    private CartRepository cartRepo;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(
            @RequestParam(name = WebConstants.PROD_FILE, required = false) MultipartFile prodImage,
            @RequestParam(name = WebConstants.PROD_DESC) String description,
            @RequestParam(name = WebConstants.PROD_PRICE) String price,
            @RequestParam(name = WebConstants.PROD_NAME) String productname,
            @RequestParam(name = WebConstants.PROD_QUANITY) String quantity) throws IOException {
        ProductResponse resp = new ProductResponse();
        if (Validator.isStringEmpty(productname) || Validator.isStringEmpty(description)
                || Validator.isStringEmpty(price) || Validator.isStringEmpty(quantity)) {
            resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
            resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
            return new ResponseEntity<ProductResponse>(resp, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                Product prod = new Product();
                prod.setDescription(description);
                prod.setPrice(Double.parseDouble(price));
                prod.setName(productname);
                prod.setQuantity(Integer.parseInt(quantity));
                if (prodImage != null) {
                    prod.setProductimage(prodImage.getBytes());
                }
                prodRepo.save(prod);

                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.ADD_SUCCESS_MESSAGE);
                resp.setOblist(prodRepo.findAll());
            } catch (Exception e) {
                throw new ProductCustomException("Unable to save product details, please try again");
            }
        }
        return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
    }

    @PutMapping("/updateProducts")
    public ResponseEntity<ServerResponse> updateProducts(
            @RequestParam(name = WebConstants.PROD_FILE, required = false) MultipartFile prodImage,
            @RequestParam(name = WebConstants.PROD_DESC) String description,
            @RequestParam(name = WebConstants.PROD_PRICE) String price,
            @RequestParam(name = WebConstants.PROD_NAME) String productname,
            @RequestParam(name = WebConstants.PROD_QUANITY) String quantity,
            @RequestParam(name = WebConstants.PROD_ID) String productid) throws IOException {
        ServerResponse resp = new ServerResponse();
        if (Validator.isStringEmpty(productname) || Validator.isStringEmpty(description)
                || Validator.isStringEmpty(price) || Validator.isStringEmpty(quantity)) {
            resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
            resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
            return new ResponseEntity<ServerResponse>(resp, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                if (prodImage != null) {
                    Product prod = new Product(Integer.parseInt(productid), description, productname,
                            Double.parseDouble(price), Integer.parseInt(quantity), prodImage.getBytes());
                    prodRepo.save(prod);
                } else {
                    Product prodOrg = prodRepo.findById(Integer.parseInt(productid));
                    Product prod = new Product(Integer.parseInt(productid), description, productname,
                            Double.parseDouble(price), Integer.parseInt(quantity), prodOrg.getProductimage());
                    prodRepo.save(prod);
                }
                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.UPD_SUCCESS_MESSAGE);
            } catch (Exception e) {
                throw new ProductCustomException("Unable to update product details, please try again");
            }
        }
        return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/delProduct")
    public ResponseEntity<ProductResponse> delProduct(@RequestParam(name = WebConstants.PROD_ID) String productid)
            throws IOException {
        ProductResponse resp = new ProductResponse();
        if (Validator.isStringEmpty(productid)) {
            resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
            resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
            return new ResponseEntity<ProductResponse>(resp, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                prodRepo.deleteById(Integer.parseInt(productid));
                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
            } catch (Exception e) {
                throw new ProductCustomException("Unable to delete product details, please try again");
            }
        }
        return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
    }

    @GetMapping("/viewOrders")
    public ResponseEntity<ViewOrderResponse> viewOrders() throws IOException {

        ViewOrderResponse resp = new ViewOrderResponse();
        try {
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.VIEW_SUCCESS_MESSAGE);
            List<Order> orderList = new ArrayList<>();
            List<PlaceOrder> poList = ordRepo.findAll();
            poList.forEach((po) -> {
                Order ord = new Order();
                ord.setOrderBy(po.getEmail());
                ord.setOrderId(po.getOrderId());
                ord.setOrderStatus(po.getOrder_status());
                ord.setProducts(cartRepo.findAllByOrderId(po.getOrderId()));
                orderList.add(ord);
            });
            resp.setOrderlist(orderList);
        } catch (Exception e) {
            throw new OrderCustomException("Unable to retrieve orderss, please try again");
        }

        return new ResponseEntity<ViewOrderResponse>(resp, HttpStatus.OK);
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<ServerResponse> updateOrders(@RequestParam(name = WebConstants.ORD_ID) String orderId,
                                                       @RequestParam(name = WebConstants.ORD_STATUS) String orderStatus) throws IOException {

        ServerResponse resp = new ServerResponse();
        if (Validator.isStringEmpty(orderId) || Validator.isStringEmpty(orderStatus)) {
            resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
            resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
            return new ResponseEntity<ServerResponse>(resp, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                PlaceOrder pc = ordRepo.findByOrderId(Integer.parseInt(orderId));
                pc.setOrder_status(orderStatus);
                pc.setOrder_date(new Date(System.currentTimeMillis()));
                ordRepo.save(pc);
                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.UPD_ORD_SUCCESS_MESSAGE);
            } catch (Exception e) {
                throw new OrderCustomException("Unable to retrieve orderss, please try again");
            }
        }
        return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
    }
}
