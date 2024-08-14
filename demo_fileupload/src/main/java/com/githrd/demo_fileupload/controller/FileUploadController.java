package com.githrd.demo_fileupload.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext application;


    /**
     * 파일 단일 업로드
     *
     * @param title
     * @param photo
     * @param model
     * @return :
     */
    @RequestMapping(value = "/upload1.do", method = RequestMethod.POST)
    public String upload1(String title, @RequestParam MultipartFile photo, Model model) throws IOException {

        // 저장할 위치 정보 구하기(절대경로)
        String absPath = application.getRealPath("/images/");
        //System.out.println("absPath = " + absPath);

        // 파일 업로드 처리
        String fileName = "no_file";
        if (!photo.isEmpty()) {
            fileName = photo.getOriginalFilename();
            File f = new File(absPath, fileName);

            // 동일 파일명이 존재하면
            if (f.exists()) {
                long tm = System.currentTimeMillis();
                fileName = String.format("%d_%s", tm, fileName);
                f = new File(absPath, fileName);
            }

            photo.transferTo(f);
        }

        model.addAttribute("title", title);
        model.addAttribute("filename", fileName);

        return "result1";

    }

    /**
     * 파일 다중 업로드
     *
     * @param title
     * @param photo
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload2.do", method = RequestMethod.POST)
    public String upload2(String title, @RequestParam(name = "photo") MultipartFile[] photoArr, Model model) throws IOException {

        List<String> fileNameList = new ArrayList<>();

        // 저장할 위치 정보 구하기(절대경로)
        String absPath = application.getRealPath("/images/");
        // 파일 업로드 처리
        String fileName = "no_file";

        for (MultipartFile photo : photoArr) {

            if (!photo.isEmpty()) {
                fileName = photo.getOriginalFilename();
                File f = new File(absPath, fileName);

                // 동일 파일명이 존재하면
                if (f.exists()) {
                    long tm = System.currentTimeMillis();
                    fileName = String.format("%d_%s", tm, fileName);
                    f = new File(absPath, fileName);
                }

                photo.transferTo(f);
                fileNameList.add(fileName);
            }
        }

        model.addAttribute("title", title);
        model.addAttribute("fileNameList", fileNameList);

        return "result2";
    }

}
