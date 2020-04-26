package wizut.tpsi.springlab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculatorController {

	@RequestMapping("/calculator")
	public String calculator(Model model, CalculatorForm cal) {

		switch (cal.getOp()) {
		case "+":
			cal.setWynik(cal.getX() + cal.getY());
			break;
		case "-":
			cal.setWynik(cal.getX() - cal.getY());
			break;
		case "*":
			cal.setWynik(cal.getX() * cal.getY());
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + cal);
		}
		model.addAttribute("cal", cal);
		return "calculator";
	}

}
