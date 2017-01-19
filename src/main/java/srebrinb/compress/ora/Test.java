/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srebrinb.compress.ora;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleStatement;

/**
 *
 * @author sbalabanov
 */
public class Test {
    public static void main(String[] args) {
        SevenZip sz;

        try {
            //   sz = new createSevenZip("D:\\incubator\\commpressors\\test.7z", "D:\\incubator\\commpressors\\test\\normalFolder");
            //       sz = new createSevenZip("D:\\incubator\\commpressors\\test.7z", "E:\\efTools\\SOPHARMA\\tests\\db\\1");

            java.sql.Connection conn = null;
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn =DriverManager.getConnection("jdbc:oracle:thin:@dg1d1.dev.srv:2082/EIPDARCH.dev.srv",
                            "eipp_arch", "eipp_arch");


        /*    OracleCallableStatement call_stmt =
                (OracleCallableStatement)conn.prepareCall("call Compressor.filesForCompress(?)");
            call_stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            call_stmt.execute();

            System.out.println("init");
            OracleResultSet cursor = (OracleResultSet)call_stmt.getCursor(1);
        */
        String query = "select filename,filecont from (\n" + 
                    "       select d.id  || decode(DTYPE,'INV','.xml','.pdf') filename,\n" + 
                    "         d.dcont filecont         \n" + 
                    "       from docs d\n" + 
                    "       where \n" + 
                    "            d.dtype='INV'\n" + 
                    "       order by d.id\n" + 
                    "      )        \n" + 
                    "      where rownum <10000";
        query ="select filename,filecont,DBMS_LOB.GETLENGTH(filecont) f_size from ( select fp.fp_filename filename,\n" +
"     coalesce(fp.fp_filecont,(select dcont from docs d where d.did=fp_did and d.dtype=fp_dtype)) filecont  \n" +
"     from \n" +
"      files_for_pack fp where fp_pack_id=2  ORDER BY fp_dtype ASC NULLS FIRST) where rownum <= 100000";
            System.out.println("query = " + query);
           OracleStatement stmt = (OracleStatement)conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY);
           OracleResultSet cursor = (OracleResultSet)stmt.executeQuery(query);
            new File("test_pack2.7z").delete();
            sz = new SevenZip("test_pack2.7z", cursor);
            sz.createArchive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
