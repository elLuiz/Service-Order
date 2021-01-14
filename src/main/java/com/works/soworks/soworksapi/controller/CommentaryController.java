package com.works.soworks.soworksapi.controller;

import com.works.soworks.soworksapi.domain.exception.EntityNotFoundException;
import com.works.soworks.soworksapi.domain.model.Commentary;
import com.works.soworks.soworksapi.domain.model.ServiceOrder;
import com.works.soworks.soworksapi.domain.repository.ServiceOrderRepository;
import com.works.soworks.soworksapi.domain.service.CRUDServiceOrderService;
import com.works.soworks.soworksapi.model.CommentaryInputModel;
import com.works.soworks.soworksapi.model.CommentaryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-order/{service_Id}/commentaries")
public class CommentaryController {
    @Autowired
    private CRUDServiceOrderService serviceOrder;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryModel insert(@PathVariable Long service_Id, @Valid @RequestBody CommentaryInputModel commentaryInputModel){
        Commentary commentary = serviceOrder.addCommentary(service_Id, commentaryInputModel.getDescription());
        return toCommentaryModel(commentary);
    }

    @GetMapping
    public List<CommentaryModel> list(@PathVariable Long service_Id){
        ServiceOrder service = serviceOrderRepository.findById(service_Id).orElseThrow(() -> new EntityNotFoundException("Service order not found."));
        return toCollectionList(service.getCommentaries());
    }

    private CommentaryModel toCommentaryModel(Commentary commentary){
        return modelMapper.map(commentary, CommentaryModel.class);
    }

    private List<CommentaryModel> toCollectionList(List<Commentary> commentaries){
        return commentaries.stream()
                .map(commentary -> modelMapper.map(commentary, CommentaryModel.class))
                .collect(Collectors.toList());
    }
}
