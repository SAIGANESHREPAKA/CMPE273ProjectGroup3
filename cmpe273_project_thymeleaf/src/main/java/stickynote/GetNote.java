package stickynote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;


public class GetNote {

	String file_name = "";
	String userid= "";
	String title = "";
	String note_data = "";
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote_data() {
		return note_data;
	}
	public void setNote_data(String note_data) {
		this.note_data = note_data;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public String getFile(String userid, DbxClient client) throws IOException {
		
		    FileOutputStream outputStream = null;
			try{
			File fileDir = new File("./DownloadedNote/"+userid);
			if(!(fileDir.exists()))
			{
				fileDir.mkdirs();
			}
		
				outputStream = new FileOutputStream("./DownloadedNote/"+userid+"/"+this.getFile_name()+".doc");
			
			    DbxEntry.File downloadedFile = client.getFile("/"+this.getFile_name()+".doc", null,
	                outputStream);
	            //System.out.println("Metadata: " + downloadedFile.toString());			    
			    return "success";
	        }
	        catch(Exception e)
	        {
	        	return e.getMessage();
	        }
	        finally
	        {
	        	outputStream.close();     
	        }			
			
		}
	
	public String readFile(String file_name) throws IOException
	{
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
			
			br = new BufferedReader(new FileReader("./DownloadedNote/"+userid+"/"+this.getFile_name()+".doc"));
			int count = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				if(count == 0)
				{
				this.setTitle(sCurrentLine);
				count++;
				}
				else
				{
				note_data = note_data +"\n"+ sCurrentLine;
				}
			}
			
			this.setNote_data(note_data);
			return note_data;
			} 
			catch (IOException e) 
			{
			return e.getMessage();
			}
			finally 
			{
				br.close();
			}
		}
}
