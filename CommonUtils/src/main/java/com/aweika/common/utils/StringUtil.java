package com.aweika.common.utils;


import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    private static int seq = 10;

    public static String getNonceStr() {
        return getRandomString(18);
    }

    /**
     * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等，
     * <p>
     * 随机范围1-9 a-z A-Z
     *
     * @param length 指定字符串长度
     * @return 返回指定位数且不重复的字符串
     */
    public static String getRandomString(int length) {
        StringBuffer bu = new StringBuffer();
        String[] arr = {"2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
                "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        while (bu.length() < length) {
            String temp = arr[random.nextInt(57)];
            if (bu.indexOf(temp) == -1)
                bu.append(temp);
        }
        return bu.toString();
    }


    /**
     * 判断字符串是否为null或者为空，去除两边空格，是返回true，否返回false
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断字符串是否为为空字符串，去除两边空格，是返回true，否返回false
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String s) {
        if (isEmpty(s)) {
            return false;
        }
        return true;
    }

    public static String toStringNotNull(Object obj) {
        if (obj == null || obj.toString().equalsIgnoreCase("null")) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 判断字符串是否为为null，去除两边空格，是返回true，否返回false
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 把一个Object对象转为字符串，如果为Object对象为null则返回空字符串
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 解析微网短信发送返回的结果
     * @param response
     * @return HashMap:resCode,resMsg resCode:0-成功，500-post请求时出错，其余参考接口文档
     */


    /**
     * 传进一个以逗号分隔的字符串，再传入一个字符或字符串参数
     * 例：传入str为"aaa,bbb"，传入arg1为"'"，得到的字符串为"'aaa','bbb'"。
     *
     * @param str
     * @param arg1
     * @return
     */
    public static String toString(String str, String arg1) {
        if (str == null) {
            return "";
        }
        String[] arrs = str.split(",");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrs.length; i++) {
            result.append(arg1).append(arrs[i]).append(arg1).append(",");
        }

        return result.substring(0, result.length() - 1);
    }


    /**
     * 传入一个字符串，去掉第一个字符，如果为null则返回null，为空字符串返回空字符串
     *
     * @param str
     * @return
     */
    public static String removeFirstChar(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.substring(1, str.length());
        }
    }

    /**
     * 传入一个字符串，去掉最后一个字符，如果为null则返回null，为空字符串返回空字符串
     *
     * @param str
     * @return
     */
    public static String removeLastChar(String str) {
        if (str == null || str.equals("")) {
            return str;
        } else {
            return str.substring(0, str.length() - 1);
        }
    }

    public static String toHideIdCard(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        return str.substring(0, 3) + "****" + str.substring(str.length() - 4, str.length());
    }

    /**
     * 用UTF转换字符串
     *
     * @param str
     * @return
     */
    public static String encode2UTF8(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 去掉小数点后面的零
     *
     * @param s
     * @return
     */
    public static String formatNum(String s) {
        String old = s;
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    /**
     * 去除数组中重复的记录
     *
     * @param a
     * @return
     */
    public static String[] arrayUnique(String[] a) {
        if (a == null) return a;
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < a.length; i++) {
            if (!list.contains(a[i])) {
                list.add(a[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 去除列表中重复的记录
     *
     * @param a
     * @return
     */
    public static List<String> arrayUnique(List<String> a) {
        if (a == null) return a;
        List<String> listTemp = new ArrayList<String>();
        Iterator<String> it = a.iterator();
        String curent;
        while (it.hasNext()) {
            curent = it.next();
            if (listTemp.contains(curent)) {
                it.remove();
            } else {
                listTemp.add(curent);
            }
        }
        return listTemp;
    }

    /**
     * 生成随机数
     */
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

    /**
     * 生成六位随机数
     *
     * @return
     */
    public static String randomNum() {
        //产生000000-999999的随机数
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char c = (char) (randomInt(0, 9) + '0');
            charValue.append(String.valueOf(c));
        }
        return charValue.toString();
    }

    /**
     * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
     *
     * @param content  传入的字符串
     * @param frontNum 保留前面字符的位数
     * @param endNum   保留后面字符的位数
     * @return 带星号的字符串
     */

    public static String getStarString(String content, int frontNum, int endNum) {

        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        StringBuilder starStr = new StringBuilder();
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            starStr.append("*");
        }
        return content.substring(0, frontNum) + starStr
                + content.substring(content.length() - endNum, content.length());

    }

    /**
     * 生成随机用户名
     * 用户名规则：“首位字母随机开头（例如：C）+4位注册时间（例如：0823）+ 4位数字字母随机（例如：f23q）”，例如：C0823F23Q
     *
     * @return
     */
    public static String getUserName() {
        Random random = new Random();
        String[] str = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "2", "3", "4", "5", "6", "7", "8", "9"};
        int num = random.nextInt(25);
        StringBuilder sb = new StringBuilder();
        sb.append(str[num]).append(DateUtils.formatDateToString(new Date(), "MMdd"));
        for (int i = 4; i > 0; i--) {
            sb.append(str[random.nextInt(33)]);
        }
        return sb.toString();
    }

    /**
     * double保留指定小数位
     *
     * @param number 数字
     * @param limit  小数点后几位
     */
    public static Double getFomateDouble(Double number, int limit) {
        BigDecimal bigNum = new BigDecimal(number);
        Double result = bigNum.setScale(limit, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    /**
     * 校验是否是double 并检验小数后几位
     *
     * @param number     检验数字
     * @param indexPoint 校验小数点后几位
     * @param maxNum     校验最大值
     * @return
     */
    public static Boolean validDouble(String number, int indexPoint, Double maxNum) {
        try {
            Double doubleNum = Double.parseDouble(number);
            if (doubleNum > maxNum || doubleNum <= 0) {
                return false;
            }
            if (number.indexOf(".") > 0) {
                int indexNuber = number.length() - number.indexOf(".") - 1;
                if (indexNuber > indexPoint) {
                    return false;
                }
            }
        } catch (Exception e) {     //输入的不是double类型
            return false;
        }
        return true;
    }

    /**
     * 校验正整数
     *
     * @param number 检验的数字
     * @param maxNum 最大值
     * @param minNum 最小值
     * @return
     */
    public static Boolean validInt(String number, int maxNum, int minNum) {
        try {
            int i = Integer.parseInt(number);
            if (i > maxNum || i <= minNum) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String convertToNotNullStr(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static boolean isNumber(String string) {
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    public static boolean isDate(String date, String a) {
        if (isEmpty(date)) {
            return false;
        }
        try {
            DateUtils.parseDate(date, a);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static boolean isEmpty(Object object) {
        if (object == null || "".equals((object.toString().trim()))) {
            return true;
        }

        return false;

    }

    /**
     * 校验是否为邮�?
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 校验是否为手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (isEmpty(mobiles))
            return false;
        Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[^4,\\D]|8[023456789])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 对输入数据库中的回车换行进行处理
     */
    public static String replaceoperationChar(String message) {
        if (message == null) {
            return null;
        }
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '\'':
                    result.append("/");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }

    public static String replaceoperationWebChar(String message) {
        if (message == null) {
            return null;
        }
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '/':
                    result.append(File.separator);
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }


    /**
     * 对输入数据库中的回车换行进行处理
     */
    public static String operationChar(String message) {
        if (message == null) {
            return null;
        }
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '\'':
                    result.append("&#039;");
                    break;
                case '\"':
                    result.append("&#034;");
                    break;
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case ' ':
                    result.append("&nbsp;");
                    break;
                case '\n':
                    result.append("<br>");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }

    /**
     *
     */
    public static String deOperationChar(String message) {
        if (message == null) {
            return null;
        }
        /**
         * 输出到页面对换行的处�?
         */
        message.replaceAll("&lt;", "<");
        message.replaceAll("&gt;", ">");
        message.replaceAll("&amp;", "&");
        message.replaceAll("&quot;", "\"");
        message.replaceAll("&nbsp;", " ");
        /**
         * 下面的两个while语句也可以对输出到页面的回车换行进行处理
         * 若用下面的方法则应该屏蔽上面的operationChar(String str)方法
         * 不然会导致有时输出有�?
         */
        return message;
    }

    /**
     * 产生固定长度的字符串�?
     * 如果src的长度比length参数大，返回原始src，否则将在前（或后）填补padding字符�?
     *
     * @param src        源字符串
     * @param length     期望的长�?
     * @param padding    填补的字�?
     * @param leadingPad 在最前面填补还是在最后面填补�?
     * @return 填补以后的字符串
     */
    public static String fixLength(String src, int length, char padding, boolean leadingPad) {
        if (src == null) {
            src = "";
        }
        if (length <= src.length()) {
            return src;
        }
        StringBuilder sb = new StringBuilder(src);
        for (int i = src.length(), j = length; i < j; i++) {
            if (leadingPad) {
                sb.insert(0, padding);
            } else {
                sb.append(padding);
            }
        }
        return sb.toString();
    }

    /**
     * url解密
     *
     * @param encodedUrl
     * @return String
     * @throws IOException
     */
    public final static String decodeUri(String encodedUrl) throws IOException {
        String[] url = encodedUrl.replace("|", ",").split(",");
        byte[] urlByte = new byte[url.length];
//      for (int i = 0; i < url.length; i++) {
//      }

        for (int i = 0; i < url.length; i++) {
            if (!url[i].equals(""))
                urlByte[i] = Byte.parseByte(url[i]);
        }
        ByteArrayInputStream bin = new ByteArrayInputStream(urlByte);
        DataInputStream din = new DataInputStream(bin);
        String oriStr = din.readUTF();
        din.close();
        bin.close();
        return oriStr;
    }

    /**
     * url加密
     *
     * @param url
     * @return String
     * @throws IOException
     */
    public final static String encodeUri(String url) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dout = new DataOutputStream(bout);
        dout.writeUTF(url);
        byte[] byteUrl = bout.toByteArray();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < byteUrl.length; i++) {
            buf.append(byteUrl[i] + "|");
        }
        dout.close();
        bout.close();
        return buf.toString();
    }

    /**
     * 从c:\\\ddd\\t.txt 或�?? /dfd/t.txt类似的路径中解析文件名�??
     */
    public static String parseFileName(String path) {
        String name = new File(path).getName();
        int a = name.lastIndexOf("/");
        if (a != -1) {
            return name.substring(a + 1);
        }
        int b = name.lastIndexOf("\\");
        if (b != -1) {
            return name.substring(b + 1);
        }
        return name;
    }

    //给出�?个文件名  返回是否是web文件
    public static String isWebFile(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') < 0) {
            return null;
        }
        String extension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        String prohibitExtension = ",.exe,.com,.jsp,.php,.php3,.asp,.aspx,.html,.htm,.hta,.js,.css,";
        if (prohibitExtension.indexOf("," + extension + ",") != -1) {
            return "文件后缀不能�?:.exe,.com,.jsp,.php,.php3,.asp,.aspx,.html,.htm,.hta,.js,.css";
        }
        return null;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src != null && src.length > 0) {
            for (int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];

            for (int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }

    /**
     * 解析微网短信发送返回的结果
     *
     * @param response
     * @return HashMap:resCode,resMsg resCode:0-成功，500-post请求时出错，其余参考接口文档
     */
    public static HashMap<String, Object> parseResStr(String response) {
        HashMap<String, Object> resMap = new HashMap<>();
        if (isEmptyOrNull(response)) {
            return resMap;
        } else {
            String[] resStrs = response.split(",");
            if (resStrs.length > 1) {
                resMap.put("resCode", resStrs[0]);
                resMap.put("resMsg", resStrs[1]);
            } else {
                resMap.put("resCode", 500);
                resMap.put("resMsg", "请求错误");
            }
            return resMap;
        }
    }

    /**
     * 获取执行起止时间字符串
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static String getExecuteTimeStr(Date beginTime, Date endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder executeTime = new StringBuilder();
        if (null != beginTime) {
            if (null != endTime) {
                executeTime.append(sdf.format(beginTime));
                executeTime.append("至");
                executeTime.append(sdf.format(endTime));
            } else {
                executeTime.append(sdf.format(beginTime));
                executeTime.append("起");
            }
        } else {
            if (null != endTime) {
                executeTime.append(sdf.format(endTime));
                executeTime.append("止");
            }
        }

        return executeTime.toString();
    }

    /**
     * 根据,分隔的id String返回Integer型list
     *
     * @param idsStr
     * @return
     */
    public static List<Integer> getIdsFromStr(String idsStr) {
        List<Integer> result = new ArrayList<>();
        String[] ids = idsStr.split(",");
        for (String id : ids) {
            result.add(Integer.parseInt(id));
        }
        return result;
    }

    public static String getStrFromIds(List<Integer> ids) {
        if (ids.size() > 0) {
            StringBuilder result = new StringBuilder();
            for (Integer id : ids) {
                result.append(id);
                result.append(",");
            }
            return result.substring(0, result.length() - 1);
        }
        return "";
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 去除字符串中的空格，制表符，回车符,null值,'null'字符串
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        if (str == null||"null".equalsIgnoreCase(str)) {
            return "";
        }
        return str.replaceAll("[\\s+\r\n\t]", "");
    }

    public static void main(String[] arg) {
        //重复3�?
        String reg = "^.*?([a-zA-Z#\\d])\\1\\1(?!\\1).*?$";

        //字母+数字+特殊字符
        String reg1 = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\\d!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$";

        String reg5 = "^(?!.*([a-zA-Z]{3,}|\\d{3,}).*$)";

        String reg2 = "^.*\\d{3}.*$";

     /*  if(password.matches(reg1)){
           System.out.println("reg1" +"连续三个相同字母或数�?");
       }*/

     /* char c = 'a';
       int b = c;
       System.out.println(b);*/

        String a = "!@#$%^&*";
        int[] b = new int[a.length()];
        for (int i = 0; i < a.length(); i++) {//遍历字符�?
            char c = a.charAt(i);
            b[i] = c;
            System.out.println(b[i]);
        }

    }
}
