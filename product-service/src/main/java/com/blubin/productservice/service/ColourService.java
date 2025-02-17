package com.blubin.productservice.service;

import com.blubin.commonservice.exception.DuplicatedException;
import com.blubin.commonservice.exception.NotFoundException;
import com.blubin.productservice.model.Colour;
import com.blubin.productservice.model.Colour;
import com.blubin.productservice.repository.ColourRepository;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.viewmodel.colour.ColourGetVm;
import com.blubin.productservice.viewmodel.colour.ColourListGetVm;
import com.blubin.productservice.viewmodel.colour.ColourPostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColourService {
    private final ColourRepository colourRepository;

    public ColourService(ColourRepository colourRepository) {
        this.colourRepository = colourRepository;
    }


    private boolean checkExistedHexCode(String hexCode, Long id) {
        return colourRepository.findExistedHexCode(hexCode, id) != null;
    }

    private void validExistedName(String hexCode, Long ColourId) {
        if(checkExistedHexCode(hexCode, ColourId)) {
            throw new DuplicatedException(Constants.ErrorCodes.HEX_CODE_ALREADY_EXITED,hexCode);
        }
    }

    public Colour create(ColourPostVm colourPostVm){
        validExistedName(colourPostVm.hexCode(),null);
        return colourRepository.save(colourPostVm.toModel());
    }

    public ColourListGetVm getColourList(int pageNo, int pageSize) {
        List<ColourGetVm> colourGetVms = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Colour> coloursPage = colourRepository.findAll(pageable);
        List<Colour> colourList = coloursPage.getContent();

        for (Colour colour : colourList) {
            colourGetVms.add(ColourGetVm.fromModel(colour));
        }

        return new ColourListGetVm(
                colourGetVms,
                coloursPage.getNumber(),
                coloursPage.getSize(),
                (int) coloursPage.getTotalElements(),
                coloursPage.getTotalPages(),
                coloursPage.isLast()
        );
    }


    public Colour update(ColourPostVm ColourPostVm, Long id) {
        validExistedName(ColourPostVm.hexCode(), id);

        Colour Colour = colourRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.ErrorCodes.HEX_CODE_ALREADY_EXITED, id));
        Colour.setColourName(ColourPostVm.colourName());
        Colour.setHexCode(ColourPostVm.hexCode());

        return colourRepository.save(Colour);
    }
}
