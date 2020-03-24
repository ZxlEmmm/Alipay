package com.aistar.service.impl;

import com.aistar.mapper.ProMapper;
import com.aistar.pojo.Pro;
import com.aistar.service.ProductService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProMapper mapper;

    @Override
    public SeverResponse getById(Integer id) {
        Pro pro = mapper.selectByPrimaryKey(id);
        if (pro!=null)
            return SeverResponse.getSuccess(pro);
        return SeverResponse.getFail();
    }

    @Override
    public SeverResponse getAll() {
        List<Pro> pros = mapper.selectByExample(null);
        if (pros!=null&&pros.size()>0)
            return SeverResponse.getSuccess(pros);
        return SeverResponse.getFail();
    }
}
