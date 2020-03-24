package com.aistar.service.impl;

import com.aistar.mapper.FlowMapper;
import com.aistar.pojo.Flow;
import com.aistar.service.FlowService;
import com.aistar.util.SeverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private FlowMapper mapper;
    @Override
    public SeverResponse save(Flow flow) {
        int rows = mapper.insert(flow);
        if (rows>0)
            return SeverResponse.addSuccess();
        return SeverResponse.addFail();
    }
}
