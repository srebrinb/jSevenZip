/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srebrinb.compress.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import spark.*;
import srebrinb.compress.sevenzip.SevenZArchiveEntry;
import srebrinb.compress.sevenzip.SevenZFile;

/**
 *
 * @author sbalabanov
 */
public class DecommpressController {

    public static Route unzipFile = (Request request, Response response) -> {
        String packId = request.params(":packid");
        String fileId = request.params(":fileid");
        try {
           // int intFileId = Integer.parseInt(fileId);
            File file = new File(packId + ".7z");//new File("src\\test\\resources\\1K_10blocks.7z");
            SevenZFile archive = new SevenZFile(file);
            SevenZArchiveEntry entry;
            int i = -1;
            while ((entry = archive.getNextEntry()) != null) {
                i++;
                // if (!entry.getName().endsWith(fileId.xml")) {
//                if (intFileId != i) {
//                    continue;
//                }
               
                if (!entry.getName().endsWith(fileId)) {
                    continue;
                }
                entry.getWindowsAttributes();
               
                response.type("application/force-download");
                response.header("Content-Transfer-Encoding", "binary");
                response.header("Content-Disposition", "attachment; filename=\"" + entry.getName() + "\"");//fileName);
                //   response.header("Content-Length", Long.toString(entry.getSize()));
                HttpServletResponse raw = response.raw();
                byte[] b;
                b = new byte[(int) entry.getSize()];
                
                archive.read(b);

                try {
                    raw.getOutputStream().write(b);
                    raw.getOutputStream().flush();
                    raw.getOutputStream().close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
                //System.out.println("downloadin:" + intFileId+"/"+);

                return raw;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.status(404);
        return "not fount " + packId + "/" + fileId;
    };
}
