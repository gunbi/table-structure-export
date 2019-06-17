package com.pioneertao.db.export;


import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExportDoc {
    private Configuration configuration;
    private String encoding;

    public ExportDoc(String encoding) {
        this.encoding = encoding;
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setDefaultEncoding(encoding);
        configuration.setClassForTemplateLoading(this.getClass(), "");
    }

    public Template getTemplate(String name) throws Exception {
        return configuration.getTemplate(name);
    }

    /**
     * 图片写入
     */
    public String getImageStr(String image) throws IOException {
        InputStream is = new FileInputStream(image);
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] data = new byte[is.available()];
        is.read(data); is.close();
        return encoder.encode(data);
    }

    public static void main(String[] args) throws Exception {
        ExportDoc maker = new ExportDoc("UTF-8");
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xxl-job.doc"), "UTF-8"));
        Map<String,Object> map=new HashMap<>();
        map.put("tableList",DBImport.getAllColumn());
        maker.getTemplate("color.ftl").process(map, writer);
        System.out.println("导出成功");
    }
}
