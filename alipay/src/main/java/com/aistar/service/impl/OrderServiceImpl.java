package com.aistar.service.impl;

import com.aistar.mapper.OrdersMapper;
import com.aistar.pojo.*;
import com.aistar.service.DetailService;
import com.aistar.service.OrderService;
import com.aistar.service.ProductService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper mapper;
    @Autowired
    private DetailService detailService;

    @Override
    public SeverResponse save(Orders order) {
        int rows = mapper.insertSelective(order);
        if (rows>0)
            return SeverResponse.addSuccess();
        return SeverResponse.addFail();
    }

    @Autowired
    private ProductService productService;

    @Override
    public SeverResponse getById(Integer id) {
        OrderDetail orderDetail = new OrderDetail();
        Orders order = mapper.selectByPrimaryKey(id);
        SeverResponse severResponse= detailService.getById(id);
        Detail detail = (Detail) severResponse.getData();
        orderDetail.setOrders(order);
        orderDetail.setDetail(detail);
        severResponse =  productService.getById(detail.getProductId());
        Pro pro = (Pro)severResponse.getData();
        orderDetail.setPro(pro);

        return SeverResponse.getSuccess(orderDetail);
    }

    @Override
    public Orders getByTime() {
        OrdersExample example = new OrdersExample();
        example.setOrderByClause("order_id desc");
        List<Orders> orders = mapper.selectByExample(example);
        return orders.get(0);
    }
}
