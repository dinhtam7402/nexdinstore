package com.nexdin.store.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexdin.store.constant.ResourceName;
import com.nexdin.store.entity.*;
import com.nexdin.store.mapper.*;
import com.nexdin.store.payload.request.*;
import com.nexdin.store.payload.response.*;
import com.nexdin.store.repository.*;
import com.nexdin.store.service.CrudService;
import com.nexdin.store.service.GenericService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

@Component
@AllArgsConstructor
public class GenericMappingRegister {

    private ApplicationContext context;
    private RequestMappingHandlerMapping handlerMapping;

    private GenericController<CategoryRequest, CategoryResponse> categoryController;
    private GenericController<ProductRequest, ProductResponse> productController;
    private GenericController<ProductVariantRequest, ProductVariantResponse> productVariantController;
    private GenericController<ProviderRequest, ProviderResponse> providerController;
    private GenericController<UserRequest, UserResponse> userController;
    private GenericController<ReviewRequest, ReviewResponse> reviewController;
    private GenericController<CommentRequest, CommentResponse> commentController;
    private GenericController<PaymentMethodRequest, PaymentMethodResponse> paymentMethodController;
    private GenericController<CustomerRequest, CustomerResponse> customerController;

    private GenericService<Category, CategoryRequest, CategoryResponse> categoryService;
    private GenericService<Product, ProductRequest, ProductResponse> productService;
    private GenericService<ProductVariant, ProductVariantRequest, ProductVariantResponse> productVariantService;
    private GenericService<Provider, ProviderRequest, ProviderResponse> providerService;
    private GenericService<User, UserRequest, UserResponse> userService;
    private GenericService<Review, ReviewRequest, ReviewResponse> reviewService;
    private GenericService<Comment, CommentRequest, CommentResponse> commentService;
    private GenericService<PaymentMethod, PaymentMethodRequest, PaymentMethodResponse> paymentMethodService;
    private GenericService<Customer, CustomerRequest, CustomerResponse> customerService;
    @PostConstruct
    public void registerControllers() throws NoSuchMethodException {
        register("category", categoryController, categoryService.init(
                context.getBean(CategoryRepository.class),
                context.getBean(CategoryMapper.class),
                ResourceName.CATEGORY
        ), CategoryRequest.class);

        register("product", productController, productService.init(
                context.getBean(ProductRepository.class),
                context.getBean(ProductMapper.class),
                ResourceName.PRODUCT
        ), ProductRequest.class);

        register("product-variant", productVariantController, productVariantService.init(
                context.getBean(ProductVariantRepository.class),
                context.getBean(ProductVariantMapper.class),
                ResourceName.PRODUCT_VARIANT
        ), ProductVariantRequest.class);

        register("provider", providerController, providerService.init(
                context.getBean(ProviderRepository.class),
                context.getBean(ProviderMapper.class),
                ResourceName.PROVIDER
        ), ProviderRequest.class);

        register("user", userController, userService.init(
                context.getBean(UserRepository.class),
                context.getBean(UserMapper.class),
                ResourceName.USER
        ), UserRequest.class);

        register("review", reviewController, reviewService.init(
                context.getBean(ReviewRepository.class),
                context.getBean(ReviewMapper.class),
                ResourceName.REVIEW
        ), ReviewRequest.class);

        register("comment", commentController, commentService.init(
                context.getBean(CommentRepository.class),
                context.getBean(CommentMapper.class),
                ResourceName.COMMENT
        ), CommentRequest.class);

        register("payment-method", paymentMethodController, paymentMethodService.init(
                context.getBean(PaymentMethodRepository.class),
                context.getBean(PaymentMethodMapper.class),
                ResourceName.PAYMENT_METHOD
        ), PaymentMethodRequest.class);

        register("customer", customerController, customerService.init(
                context.getBean(CustomerRepository.class),
                context.getBean(CustomerMapper.class),
                ResourceName.CUSTOMER
        ), CustomerRequest.class);
    }

    private <I, O> void register(String resource, GenericController<I, O> controller, CrudService<Integer, I, O> service, Class<I> requestType) throws NoSuchMethodException {
        RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
        options.setPatternParser(new PathPatternParser());

        controller.setCrudService(service);
        controller.setRequestType(requestType);

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getAllResource", int.class, int.class, String.class, boolean.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getResource", Integer.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.POST)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("createResource", JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.PUT)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("updateResource", Integer.class, JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.DELETE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResource", Integer.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.DELETE)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResource", List.class)
        );
    }
}
