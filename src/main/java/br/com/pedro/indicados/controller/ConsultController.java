package br.com.pedro.indicados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.indicados.controller.dto.ConsultMaxMinDto;
import br.com.pedro.indicados.service.ConsultService;

@RestController
@RequestMapping("/consult")
public class ConsultController {
	
	@Autowired
	private ConsultService consultService;
	
	@GetMapping	             
	public ResponseEntity<ConsultMaxMinDto> importar() {		
		try {
			ConsultMaxMinDto res = consultService.consultMaxMinWiner();			
			return ResponseEntity.ok(res);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
