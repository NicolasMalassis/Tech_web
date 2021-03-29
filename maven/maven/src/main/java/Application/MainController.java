package Application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// classe MVC seulement utile pour bien charger la page index.html quand on tape localhost...
@Controller
public class MainController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/page_accueil/index").setViewName("index");
    }

    @GetMapping("/")
    public String kk() {
        return "redirect:/page_accueil/index.html";
    }

    @PostMapping("/")
    public String gg() {
        return "redirect:/page_accueil/index.html";
    }

}