package com.ecommerce.springbootecommerce.controller;

import com.ecommerce.springbootecommerce.constants.ResponseCode;
import com.ecommerce.springbootecommerce.constants.WebConstants;
import com.ecommerce.springbootecommerce.exception.*;
import com.ecommerce.springbootecommerce.model.*;
import com.ecommerce.springbootecommerce.repository.*;
import com.ecommerce.springbootecommerce.response.*;
import com.ecommerce.springbootecommerce.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AddressRepository addrRepo;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository ordRepo;

    @PostMapping("/addAddress")
    public ResponseEntity<UserResponse> addAddress(@RequestBody Address address, Authentication auth) {
        UserResponse resp = new UserResponse();
        if (Validator.isAddressEmpty(address)) {
            resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
            resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
            return new ResponseEntity<UserResponse>(resp, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                User user = userRepo.findByUsername(auth.getName())
                        .orElseThrow(() -> new UsernameNotFoundException(auth.getName()));
                Address userAddress = addrRepo.findByUser(user);
                if (userAddress != null) {
                    userAddress.setAddress(address.getAddress());
                    userAddress.setCity(address.getCity());
                    userAddress.setCountry(address.getCountry());
                    userAddress.setPhonenumber(address.getPhonenumber());
                    userAddress.setState(address.getState());
                    userAddress.setZipcode(address.getZipcode());
                    addrRepo.save(userAddress);
                } else {
                    user.setAddress(address);
                    address.setUser(user);
                    addrRepo.save(address);
                }
                resp.setStatus(ResponseCode.SUCCESS_CODE);
                resp.setMessage(ResponseCode.CUST_ADR_ADD);
            } catch (Exception e) {
                throw new AddressCustomException("Unable to add address, please try again");
            }
        }
        return new ResponseEntity<UserResponse>(resp, HttpStatus.OK);
    }

    @GetMapping("/getAddress")
    public ResponseEntity<Response> getAddress(Authentication auth) {
        Response resp = new Response();
        try {
            User user = userRepo.findByUsername(auth.getName()).orElseThrow(
                    () -> new UserCustomException("User with username " + auth.getName() + " doesn't exists"));
            Address adr = addrRepo.findByUser(user);



            HashMap<String, String> map = new HashMap<>();
            map.put(WebConstants.ADR_NAME, adr.getAddress());
            map.put(WebConstants.ADR_CITY, adr.getCity());
            map.put(WebConstants.ADR_STATE, adr.getState());
            map.put(WebConstants.ADR_COUNTRY, adr.getCountry());
            map.put(WebConstants.ADR_ZP, String.valueOf(adr.getZipcode()));
            map.put(WebConstants.PHONE, adr.getPhonenumber());

            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.CUST_ADR_ADD);
            resp.setMap(map);
        } catch (Exception e) {
            throw new AddressCustomException("Unable to retrieve address, please try again");
        }
        return new ResponseEntity<Response>(resp, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<ProductResponse> getProducts(Authentication auth) throws IOException {
        ProductResponse resp = new ProductResponse();
        try {
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
            resp.setOblist(prodRepo.findAll());
        } catch (Exception e) {
            throw new ProductCustomException("Unable to retrieve products, please try again");
        }
        return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
    }

    @GetMapping("/addToCart")
    public ResponseEntity<ServerResponse> addToCart(@RequestParam(WebConstants.PROD_ID) String productId,
                                                    Authentication auth) throws IOException {

        ServerResponse resp = new ServerResponse();
        try {
            User loggedUser = userRepo.findByUsername(auth.getName())
                    .orElseThrow(() -> new UserCustomException(auth.getName()));
            Product cartItem = prodRepo.findById(Integer.parseInt(productId));

            Cart buf = new Cart();
            buf.setEmail(loggedUser.getEmail());
            buf.setQuantity(1);
            buf.setPrice(cartItem.getPrice());
            buf.setProductId(Integer.parseInt(productId));
            buf.setProductname(cartItem.getName());
            Date date = new Date();
            buf.setDateAdded(date);

            cartRepo.save(buf);

            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.CART_UPD_MESSAGE_CODE);
        } catch (Exception e) {
            throw new CartCustomException("Unable to add product to cart, please try again");
        }
        return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
    }

    @GetMapping("/viewCart")
    public ResponseEntity<CartResponse> viewCart(Authentication auth) throws IOException {
        logger.info("Inside View cart request method");
        CartResponse resp = new CartResponse();
        try {
            logger.info("Inside View cart request method 2");
            User loggedUser = userRepo.findByUsername(auth.getName())
                    .orElseThrow(() -> new UserCustomException(auth.getName()));
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.VW_CART_MESSAGE);
            resp.setOblist(cartRepo.findByEmail(loggedUser.getEmail()));
        } catch (Exception e) {
            throw new CartCustomException("Unable to retrieve cart items, please try again");
        }

        return new ResponseEntity<CartResponse>(resp, HttpStatus.OK);
    }

    @PutMapping("/updateCart")
    public ResponseEntity<CartResponse> updateCart(@RequestBody HashMap<String, String> cart, Authentication auth)
            throws IOException {

        CartResponse resp = new CartResponse();
        try {
            User loggedUser = userRepo.findByUsername(auth.getName())
                    .orElseThrow(() -> new UserCustomException(auth.getName()));
            Cart selCart = cartRepo.findByCartIdAndEmail(Integer.parseInt(cart.get("id")), loggedUser.getEmail());
            selCart.setQuantity(Integer.parseInt(cart.get("quantity")));
            cartRepo.save(selCart);
            List<Cart> bufcartlist = cartRepo.findByEmail(loggedUser.getEmail());
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.UPD_CART_MESSAGE);
            resp.setOblist(bufcartlist);
        } catch (Exception e) {
            throw new CartCustomException("Unable to update cart items, please try again");
        }

        return new ResponseEntity<CartResponse>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/delCart")
    public ResponseEntity<CartResponse> delCart(@RequestParam(name = WebConstants.BUF_ID) String bufcartid,
                                                Authentication auth) throws IOException {

        CartResponse resp = new CartResponse();
        try {
            User loggedUser = userRepo.findByUsername(auth.getName())
                    .orElseThrow(() -> new UserCustomException(auth.getName()));
            cartRepo.deleteByCartIdAndEmail(Integer.parseInt(bufcartid), loggedUser.getEmail());
            List<Cart> bufcartlist = cartRepo.findByEmail(loggedUser.getEmail());
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.DEL_CART_SUCCESS_MESSAGE);
            resp.setOblist(bufcartlist);
        } catch (Exception e) {
            throw new CartCustomException("Unable to delete cart items, please try again");
        }
        return new ResponseEntity<CartResponse>(resp, HttpStatus.OK);
    }

    @GetMapping("/placeOrder")
    public ResponseEntity<ServerResponse> placeOrder(Authentication auth) throws IOException {

        ServerResponse resp = new ServerResponse();
        try {
            User loggedUser = userRepo.findByUsername(auth.getName())
                    .orElseThrow(() -> new UserCustomException(auth.getName()));
            PlaceOrder po = new PlaceOrder();
            po.setEmail(loggedUser.getEmail());
            Date date = new Date();
            po.setOrder_date(date);
            po.setOrder_status(ResponseCode.ORD_STATUS_CODE);
            double total = 0;
            List<Cart> buflist = cartRepo.findAllByEmail(loggedUser.getEmail());
            for (Cart buf : buflist) {
                total = +(buf.getQuantity() * buf.getPrice());
            }
            po.setTotal_price(total);
            PlaceOrder res = ordRepo.save(po);
            buflist.forEach(bufcart -> {
                bufcart.setOrderId(res.getOrderId());
                cartRepo.save(bufcart);

            });
            resp.setStatus(ResponseCode.SUCCESS_CODE);
            resp.setMessage(ResponseCode.ORD_SUCCESS_MESSAGE);
        } catch (Exception e) {
            throw new PlaceOrderCustomException("Unable to place order, please try again later");
        }
        return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
    }
}
