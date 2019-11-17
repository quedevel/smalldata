package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dto.AttachDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j
@Controller
public class UploadController {

//	@GetMapping("/uploadForm")
//	public void uploadByForm() {
//	}

//	@PostMapping("/uploadFormAction")
//	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			log.info("-----------------------------------------------");
//			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
//			log.info("Upload File Size: " + multipartFile.getSize());
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			// IE has file path
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//			log.info("only file name: " + uploadFileName);
//			UUID uuid = UUID.randomUUID();
//			String uploadName = uuid.toString() + "_" + uploadFileName;
//			File saveFile = new File("C:\\upload", uploadName);
//			
//			try {
//				multipartFile.transferTo(saveFile);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	@GetMapping("/uploadAjax")
	public void uploadByAjax() {
	}

	@GetMapping("/downFile")
	@ResponseBody
	public ResponseEntity<byte[]> downFile(String fname) {
		File file = new File("C:\\upload", fname);
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition",
			          "attachment; filename=" + new String(fname.substring(fname.lastIndexOf("_")+1).getBytes("UTF-8"), "ISO-8859-1"));
			byte[] data = FileCopyUtils.copyToByteArray(file);
			return new ResponseEntity<>(data, header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/viewFile")
	@ResponseBody
	public ResponseEntity<byte[]> viewFile(String fname) {
		File file = new File("C:\\upload", fname);
		try {
			String contentType = Files.probeContentType(file.toPath());
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", contentType);
			byte[] data = FileCopyUtils.copyToByteArray(file);
			return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachDTO>> uploadAjaxPost(MultipartFile[] uploadFile, Model model) {
		List<AttachDTO> list = new ArrayList<>();
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-----------------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();
			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			UUID uuid = UUID.randomUUID();
			String uploadName = uuid.toString() + "_" + uploadFileName;
			File saveFile = new File("C:\\upload", uploadName);

			try {
				multipartFile.transferTo(saveFile);
				boolean isImage = checkImageType(saveFile);
				if (isImage) {
					FileOutputStream thumbnail = new FileOutputStream(new File("C:\\upload", "s_" + uploadName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				AttachDTO fileInfo = new AttachDTO(multipartFile.getOriginalFilename(), uploadName, isImage);
				log.info("AttachDTO :                " + fileInfo);
				list.add(fileInfo);
			} catch (Exception e) {
				e.printStackTrace();
			} // try - catch
		} // for

		return new ResponseEntity<List<AttachDTO>>(list, HttpStatus.OK);
	}

	private boolean checkImageType(File file) {
		boolean result = false;

		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info("checkImageType =                        " + contentType);
			result = contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
