package ResumeBuilder.dao;

import java.io.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import ResumeBuilder.domain.ResumeBuilderDom;

public interface IResumeBuilderDao {

		MultipartFile pdfExtractor(ResumeBuilderDom resumeBuilderDom)
				throws FileNotFoundException;
}
