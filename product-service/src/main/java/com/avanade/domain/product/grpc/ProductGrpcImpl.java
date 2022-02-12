package com.avanade.domain.product.grpc;

import com.avanade.domain.product.model.ProductModel;
import com.avanade.domain.product.repositories.ProductRepository;
import com.avanade.grpc.product.lib.ProductRequest;
import com.avanade.grpc.product.lib.ProductResponse;
import com.avanade.grpc.product.lib.ProductServiceGrpc.ProductServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class ProductGrpcImpl extends ProductServiceImplBase {

    private final ProductRepository repository;

    public ProductGrpcImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void productsByDescription(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {

        Optional<ProductModel> productOpt = repository.findByDescription(request.getDescription());
        ProductResponse response;

        if (productOpt.isPresent()) {
            ProductModel product = productOpt.get();
            response = ProductResponse.newBuilder()
                    .setId(product.getId())
                    .setDescription(product.getDescription())
                    .build();
        } else {
            response =  ProductResponse.newBuilder().build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
