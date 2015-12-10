package ResumeBuilder.controller;

import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ResumeBuilder.domain.ResumeBuilderDom;
import ResumeBuilder.service.ResumeBuilderService;

@Controller
public class ResumeBuilderController {

	@Autowired
	ResumeBuilderService resumeBuilderService;

	@RequestMapping(value = "/ResumeBuilder", method = RequestMethod.GET)
	public ModelAndView getResumePage() {
		ModelAndView model = new ModelAndView("ResumeBuilder");
		return model;
	}

	@RequestMapping(value = "/ResumeBuilder", method = RequestMethod.POST)
	public ModelAndView readPdf(@ModelAttribute("resumeBuilderDom") ResumeBuilderDom resumeBuilderDom,BindingResult result,Model model )throws FileNotFoundException {
		if (result.hasErrors()) {
			model.addAttribute("ResumeBuilder",resumeBuilderDom);
			return new ModelAndView("ResumeBuilder");
		} else {
		MultipartFile resumeFile = resumeBuilderService.pdfExtractor(resumeBuilderDom);
		ModelAndView model1 = new ModelAndView("ResumeBuilder");
		model1.addObject("resumeView", resumeFile);
		return model1;
	}
	}
}
