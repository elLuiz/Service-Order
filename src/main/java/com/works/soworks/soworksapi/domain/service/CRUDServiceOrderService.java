package com.works.soworks.soworksapi.domain.service;

import com.works.soworks.soworksapi.domain.exception.ClientException;
import com.works.soworks.soworksapi.domain.exception.EntityNotFoundException;
import com.works.soworks.soworksapi.domain.model.Client;
import com.works.soworks.soworksapi.domain.model.Commentary;
import com.works.soworks.soworksapi.domain.model.ServiceOrder;
import com.works.soworks.soworksapi.domain.model.Status;
import com.works.soworks.soworksapi.domain.repository.ClientRepository;
import com.works.soworks.soworksapi.domain.repository.CommentaryRepository;
import com.works.soworks.soworksapi.domain.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CRUDServiceOrderService {
    @Autowired
    private ServiceOrderRepository serviceOrderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommentaryRepository commentaryRepository;

    public ServiceOrder create(ServiceOrder serviceOrder){
        Client client = clientRepository.findById(serviceOrder.getClient().getId()).orElseThrow(() -> new ClientException("Client not found."));
        serviceOrder.setClient(client);
        serviceOrder.setStatus(Status.OPEN);
        serviceOrder.setDate_opening(OffsetDateTime.now());
        return serviceOrderRepository.save(serviceOrder);
    }

    public List<ServiceOrder> list(){
        return serviceOrderRepository.findAll();
    }

    public Optional<ServiceOrder> search(Long serviceId){
        return serviceOrderRepository.findById(serviceId);
    }

    public Commentary addCommentary(Long serviceOrderId, String description){
        ServiceOrder serviceOrder = getServiceOrder(serviceOrderId);

        Commentary commentary = new Commentary();
        commentary.setServiceOrder(serviceOrder);
        commentary.setDescription(description);
        commentary.setDateCommentary(OffsetDateTime.now());
        return commentaryRepository.save(commentary);
    }

    public void finishService(Long serviceId){
        ServiceOrder serviceOrder = getServiceOrder(serviceId);
        serviceOrder.finish();
        serviceOrderRepository.save(serviceOrder);
    }


    private ServiceOrder getServiceOrder(Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Service order not found."));
        return serviceOrder;
    }

}
