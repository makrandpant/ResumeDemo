package ResumeBuilder.dao;

import java.io.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import ResumeBuilder.domain.ResumeBuilderDom;

public interface IResumeBuilderDao {

	MultipartFile importFile(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException;
	
		MultipartFile docFileExtractor(ResumeBuilderDom resumeBuilderDom)
				throws FileNotFoundException;
}
