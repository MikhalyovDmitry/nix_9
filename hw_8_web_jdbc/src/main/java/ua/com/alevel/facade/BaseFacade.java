package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.RequestDto;
import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES findById(Long id);

    PageData<RES> findAll(WebRequest request);
}
