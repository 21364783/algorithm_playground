package basics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class StringTest {
   
   /**
    * Ϊ��ͳ���ַ����з�Ӣ���ֵ�����
    * @throws UnsupportedEncodingException
    */
   @Test
   public void testCountHanzi() throws UnsupportedEncodingException {
      String str = "abc����abc���֤���";

      int count = 0;
      for (int i = 0; i < str.length(); i++) {
         if (String.valueOf(str.charAt(i)).getBytes("GBK").length > 1) {
            count++;
         }
      }

      System.out.println(count);
   }

   /**
    * File <- FileWriter <- BufferedWriter <- buffer <- BufferedReader <-
    * StringReader <- String
    * 
    * ���ַ����е����ݴ洢��һ���ļ���
    * 
    * @throws IOException
    */
   @Test
   public void testStringToFile() throws IOException {
      String src = "����Դ�ַ���!\r\n���ǵڶ���!";
      String dest = "test/basics/string/Target.txt";

      File f = new File(dest);

      if (!f.exists()) {
         f.getParentFile().mkdirs();
      }

      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      BufferedReader br = new BufferedReader(new StringReader(src));

      char[] buffer = new char[1024];
      int len;
      while ((len = br.read(buffer)) != -1) {
         bw.write(buffer, 0, len);
      }
      bw.flush();
      bw.close();
      br.close();
   }

   /**
    * Underlying String <- StringWriter <- BufferedWriter <- buffer <-
    * BufferedReader <- FileReader
    * 
    * ���ļ��е����ݶ��뵽����String��
    * 
    * @throws IOException
    */
   @Test
   public void testFileToString() throws IOException {
      String srcPath = "test/basics/string/Target.txt";
      StringWriter sw = new StringWriter();

      File f = new File(srcPath);

      if (!f.exists()) {
         System.out.println("Դ�ļ������ڣ�");
         return;
      }

      BufferedWriter bw = new BufferedWriter(sw);
      BufferedReader br = new BufferedReader(new FileReader(f));
      char[] buffer = new char[1024];
      int len;

      while (-1 != (len = br.read(buffer))) {
         bw.write(buffer, 0, len);
      }
      bw.flush();
      bw.close();
      br.close();

      System.out.println(sw.toString());
   }
   
   /**
    * substring�е�index������char��index��lastIndexOfҲ��
    */
   @Test
   public void truncateHanziTest() {
      String hanzi = "abc���Ǻ���abc";
      
      // read 0 - 3 chars, default UTF-8, correct
      System.out.println(hanzi.substring(0, 5));
      System.out.println(hanzi.lastIndexOf("��"));
   }
}
