package com.aistar.service.impl;

import com.aistar.mapper.CustomerMapper;
import com.aistar.pojo.Customer;
import com.aistar.service.CustomerService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper mapper;

    @Override
    public SeverResponse getById(Integer id) {
        Customer customer = mapper.selectByPrimaryKey(id);
        if (customer!=null)
            return SeverResponse.getSuccess(customer);
        return SeverResponse.getFail();
    }
}
