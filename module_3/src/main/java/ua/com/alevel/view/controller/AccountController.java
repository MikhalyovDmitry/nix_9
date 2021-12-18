package ua.com.alevel.view.controller;

import com.opencsv.CSVWriter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.type.Category;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/accounts")
public class AccountController extends AbstractController {

    private final UserFacade userFacade;
    private final AccountFacade accountFacade;
    private final OperationFacade operationFacade;

    public AccountController(UserFacade userFacade, AccountFacade accountFacade, OperationFacade operationFacade) {
        this.userFacade = userFacade;
        this.accountFacade = accountFacade;
        this.operationFacade = operationFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnNames = new AbstractController.HeaderName[] {
                new AbstractController.HeaderName("#", null, null),
                new AbstractController.HeaderName("User", "user", "user"),
                new AbstractController.HeaderName("Balance", "balance", "balance"),
                new AbstractController.HeaderName("", null, null),
                new AbstractController.HeaderName("", null, null),
                new AbstractController.HeaderName("", null, null),
                new AbstractController.HeaderName("", null, null)
        };
        PageData<AccountResponseDto> response = accountFacade.findAll(webRequest);
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
        model.addAttribute("createUrl", "/accounts/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Accounts");
        return "pages/account/account_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/accounts", model);
    }

    @GetMapping("/operate/{id}")
    public String redirectToOperationCreate(Model model, @PathVariable Long id) {
        OperationRequestDto operation = new OperationRequestDto();
        AccountResponseDto account = accountFacade.findById(id);
        model.addAttribute("operation", operation);
        model.addAttribute("account", account);
        model.addAttribute("categories", Category.values());
        return "pages/operation/operation_new";
    }

    @PostMapping("/operate/{id}")
    public String createOperation(@PathVariable Long id, @ModelAttribute("operation") OperationRequestDto operationRequestDto) {
        operationRequestDto.setAccountId(id);
        Long operationId = operationFacade.create(operationRequestDto).getId();
        accountFacade.addOperation(id, operationId);
        return "redirect:/accounts";
    }

    @GetMapping("/details/{id}")
    public String accountDetails(@PathVariable Long id, Model model) throws IOException {
        AccountResponseDto account = accountFacade.findById(id);
        List<OperationResponseDto> operations = accountFacade.getOperations(id);
        model.addAttribute("account", account);
        model.addAttribute("operations", operations);
        return  "pages/account/account_details";
    }

    @GetMapping("/csv/{id}")
    public String csvStatement(@PathVariable Long id, Model model) {
        String fileName = "out.csv";
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/my_schema?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true";
        String user = "root";
        String password = "Qqq111!!!";

        Path myPath = Paths.get(fileName);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM operations WHERE account_id =" + id);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            try (CSVWriter writer = new CSVWriter(Files.newBufferedWriter(myPath,
                    StandardCharsets.UTF_8), CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END)) {

                writer.writeAll(resultSet, true);
            }

        } catch (IOException | SQLException ex) {
            System.out.println("An error occurred by SQL or IO");
            ex.printStackTrace();
        }

        AccountResponseDto account = accountFacade.findById(id);
        List<OperationResponseDto> operations = accountFacade.getOperations(id);
        model.addAttribute("account", account);
        model.addAttribute("operations", operations);
        return  "pages/account/account_details";
    }

    @GetMapping("/transfer/{id}")
    public String transfer(@PathVariable Long id, WebRequest webRequest, Model model) {
        List<AccountResponseDto> accounts = accountFacade.findAll(webRequest).getItems();
        model.addAttribute("accounts", accounts);
        model.addAttribute("accountSenderId", id);
        return "pages/user/user_add";
    }

    @GetMapping("/transfer/{senderId}/{recipientId}")
    public String transferTo(@PathVariable Long senderId, @PathVariable Long recipientId, Model model) {
        OperationRequestDto operation = new OperationRequestDto();
        model.addAttribute("operation", operation);
        model.addAttribute("senderId", senderId);
        model.addAttribute("recipientId", recipientId);
        return "pages/operation/transfer_new";
    }

    @PostMapping("/transfer/{senderId}/{recipientId}")
    public String commit(@PathVariable Long senderId, @PathVariable Long recipientId, @ModelAttribute("operation") OperationRequestDto operationRequestDto) {
        operationRequestDto.setAccountId(senderId);
        operationRequestDto.setCategory(Category.EXPENSE);
        Long operationId = operationFacade.create(operationRequestDto).getId();
        accountFacade.addOperation(senderId, operationId);

        operationRequestDto.setAccountId(recipientId);
        operationRequestDto.setCategory(Category.INCOME);
        Long operationIdTwo = operationFacade.create(operationRequestDto).getId();
        accountFacade.addOperation(recipientId, operationIdTwo);
        return "redirect:/accounts";
    }
}
