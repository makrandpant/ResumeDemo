package ResumeBuilder.dao;

import java.io.FileNotFoundException;

import org.jsoup.nodes.Document;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ResumeBuilder.domain.ResumeBuilderDom;

public interface IResumeBuilderDao {

	MultipartFile importFile(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException;
	
		String docFileExtractor(ResumeBuilderDom resumeBuilderDom)
				throws FileNotFoundException;
}
