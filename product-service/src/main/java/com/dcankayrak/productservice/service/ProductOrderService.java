package com.dcankayrak.productservice.service;

import com.dcankayrak.productservice.converter.GeneralConverter;
import com.dcankayrak.productservice.dto.request.productOrder.ProductOrderSaveRequestDto;
import com.dcankayrak.productservice.dto.response.CartListResponseDto;
import com.dcankayrak.productservice.dto.response.OrderListByUserResponseDto;
import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.Order;
import com.dcankayrak.productservice.entity.Product;
import com.dcankayrak.productservice.entity.ProductOrder;
import com.dcankayrak.productservice.exception.ProductNotFoundException;
import com.dcankayrak.productservice.feign.UserServiceClient;
import com.dcankayrak.productservice.repository.OrderRepository;
import com.dcankayrak.productservice.repository.ProductOrderRepository;
import com.dcankayrak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final GeneralConverter generalConverter;
    private final OrderService orderService;
    public void saveProductOrder(ProductOrderSaveRequestDto request,Long orderId,Long userId) {
        Product tempProduct = productRepository
                .findBySlug(request.getProductSlug());
        Order tempOrder = this.orderRepository.findById(orderId).orElseThrow();

        if(tempProduct != null &&userServiceClient.isUserExists(userId)){
            ProductOrder newProductOrder = new ProductOrder();
            newProductOrder.setProduct(tempProduct);
            newProductOrder.setOrder(tempOrder);
            newProductOrder.setUserId(userId);
            newProductOrder.setQuantity(request.getQuantity());
            productOrderRepository.save(newProductOrder);
        }else{
            throw new RuntimeException("Kullanıcı Bulunamadı!");
        }
    }

    public List<OrderListByUserResponseDto> getOrdersByUser(Long userId) {
        List<ProductOrder> tempList = this.productOrderRepository.findByUserId(userId);
        List<OrderListByUserResponseDto> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        for (ProductOrder item:tempList
             ) {
            OrderListByUserResponseDto tempItem = new OrderListByUserResponseDto();
            tempItem.setProduct(this.generalConverter.convertEntityToTargetEntity(item.getProduct(), ProductListResponseDto.class));
            tempItem.setQuantity(item.getQuantity());
            String time = sdf.format(item.getCreateDate());
            tempItem.setDate(time);
            resultList.add(tempItem);
        }

        return resultList;
    }

    public void createProductOrders(List<ProductOrderSaveRequestDto> request,Long userId) {
        Order order = this.orderService.createOrder();

        for (ProductOrderSaveRequestDto product:request) {
            this.saveProductOrder(product,order.getId(),userId);
        }
    }
}
