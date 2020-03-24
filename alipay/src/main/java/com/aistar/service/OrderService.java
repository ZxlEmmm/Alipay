package com.aistar.service;

import com.aistar.pojo.Orders;
import com.aistar.util.SeverResponse;

public interface OrderService {

    SeverResponse save(Orders order);
    SeverResponse getById(Integer id);
    Orders getByTime();

}
