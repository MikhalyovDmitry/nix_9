package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class NixComputersApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final PersonalRepository personalRepository;
    private final SecurityService securityService;

    public NixComputersApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository, PersonalRepository personalRepository, UserRepository userRepository, SecurityService securityService) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.personalRepository = personalRepository;
        this.securityService = securityService;
    }


    public static void main(String[] args) {
        SpringApplication.run(NixComputersApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {

//        Personal personal = new Personal();
//        personal.setEmail("xxxxxxxx@mail");
//        personal.setPassword(encoder.encode("xxxxxxxx"));
//        personalRepository.save(personal);
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminRepository.save(admin);
//        String pass = "123456789";
//        String encode = encoder.encode(pass);
//        System.out.println("encode = " + encode);
//        String newEncode = encoder.encode(pass);
//        System.out.println("newEncode = " + newEncode);
//        boolean matches = newEncode.matches(pass);
//        System.out.println("matches = " + matches);
    }
}
