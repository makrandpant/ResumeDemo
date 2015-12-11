package ResumeBuilder.service;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ResumeBuilder.dao.ResumeBuilderDao;
import ResumeBuilder.domain.ResumeBuilderDom;

@Service
public class ResumeBuilderService implements IResumeBuilderService {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ResumeBuilderDao connect;
	
	@Override
	public MultipartFile importFile(ResumeBuilderDom resumeBuilderDom)
			throws FileNotFoundException {
		return connect.importFile(resumeBuilderDom);
	}
	
	@Override
	public MultipartFile docFileExtractor(ResumeBuilderDom resumeBuilderDom) throws FileNotFoundException {
		
		return connect.docFileExtractor(resumeBuilderDom);
		
	}
}
