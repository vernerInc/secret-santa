package secret.santa.standalone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({"/secret-santa"})
public class SecretSantaController {

    @Autowired
    SecretSanta secretSanta;

    @RequestMapping(value = "/says", method = RequestMethod.GET, produces = "application/json")
    public void says() {
        secretSanta.says();
    }
}
