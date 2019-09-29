package com.haigeek.controller;

import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.gridfs.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

 import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;


public class mongoController {

    // 获得SpringBoot提供的mongodb的GridFS对象
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Resource
    private MongoDbFactory mongoDbFactory;
    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile multiportFile) throws Exception {
        // 获得提交的文件名
        String fileName = multiportFile.getOriginalFilename();
        // 获得文件输入流
        InputStream ins = multiportFile.getInputStream();
        // 获得文件类型
        String contentType = multiportFile.getContentType();
        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
        GridFSFile gridFSFile = gridFsTemplate.store(ins, fileName, contentType);
        //将文件信息保存到关系型数据库中进行维护

        return "success";
    }

    /**
     * 下载
     *
     * @param fileId   文件id
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(@RequestParam(name = "file_id") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        GridFsResource gridFsResource=new GridFsResource(gridFSFile,
        GridFSBuckets.create(mongoDbFactory.getDb()).openDownloadStream(gridFSFile.getId());
        String fileName = gridFSFile.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        IOUtils.copy(gridFsResource.getInputStream(),response.getOutputStream());
    }

    /**
     * 删除文件
     *
     * @param fileId
     * @return
     */
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFile(@RequestParam(name = "file_id") String fileId) {
        Query query = Query.query(Criteria.where("_id").is(fileId));
        // 查询单个文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
//        if (gridFSFile == null) {
//            return ServiceResultHelper.genResultWithFaild(Constant.ErrorCode.FILE_NOT_EXIST_ERROR_MSG, Constant.ErrorCode.FILE_NOT_EXIST_ERROR);
//        }
        gridFsTemplate.delete(query);
        return "success";
    }
}
