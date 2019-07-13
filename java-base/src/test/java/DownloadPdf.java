import java.io.*;
import java.net.*;

/**
 * @description:
 * @author: general
 * @version: 1.0
 * @create: 2019-06-26 16:20
 **/
public class DownloadPdf {

        /**
         * 从网络Url中下载文件
         * @param urlStr
         * @param fileName
         * @param savePath
         * @throws IOException
         */
        public static void  downLoadByUrl(String urlStr,String fileName,String savePath) throws IOException{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(5*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            System.out.println("get  inputStream success!");
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            //文件保存位置
            File saveDir = new File(savePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
                System.out.println("mkdir  success!");
            }
            File file = new File(saveDir+File.separator+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if(fos!=null){
                fos.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            System.out.println("info:"+url+" download success");
        }


        /**
         * 从输入流中获取字节数组
         * @param inputStream
         * @return
         * @throws IOException
         */
        public static  byte[] readInputStream(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        }

        public static void main(String[] args) {
            try{
                downLoadByUrl("http://drmingdrmer.github.io/post-res/paxos-slide/pdf/paxos.pdf",
                        "paxos.pdf","E:");
                System.out.println("download success!");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
}
