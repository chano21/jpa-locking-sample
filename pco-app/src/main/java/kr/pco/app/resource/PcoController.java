package kr.pco.app.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pco.service.MemberService;

@RestController
@RequestMapping("/")
public class PcoController {
	
	@Autowired
	MemberService service;
	
//	public pcoController(pcoService service) {
//		this.service=service;
//	}
	
	@GetMapping(value = "/")
	public ResponseEntity<Void> getTodo() {
		service.printBean();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
