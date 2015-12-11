package ResumeBuilder.service;

import java.io.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import ResumeBuilder.domain.ResumeBuilderDom;

public interface IResumeBuilderService {
	
	MultipartFile importFile(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException;
	
	MultipartFile docFileExtractor(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException;
}
