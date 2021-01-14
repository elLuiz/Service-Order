package com.works.soworks.soworksapi.controller;

import com.works.soworks.soworksapi.domain.model.ServiceOrder;
import com.works.soworks.soworksapi.domain.service.CRUDServiceOrderService;
import com.works.soworks.soworksapi.model.ServiceOrderModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController {
    @Autowired
    private CRUDServiceOrderService orderService;

    @Autowired
    private ModelMapper  modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderModel create(@Valid @RequestBody ServiceOrder serviceOrder){
        return toServiceOrderModel(orderService.create(serviceOrder));
    }

    @GetMapping
    public List<ServiceOrderModel> list(){
        return toServiceOrderModelList(orderService.list());
    }
    @GetMapping("/{service_Id}")
    public ResponseEntity<ServiceOrderModel> searchById(@PathVariable Long service_Id){
        Optional<ServiceOrder> serviceOrder = orderService.search(service_Id);
        if(serviceOrder.isPresent()){
            ServiceOrderModel serviceOrderModel = toServiceOrderModel(serviceOrder.get());
            return ResponseEntity.ok(serviceOrderModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{service_Id}/finalization")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finishService(@PathVariable Long service_Id){
        orderService.finishService(service_Id);
    }

    private ServiceOrderModel toServiceOrderModel(ServiceOrder serviceOrder){
        return modelMapper.map(serviceOrder, ServiceOrderModel.class);
    }

    private List<ServiceOrderModel> toServiceOrderModelList(List<ServiceOrder> serviceOrders){
        return serviceOrders.stream()
                .map(serviceOrder -> toServiceOrderModel(serviceOrder))
                .collect(Collectors.toList());
    }
}
