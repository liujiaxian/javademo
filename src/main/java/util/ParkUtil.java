package util;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.beetl.json.JsonTool;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class ParkUtil {
	
	public static Double[] getLoc(String loc){
		String[] strs = loc.split(",");
		Double[] ds = new Double[2];
		ds[0] = Double.parseDouble(strs[0]);
		ds[1] = Double.parseDouble(strs[1]);
		return ds;
		
	}
	
	public static String today(){
		return getDay(0);
		
	}
	
	public static String getDay(int offset){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, offset);
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
		
	}
	
	public static String getDatTime(Date date ){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		return sdf.format(new Date());
		
	}
	
	public static String encode(long id){
		//将id转为8位的16进制字符串
//		String str = String.valueOf(id);
//		long first = Long.parseLong(str.substring(0, 1));
//		long last = Long.parseLong(str.substring(str.length()-1));
//		str = str +(first+last%10);
//		String hex = Long.toHexString(Long.parseLong(str));
		String hex = Long.toHexString(id);
		char[] array = hex.toCharArray();
		char[] cp = "00000000".toCharArray();
		int start = cp.length-array.length;
		System.arraycopy(array, 0, cp, start, array.length);
		return new String(cp);
		
	}
	
	public static long decode(String id){
		
		char[] cs = id.toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean start = false; 
		for(int i=0;i<cs.length;i++){
			if(!start&&cs[i]=='0') continue;
			sb.append(cs[i]);
			start = true;
		}
//		long data = Long.parseLong(sb.toString(), 16);
//		long real = data/10;
//		long check = data%10; //ignore check 
//		return real;
		long data = Long.parseLong(sb.toString(), 16);
		return data;
		
		
	}
	
	public static void genMatrixImage(String content,OutputStream os){
		try {
            
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 800, 800,hints);
		    
		     MatrixToImageWriter.writeToStream(bitMatrix, "jpg", os);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	public static String getRentPlan(int type){
		switch(type){
		case 1:return "今天";
		case 2:return "每天";
		default: return "未知";
		
		}
		
	}
	
	
	public static String gateInfo(Long id,int status,String gateCode){
		String code = encode(id);
		StringBuilder sb = new StringBuilder();
		sb.append(code);
		//有问题
		Long time = System.currentTimeMillis()/1000;
		String timeStr = Long.toHexString(time);
		sb.append(timeStr);
		sb.append("0");
		sb.append(status);
		sb.append(gateCode);
		return sb.toString();
		
	}
	
	
	
	public static void main(String[] args){
//		String encode = encode(6);
//		System.out.println(encode);
//		long decode = decode(encode);
//		System.out.println(decode);
	String str = gateInfo(15l,1,"2E250002");
		System.out.println(str);
		
	}
}
