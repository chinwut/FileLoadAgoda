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

@Controller
/**
 * Created by Blazer on 3/22/2017.
 */
public class DownloadController {

    private static final String PDF_TYPE = "application/pdf";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String downloadFile(ModelMap model) {
        model.addAttribute("message", "downloadFile");
        return "index";
    }

    @RequestMapping(value = "/downloadResource", method = RequestMethod.POST)
    public  String downloadResource(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        String fileNameConvert = "C:/download/";

        fileName = (fileName != null) ? fileName : "downloadResource";

        //Test File : http://www.axmag.com/download/pdfurl-guide.pdf
        URL url = new URL(fileName);
        String[] parts = fileName.split("/");
        fileNameConvert = fileNameConvert+parts[parts.length-1];


       saveFile(url,fileNameConvert);

        model.addAttribute("message",fileNameConvert);
        return "index";
    }


    public String saveFile(URL url, String file) throws IOException {
        try {
            String messageReturn = "";

            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File(file));

            int length = -1;
            byte[] buffer = new byte[1024];

            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }

            fos.close();
            in.close();
            messageReturn = "file was downloaded";
            return messageReturn;
        }catch (IOException  e)
        {
            return e.getMessage();
        }
    }

}
