package com.example.oj_project.compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @title: CommandUtil
 * @Author Xu
 * @Date: 09/11/2022 上午 11:06
 * @Version 1.0
 */
@SuppressWarnings({"all"})
public class CommandUtil {

    public static int run(String cmd, String stdoutFile, String stderrFile) {
        try {
            //获取Runtime实例执行exec方法
            Process process = Runtime.getRuntime().exec(cmd);
            //获取标准输出 写入到指定文件
            if (stdoutFile != null) {
                InputStream stdoutFrom = process.getInputStream();
                OutputStream stdoutTo = new FileOutputStream(stdoutFile);
                while (true) {
                    int ch = stdoutFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stdoutTo.write(ch);
                }
                stdoutFrom.close();
                stdoutTo.close();
            }
            if (stderrFile != null) {
                InputStream stderrFrom = process.getErrorStream();
                OutputStream stderrTo = new FileOutputStream(stderrFile);
                while (true) {
                    int ch = stderrFrom.read();
                    if (ch == -1) {
                        break;
                    }
                    stderrTo.write(ch);
                }
                stderrFrom.close();
                stderrTo.close();
            }
            return process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

}