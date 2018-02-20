package gra;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"dayofweek",
"dayofweekName",
"day",
"month",
"monthName",
"year",
"hours",
"minutes",
"seconds",
"millis",
"fulldate",
"timezone",
"status"
})
public class PolandTime {

@JsonProperty("dayofweek")
private Integer dayofweek;
@JsonProperty("dayofweekName")
private String dayofweekName;
@JsonProperty("day")
private Integer day;
@JsonProperty("month")
private Integer month;
@JsonProperty("monthName")
private String monthName;
@JsonProperty("year")
private Integer year;
@JsonProperty("hours")
private Integer hours;
@JsonProperty("minutes")
private Integer minutes;
@JsonProperty("seconds")
private Integer seconds;
@JsonProperty("millis")
private Integer millis;
@JsonProperty("fulldate")
private String fulldate;
@JsonProperty("timezone")
private String timezone;
@JsonProperty("status")
private String status;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("dayofweek")
public Integer getDayofweek() {
return dayofweek;
}

@JsonProperty("dayofweek")
public void setDayofweek(Integer dayofweek) {
this.dayofweek = dayofweek;
}

@JsonProperty("dayofweekName")
public String getDayofweekName() {
return dayofweekName;
}

@JsonProperty("dayofweekName")
public void setDayofweekName(String dayofweekName) {
this.dayofweekName = dayofweekName;
}

@JsonProperty("day")
public Integer getDay() {
return day;
}

@JsonProperty("day")
public void setDay(Integer day) {
this.day = day;
}

@JsonProperty("month")
public Integer getMonth() {
return month;
}

@JsonProperty("month")
public void setMonth(Integer month) {
this.month = month;
}

@JsonProperty("monthName")
public String getMonthName() {
return monthName;
}

@JsonProperty("monthName")
public void setMonthName(String monthName) {
this.monthName = monthName;
}

@JsonProperty("year")
public Integer getYear() {
return year;
}

@JsonProperty("year")
public void setYear(Integer year) {
this.year = year;
}

@JsonProperty("hours")
public Integer getHours() {
return hours;
}

@JsonProperty("hours")
public void setHours(Integer hours) {
this.hours = hours;
}

@JsonProperty("minutes")
public Integer getMinutes() {
return minutes;
}

@JsonProperty("minutes")
public void setMinutes(Integer minutes) {
this.minutes = minutes;
}

@JsonProperty("seconds")
public Integer getSeconds() {
return seconds;
}

@JsonProperty("seconds")
public void setSeconds(Integer seconds) {
this.seconds = seconds;
}

@JsonProperty("millis")
public Integer getMillis() {
return millis;
}

@JsonProperty("millis")
public void setMillis(Integer millis) {
this.millis = millis;
}

@JsonProperty("fulldate")
public String getFulldate() {
return fulldate;
}

@JsonProperty("fulldate")
public void setFulldate(String fulldate) {
this.fulldate = fulldate;
}

@JsonProperty("timezone")
public String getTimezone() {
return timezone;
}

@JsonProperty("timezone")
public void setTimezone(String timezone) {
this.timezone = timezone;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}