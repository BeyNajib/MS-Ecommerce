package com.example.client.proxies;

import com.example.client.bean.CartBean;
import com.example.client.bean.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@FeignClient(name = "ms-order", url = "localhost:8095")
public interface MsCommandeProxy {

    @PostMapping(value = "/order")
    Optional<CommandeBean> createOrder(@RequestBody CartBean cartBean);
}
