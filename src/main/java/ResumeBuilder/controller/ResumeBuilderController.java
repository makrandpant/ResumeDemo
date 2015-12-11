package ResumeBuilder.controller;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import ResumeBuilder.util.URLMapper;
import ResumeBuilder.util.ViewMapper;

@Controller
public class ResumeBuilderController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ResumeBuilderService resumeBuilderService;

	@RequestMapping(value = URLMapper.RESUME_BUILDER, method = RequestMethod.GET)
	public ModelAndView getResumePage() {
		ModelAndView model = new ModelAndView(ViewMapper.RESUME_BUILDER);
		return model;
	}

	@RequestMapping(value = URLMapper.RESUME_BUILDER,method = RequestMethod.POST)
	public String readFile(
			@ModelAttribute("resumeBuilderDom") ResumeBuilderDom resumeBuilderDom,
			BindingResult result, Model model) throws FileNotFoundException {
		if (result.hasErrors()) {
			model.addAttribute("resume_builder_error", resumeBuilderDom);
			return ViewMapper.RESUME_BUILDER;
		} else {
			MultipartFile importFile = resumeBuilderService.importFile(resumeBuilderDom);
			MultipartFile extractFile = resumeBuilderService.docFileExtractor(resumeBuilderDom);
			ModelAndView model1 = new ModelAndView(ViewMapper.RESUME_BUILDER);
			model1.addObject("import_resume_view", importFile);
			model1.addObject("extract_resume_view",extractFile);
			return ViewMapper.RESUME_BUILDER;
		}
	}
}
