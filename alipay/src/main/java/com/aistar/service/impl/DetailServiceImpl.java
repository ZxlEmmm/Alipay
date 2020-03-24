package com.aistar.service.impl;

import com.aistar.mapper.DetailMapper;
import com.aistar.pojo.Detail;
import com.aistar.service.DetailService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    private DetailMapper mapper;

    @Override
    public SeverResponse save(Detail detail) {
        int rows = mapper.insert(detail);
        if (rows>0)
            return SeverResponse.addSuccess();
        return SeverResponse.addFail();
    }

    @Override
    public SeverResponse getById(Integer id) {
        Detail detail = mapper.selectByPrimaryKey(id);
        if (detail!=null)
            return SeverResponse.getSuccess(detail);
        return SeverResponse.getFail();
    }
}
