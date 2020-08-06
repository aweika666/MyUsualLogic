package com.aweika.common.utils;

/**
 * Created by YScredit on 2019/3/16.
 */
/*public class Office2PDFUtil extends Thread {

    private String host;
    private String port;
    private File inputFile;// 需要转换的文件
    private File outputFile;// 输出的文件
    private String docFormat;//需要转换的文件格式
    private String outFormat;//输出的文件的文件格式

    public String getOutFormat() {
        return outFormat;
    }

    public void setOutFormat(String outFormat) {
        this.outFormat = outFormat;
    }

    public String getDocFormat() {
        return docFormat;
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }



    public Office2PDFUtil(String host , String port, File inputFile, File outputFile, String docFormat, String outFormat) {
        this.host = host;
        this.port = port;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.docFormat=docFormat;
        this.outFormat=outFormat;

    }

    public void docToPdf() {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(host, Integer.parseInt(port));

        try {

            // 获得文件格式
            DefaultDocumentFormatRegistry formatReg = this.getDocumentFormats();
            DocumentFormat pdfFormat = formatReg.getFormatByFileExtension(this.outFormat);
            DocumentFormat docFormat = formatReg.getFormatByFileExtension(this.docFormat);
            if(pdfFormat ==null || docFormat==null){
            }
            connection.connect();
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
//            DocumentConverter converter = new Office2PDFConver(connection);

            converter.convert(this.getInputFile(), docFormat, this.getOutputFile(), pdfFormat);
//                converter.convert(this.getInputFile(), this.getOutputFile());
        } catch (ConnectException cex) {
            cex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                connection = null;
            }
//                this.destroy();
        }
    }

    private DefaultDocumentFormatRegistry getDocumentFormats(){
        DefaultDocumentFormatRegistry defaultDocumentFormatRegistry = new DefaultDocumentFormatRegistry();
        DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML", DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
        defaultDocumentFormatRegistry.addDocumentFormat(xlsx);
        DocumentFormat docx = new DocumentFormat("Microsoft Word", DocumentFamily.TEXT, "application/msword", "docx");
        defaultDocumentFormatRegistry.addDocumentFormat(docx);
        return defaultDocumentFormatRegistry;
    }
    *//**
     * 由于服务是线程不安全的，所以……需要启动线程
     *//*
    @Override
    public void run() {
        this.docToPdf();
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
}*/
