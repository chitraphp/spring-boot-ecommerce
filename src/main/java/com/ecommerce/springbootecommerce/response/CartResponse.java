package com.ecommerce.springbootecommerce.response;

import com.ecommerce.springbootecommerce.model.Cart;

import java.util.List;

public class CartResponse {
    private String status;
    private String message;
    private String AUTH_TOKEN;
    private List<Cart> oblist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String aUTH_TOKEN) {
        AUTH_TOKEN = aUTH_TOKEN;
    }

    public List<Cart> getOblist() {
        return oblist;
    }

    public void setOblist(List<Cart> oblist) {
        this.oblist = oblist;
    }
}
