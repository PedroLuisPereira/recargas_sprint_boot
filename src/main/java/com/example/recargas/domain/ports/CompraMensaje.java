package com.example.recargas.domain.ports;

import com.example.recargas.domain.dto.CompraDto;


public interface CompraMensaje {

    void  sendCompra(CompraDto compraDto);

}
