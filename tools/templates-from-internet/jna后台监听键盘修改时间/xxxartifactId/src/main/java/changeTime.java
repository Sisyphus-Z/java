import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class changeTime
{
	static void plus_min(int min) throws IOException {
		Long millis = Long.valueOf(System.currentTimeMillis() + 60000*min);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis.longValue());
		Date newDate = c.getTime();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(date.format(newDate));
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		System.out.println(time.format(newDate));
		String cmd = " cmd /c date " + date.format(newDate);
		Runtime.getRuntime().exec(cmd);
		cmd = " cmd /c time " + time.format(newDate);
		System.out.println(cmd);
		Runtime.getRuntime().exec(cmd);
	}
	
}