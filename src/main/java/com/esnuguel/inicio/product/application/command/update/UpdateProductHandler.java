package com.esnuguel.inicio.product.application.command.update;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.category.domain.Category;
import com.esnuguel.inicio.category.infrastructure.CategoryEntityMapper;
import com.esnuguel.inicio.category.infrastructure.QueryCategoryRepository;
import com.esnuguel.inicio.common.application.mediator.RequestHandler;
import com.esnuguel.inicio.common.infrastructure.util.FileUtils;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.exception.ProductNotFounException;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;
import com.esnuguel.inicio.productDetail.domain.ProductDetail;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest,Void> {

    private final ProductoRepository productRepository;
    private final QueryCategoryRepository queryCategoryRepository;  
    private final CategoryEntityMapper categoryEntityMapper;
    //private final FileUtils fileUtils;

    @Override
    public Void handle(UpdateProductRequest request) {
        //String uniqueFileName=fileUtils.saveProductImage(request.getFile());

        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFounException(request.getId()));

        ProductDetail productDetail = product.getProductDetail();

        productDetail.setProvider(request.getProvider());

        product.getReviews().add(request.getReview());

        Category category = queryCategoryRepository.findById(request.getCategoryId())
                .map(categoryEntityMapper::mapToCategory)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.getCategories().add(category);

        productRepository.uppsert(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
    
}
