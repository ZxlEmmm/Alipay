package com.aistar.service;

import com.aistar.util.SeverResponse;

public interface ProductService {

    SeverResponse getById(Integer id);
    SeverResponse getAll();

}
