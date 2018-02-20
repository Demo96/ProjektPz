package gra;

import java.io.IOException;

import javax.swing.SwingWorker;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PlTime extends SwingWorker<Integer, String> {
	private String date;
	private String time;
	private ActionFrame main;

	public PlTime(ActionFrame m,String d,String t) {
		this.main = m;
		this.date=d;
		this.time=t;
	}

	public PolandTime getjson() {
		ObjectMapper objectmapper = new ObjectMapper();
		String json = "https://script.google.com/macros/s/AKfycbyd5AcbAnWi2Yn0xhFRbyzS4qMq1VucMVgVvhul5XqS9HkAyJY/exec?tz=Poland";
		HttpClient client = new DefaultHttpClient();
		HttpResponse response=null;
		try {
			response = client.execute(new HttpGet(json));
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			PolandTime pt = (PolandTime) objectmapper.readValue(responseString, PolandTime.class);
			return pt;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public void refresh() {
		PolandTime pt = getjson();
		String xdate=this.date, xtime=this.time;
		if(pt!=null) {
		if (pt.getDay() < 10)
			xdate += "0" + pt.getDay() + ".";
		else
			xdate += pt.getDay() + ".";
		if (pt.getMonth() < 10)
			xdate += "0" + pt.getMonth() + "." + pt.getYear();
		else
			xdate += pt.getMonth() + "." + pt.getYear();
		if (pt.getHours() < 10)
			xtime = "0" + pt.getHours() + ":";
		else
			xtime = pt.getHours() + ":";
		if (pt.getMinutes() < 10)
			xtime += "0" + pt.getMinutes();
		else
			xtime += pt.getMinutes();}
		setDate(xdate);
		setTime(xtime);
		main.log.info("data i czas pobrane");
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		refresh();
		return 1;
	}

}
