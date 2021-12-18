package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/users")
public class UserController  extends AbstractController{

    private final UserFacade userFacade;
    private final AccountFacade accountFacade;


    public UserController(UserFacade userFacade, AccountFacade accountFacade) {
        this.userFacade = userFacade;
        this.accountFacade = accountFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnNames = new AbstractController.HeaderName[] {
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("Name", "name", "name"),
                new AbstractController.HeaderName("Password", "password", "password"),
                new AbstractController.HeaderName("Phone", "phone", "phone"),
                new AbstractController.HeaderName("", null, null),
                new AbstractController.HeaderName("", null, null),
                new AbstractController.HeaderName("", null, null)
        };
        PageData<UserResponseDto> response = userFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<AbstractController.HeaderData> headerDataList = new ArrayList<>();
        for (AbstractController.HeaderName headerName : columnNames) {
            AbstractController.HeaderData data = new AbstractController.HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getTableName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Users");
        return "pages/user/user_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/users", model);
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") UserRequestDto userRequestDto) {
        userFacade.create(userRequestDto);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<AccountResponseDto> accounts = userFacade.getAccounts(id);
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", accounts);
        return "pages/user/user_details";
    }

    @GetMapping("/open/{id}")
    public String openAccount(@PathVariable Long id, Model model) {
        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUserId(id);
        Long accountId = accountFacade.create(accountRequestDto).getId();
        userFacade.addAccount(id, accountId);
        List<AccountResponseDto> accounts = userFacade.getAccounts(id);
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", accounts);
        return "pages/user/user_details";
    }

    @GetMapping("/close/{userId}/{accountId}")
    public String closeAccount(@PathVariable Long userId, @PathVariable Long accountId, Model model) {
        userFacade.removeAccount(userId, accountId);
        List<AccountResponseDto> accounts = userFacade.getAccounts(userId);
        model.addAttribute("user", userFacade.findById(userId));
        model.addAttribute("accounts", accounts);
        return "pages/user/user_details";
    }


}
