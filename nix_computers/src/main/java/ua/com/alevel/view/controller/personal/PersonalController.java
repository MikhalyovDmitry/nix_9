package ua.com.alevel.view.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

@Validated
@Controller
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalFacade personalFacade;
    private final SecurityService securityService;

    public PersonalController(PersonalFacade personalFacade, SecurityService securityService) {
        this.personalFacade = personalFacade;
        this.securityService = securityService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        Long userId = null;
        boolean isAuthenticated = securityService.isAuthenticated();
        if (isAuthenticated) {
            if (personalFacade.findByName(SecurityUtil.getUsername()) != null) {
                userId = personalFacade.findByName(SecurityUtil.getUsername());
            }
        }
        PersonalResponseDto personal = personalFacade.findById(userId);
        model.addAttribute("user", personal);
        return "user_profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute("user") PersonalRequestDto user) {
        Long userId = null;
        boolean isAuthenticated = securityService.isAuthenticated();
        if (isAuthenticated) {
            userId = securityService.currentUser().getId();
        }
        personalFacade.update(user, userId);
        return "redirect:/products/magic";
    }
}
