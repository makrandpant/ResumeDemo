package ResumeBuilder.domain;

import org.springframework.web.multipart.MultipartFile;

public class ResumeBuilderDom {

	private MultipartFile fileResume;
	private String fileName;

	

	public MultipartFile getFileResume() {
		return fileResume;
	}

	public void setFileResume(MultipartFile fileResume) {
		this.fileResume = fileResume;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
