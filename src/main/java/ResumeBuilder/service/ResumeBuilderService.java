package ResumeBuilder.service;

import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ResumeBuilder.dao.ResumeBuilderDao;
import ResumeBuilder.domain.ResumeBuilderDom;

@Service
public class ResumeBuilderService implements IResumeBuilderService {

	@Autowired
	ResumeBuilderDao connect;
	
	@Override
	public MultipartFile pdfExtractor(ResumeBuilderDom resumeBuilderDom) throws FileNotFoundException {
		
		return connect.pdfExtractor(resumeBuilderDom);
		
	}
}
