package com.javatpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/Hello")  
public class Hello {
	private static final String FILE_PATH = "D:\\Dejvi\\Programs\\eclipseJavaEE\\workspace\\Hello\\";  
	
  // This method is called if HTML and XML is not requested  
  @GET  
  @Produces(MediaType.TEXT_PLAIN)  
  public String sayPlainTextHello() {  
    return "Hello Jersey Plain";  
  }  
  // This method is called if XML is requested  
  @GET  
  @Produces(MediaType.TEXT_XML)  
  public String sayXMLHello() {  
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";  
  }  
  
  // This method is called if HTML is requested  
  @GET  
  @Produces(MediaType.TEXT_HTML)  
  public String sayHtmlHello() {  
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"  
        + "<body><h1>" + "Hello Jersey HTML" + "</h1></body>" + "</html> ";  
  } 
  
  @GET  
  @Path("/parameter/{param}")  
  public Response getMsg(@PathParam("param") String msg) {  
      String output = "Jersey say : " + msg;  
      return Response.status(200).entity(output).build();  
  } 
  
  @GET  
  @Path("/parameter/{year}/{month}/{day}")  
  public Response getDate(  
          @PathParam("year") int year,  
          @PathParam("month") int month,   
          @PathParam("day") int day) {  
 
     String date = year + "/" + month + "/" + day;  
 
     return Response.status(200)  
      .entity("getDate is called, year/month/day : " + date)  
      .build();  
  }
  
  @POST  
  @Path("/add")  
  public Response addUser(  
      @FormParam("id") int id,  
      @FormParam("name") String name,  
      @FormParam("price") float price) {  
 
      return Response.status(200)  
          .entity(" Product added successfuly!<br> Id: "+id+"<br> Name: " + name+"<br> Price: "+price)  
          .build();  
  }
  
  @GET  
  @Path("/txt")  
  @Produces("text/plain")  
  public Response getFile() {  
      File file = new File(FILE_PATH + "myfile.txt");  
 
      ResponseBuilder response = Response.ok((Object) file);  
      response.header("Content-Disposition","attachment; filename=\"javatpoint_file.txt\"");  
      return response.build();  
 
  } 
  
  @GET  
  @Path("/image")  
  @Produces("image/png")  
  public Response getImage() {  
      File file = new File(FILE_PATH + "myimage.png");  
 
      ResponseBuilder response = Response.ok((Object) file);  
      response.header("Content-Disposition","attachment; filename=\"javatpoint_image.png\"");  
      return response.build();  
 
  } 
  
  @GET  
  @Path("/pdf")  
  @Produces("application/pdf")  
  public Response getPDF() {  
      File file = new File(FILE_PATH + "mypdf.pdf");  
      ResponseBuilder response = Response.ok((Object) file);  
      response.header("Content-Disposition","attachment; filename=\"javatpoint_pdf.pdf\"");  
      return response.build();  
  }
  
  @POST  
  @Path("/upload")  
  @Consumes(MediaType.MULTIPART_FORM_DATA)  
  public Response uploadFile(  
          @FormDataParam("file") InputStream uploadedInputStream,  
          @FormDataParam("file") FormDataContentDisposition fileDetail) {  
          String fileLocation = "C:\\Users\\Dejvi\\Downloads\\" + fileDetail.getFileName();  
                  //saving file  
          try {  
              FileOutputStream out = new FileOutputStream(new File(fileLocation));  
              int read = 0;  
              byte[] bytes = new byte[1024];  
              out = new FileOutputStream(new File(fileLocation));  
              while ((read = uploadedInputStream.read(bytes)) != -1) {  
                  out.write(bytes, 0, read);  
              }  
              out.flush();  
              out.close();  
          } catch (IOException e) {e.printStackTrace();}  
          String output = "File successfully uploaded to : " + fileLocation;  
          return Response.status(200).entity(output).build();  
      }  
}  
