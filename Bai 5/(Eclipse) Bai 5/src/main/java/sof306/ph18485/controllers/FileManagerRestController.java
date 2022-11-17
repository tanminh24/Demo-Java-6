package sof306.ph18485.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sof306.ph18485.services.FileManagerService;

@CrossOrigin("*")
@RestController
public class FileManagerRestController {
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@GetMapping("/api/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder) {
		return fileManagerService.list(folder);
	}

	@GetMapping("/api/files/{folder}/{file}")
	public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		return fileManagerService.read(folder, file);
	}
	
	@PostMapping("/api/files/{folder}")
	public List<String> upload(@PathVariable("folder") String folder, @PathParam("files") MultipartFile[] files) {
		return fileManagerService.save(folder, files);
	}
	
	@DeleteMapping("/api/files/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		fileManagerService.delete(folder, file);
	}
}
