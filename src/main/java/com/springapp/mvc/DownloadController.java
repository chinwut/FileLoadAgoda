package com.springapp.mvc;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Controller
/**
 * Created by Blazer on 3/22/2017.
 */
public class DownloadController {

    private static final String PDF_TYPE = "application/pdf";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String downloadFile(ModelMap model) {
        model.addAttribute("message", "");
        return "index";
    }

    @RequestMapping(value = "/downloadResource", method = RequestMethod.POST)
    public  String downloadResource(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        String fileNameConvert = "C:/download/";
        String fileStr = "";
        String messageRrn = "";
        String typeProtocal = request.getParameter("typeProtocal");

        fileName = (fileName != null) ? fileName : "";

        String ftpUrl = request.getParameter("fileName");
        String ftpUserName = request.getParameter("fileName");
        String ftpPassword = request.getParameter("fileName");
        String ftpFileName = request.getParameter("ftpFileName");

        if(typeProtocal.trim().equalsIgnoreCase("url")) {
            //Test File : http://www.axmag.com/download/pdfurl-guide.pdf
            URL url = new URL(fileName);
            InputStream in = url.openStream();
            String[] parts = fileName.split("/");
            fileNameConvert = fileNameConvert + parts[parts.length - 1];

            messageRrn = saveFileUrl(url,fileNameConvert);
        }
        else
        {
            String[] parts = fileName.split("/");
            fileStr = (parts.length > 1) ? parts[parts.length - 1] : fileName;
            fileNameConvert = fileNameConvert + fileStr;
            messageRrn = saveFileFTP(ftpUrl,ftpUserName,ftpPassword,ftpFileName, fileNameConvert,typeProtocal);
        }
        model.addAttribute("message",messageRrn);
        return "index";
    }


    public String saveFileUrl(URL url, String file) throws IOException {
        try {
            String messageReturn = "";

            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File(file));

            int length = -1;
            byte[] buffer = new byte[1024];

            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            in.close();

            return messageReturn;
        }catch (IOException  e)
        {
            return e.getMessage();
        }
    }
    public String saveFileFTP(String server,String user,String pass,String filePath,String fileNameConvert,String typeProtocal) throws IOException {
        String messageReturn = "";
        String strProtocal = (typeProtocal.equals("ftp")) ? "ftp" : "sftp";
        try {
            URL urlFtp = new URL(strProtocal+"://"+user+":"+pass+"@"+server+"/"+filePath);
            URLConnection urlc = urlFtp.openConnection();
            InputStream in = urlc.getInputStream(); // To download

            FileOutputStream fos = new FileOutputStream(new File(fileNameConvert));

            int length = -1;
            byte[] buffer = new byte[1024];

            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            in.close();
            messageReturn = "";
            return messageReturn;

        }catch (IOException  e)
        {
            return e.getMessage();
        }
    }
}
