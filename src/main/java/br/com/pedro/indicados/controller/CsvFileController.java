package br.com.pedro.indicados.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.indicados.controller.dto.CsvLineDto;
import br.com.pedro.indicados.controller.form.CsvFileForm;
import br.com.pedro.indicados.service.CsvFileService;

@RestController
@RequestMapping("/csv")
public class CsvFileController {
	
	@Autowired
	private CsvFileService csvFileService;
	
	@PostMapping	             
	public ResponseEntity<List<CsvLineDto>> importar(@RequestBody @Valid CsvFileForm  form) {
		try {			
			List<CsvLineDto> linesError = CsvLineDto.converter(csvFileService.importarDadosCSV(form.getPath()));		
			return ResponseEntity.ok(linesError);
		} catch (Exception e) {		
			return ResponseEntity.badRequest().build();
		}					
	}

}
