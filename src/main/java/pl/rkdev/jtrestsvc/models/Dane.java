package pl.rkdev.jtrestsvc.models;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","date","value","deviceId","eventDate"})
public class Dane {

	private Long id;
	@JsonIgnore
	private Timestamp date;
	private Float value;
	private Integer deviceId;
	private Timestamp eventDate;
	
	public Dane() {
		date = new Timestamp(Calendar.getInstance().getTime().getTime());
	}
	
	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonProperty("date")
	public Timestamp getDate() {
		System.out.println(date.toString());
		return date;
	}
	
	@JsonIgnore
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public Float getValue() {
		return value;
	}
	
	public void setValue(Float value) {
		this.value = value;
	}
}
