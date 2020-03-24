package com.aistar.service;

import com.aistar.pojo.Detail;
import com.aistar.util.SeverResponse;

public interface DetailService {

    SeverResponse save(Detail detail);
    SeverResponse getById(Integer id);


}
