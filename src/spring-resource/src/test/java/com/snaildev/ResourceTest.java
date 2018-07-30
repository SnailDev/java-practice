package com.snaildev;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {

    /**
     * 定义资源，验证资源是否存在， 访问资源
     */
    @Test
    public void testByteArrayResource() {
        Resource resource = new ByteArrayResource("helloworld".getBytes());
        if (resource.exists()) {
            dumpStream(resource);
        }
    }

    @Test
    public void testInputStreamResource() {
        ByteArrayInputStream bis = new ByteArrayInputStream("helloworld".getBytes());
        Resource resource = new InputStreamResource(bis);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(true, resource.isOpen());
    }

    @Test
    public void testFileResource() {
        File file = new File("d:/123.txt");
        Resource resource = new FileSystemResource(file);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(false, resource.isOpen());
    }

    @Test
    public void testClasspathResourceByDefaultClassLoader() {
        Resource resource = new ClassPathResource("test.txt");
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(false, resource.isOpen());
    }

    @Test
    public void testClasspathResourceByClassLoader() throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        Resource resource = new ClassPathResource("test.txt", loader);
        if (resource.exists()) {
            dumpStream(resource);
        }

        System.out.println("path:" + resource.getFile().getAbsolutePath());
        System.out.println("path:" + resource.getURL().getPath());
        Assert.assertEquals(false, resource.isOpen());
    }

    @Test
    public void testClassPathXmlApplictionContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
    }

    /**
     * 打开资源、读取资源、关闭资源
     *
     * @param resource
     */
    private void dumpStream(Resource resource) {
        InputStream ins = null;
        try {
            ins = resource.getInputStream();
            byte[] descBytes = new byte[ins.available()];
            ins.read(descBytes);

            System.out.println(new String(descBytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
