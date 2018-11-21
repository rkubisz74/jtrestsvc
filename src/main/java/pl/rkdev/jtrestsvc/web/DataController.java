package pl.rkdev.jtrestsvc.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.rkdev.jtrestsvc.dao.DaneDao;
import pl.rkdev.jtrestsvc.models.Dane;

@RestController
@RequestMapping("/app")
public class DataController {
	
	@Autowired
	private DaneDao daneDao;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@GetMapping
	public List<Dane> getAllRecords(@RequestParam Map<String, String> paramList) {
		Date dateFrom = null;
		if(paramList.containsKey("from"))
			try {
				dateFrom = df.parse(paramList.get("from"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		Date dateTo = null;
		if(paramList.containsKey("to"))
			try {
				dateTo = df.parse(paramList.get("to"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return daneDao.findAll(dateFrom, dateTo);
	}
	
	@GetMapping("/{id}")
	public Dane getRecordById(@PathVariable Long id) {
		try {
			return daneDao.findById(id);
		} catch(Exception e) {
			//ToDo
		}
		return null;
	}
	
	@PostMapping
	public ResponseEntity<String> saveRecord(@RequestBody Dane dane) {
		Long id = daneDao.save(dane);
		if(id != null)
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<String> updateRecord(@RequestBody Dane dane) {
		int updatedRows = daneDao.update(dane);
		if(updatedRows > 0)
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
		int rowCount = daneDao.delete(id);
		if(rowCount > 0)
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
