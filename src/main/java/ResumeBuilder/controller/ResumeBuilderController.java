package ResumeBuilder.controller;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
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
	public ModelAndView readFile(
			@ModelAttribute("resumeBuilderDom") ResumeBuilderDom resumeBuilderDom,
			BindingResult result, ModelAndView modelAndView) throws FileNotFoundException {
		if (result.hasErrors()) {
			modelAndView.addObject("resume_builder_error", resumeBuilderDom);
			return new ModelAndView(ViewMapper.RESUME_BUILDER);
		} else {
			MultipartFile importFile = resumeBuilderService.importFile(resumeBuilderDom);
			String extractFile = resumeBuilderService.docFileExtractor(resumeBuilderDom);
			modelAndView.addObject("import_resume_view", importFile);
			modelAndView.addObject("extract_resume_view",extractFile);
			return new ModelAndView(ViewMapper.RESUME_BUILDER,"extract_resume_view",extractFile);
		}
	}
}
