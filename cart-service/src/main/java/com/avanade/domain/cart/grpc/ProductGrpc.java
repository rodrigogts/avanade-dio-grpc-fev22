package com.avanade.domain.cart.grpc;

import com.avanade.domain.cart.data.ProductData;
import com.avanade.grpc.product.lib.ProductRequest;
import com.avanade.grpc.product.lib.ProductResponse;
import com.avanade.grpc.product.lib.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductGrpc {

    @GrpcClient("product-server")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceStub;

    public Optional<ProductData> receiveProductByDescription(String description) {
        ProductRequest request = ProductRequest.newBuilder()
                .setDescription(description)
                .build();

        ProductResponse response = serviceStub.productsByDescription(request);
        if (StringUtils.isEmpty(response.getId())) {
            return Optional.empty();
        }

        ProductData product = new ProductData();
        product.setId(response.getId());
        product.setDescription(response.getDescription());

        return Optional.of(product);
    }
}
