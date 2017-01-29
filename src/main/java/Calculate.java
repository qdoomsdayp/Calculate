import com.sun.org.apache.xpath.internal.operations.Number;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.util.ArrayList;

@WebServlet("/s")
public class Calculate extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("calc.jsp").forward(request, response);


    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        method(request, response);
    }

    private void method(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String edit = request.getParameter("value");

        edit = edit.replaceAll(" ", "");
        String str = calculate1(edit);
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();

            jsonEnt.put("valu", str);
            jsonEnt.put("edit", edit);


            out.print(jsonEnt.toString());

        }


    }

    public void destroy() {
    }

    public String calculate1(String str) {

        int cnt = 1;
        for (int i = 0; i < str.length(); i++) {
            int www = (int) str.charAt(i);
            if (((int) str.charAt(i) < 48 || (int) str.charAt(i) > 57) & (int) str.charAt(i) != 46) {
                cnt = 2;
                break;
            }
        }
        if (cnt != 1) {
            String rez = "";

            int pos = str.indexOf('(');
            if (pos != -1) {
                String str1 = str.substring(str.indexOf('(') + 1, str.indexOf(')'));
                String qqq = calculate1(str1);
                str1 = "(" + str1 + ")";
                str1 = str1.replaceAll("\\^", "\\\\^");
                str1 = str1.replaceAll("\\*", "\\\\*");
                str1 = str1.replaceAll("\\/", "\\\\/");
                str1 = str1.replaceAll("\\+", "\\\\+");
                str1 = str1.replaceAll("\\-", "\\\\-");
                str1 = str1.replaceAll("\\(", "\\\\(");
                str1 = str1.replaceAll("\\)", "\\\\)");

                str = str.replaceAll(str1, qqq);

                return calculate1(str);
            } else {
                if (str.indexOf("^") != -1) {
                    rez = operation(str, str.indexOf("^"));
                    //return calculate1(rez);
                } else if (str.indexOf("*") != -1) {
                    rez = operation(str, str.indexOf("*"));
                    // return calculate1(rez);
                } else if (str.indexOf("/") != -1) {
                    rez = operation(str, str.indexOf("/"));
                    // return calculate1(rez);
                } else if (str.indexOf("+") != -1) {
                    rez = operation(str, str.indexOf("+"));
                    // return calculate1(rez);
                } else if (str.indexOf("-") != -1) {
                    rez = operation(str, str.indexOf("-"));
                    // return calculate1(rez);
                }
            }
            return calculate1(rez);
        } else return str;

    }

    public String operation(String str, int pos) {
        char ch = str.charAt(pos);
        String str2 = "";
        int pos2 = 0, pos3 = 0;
        for (int i = pos - 1; i >= 0; i--) {
            if ((int) str.charAt(i) >= 48 & (int) str.charAt(i) <= 57 || (int) str.charAt(i) == 46) {
                str2 = str.charAt(i) + str2;
                pos2 = i;

            } else break;

        }
        String str3 = "";
        for (int i = pos + 1; i < str.length(); i++) {
            if ((int) str.charAt(i) >= 48 & (int) str.charAt(i) <= 57 || (int) str.charAt(i) == 46) {
                str3 = str3 + str.charAt(i);
                pos3 = i;
            } else break;
        }
        String rez = "";
        switch (ch) {
            case '^': {
                rez = str.replaceAll(str.substring(pos2, pos3 + 1).replaceAll("\\" + ch, "\\\\" + ch),
                        "" + Math.pow(Double.parseDouble(str2), Double.parseDouble(str3)));
                break;
            }
            case '*': {
                rez = str.replaceAll(str.substring(pos2, pos3 + 1).replaceAll("\\" + ch, "\\\\" + ch),
                        "" + (Double.parseDouble(str2) * Double.parseDouble(str3)));
                break;
            }
            case '/': {
                rez = str.replaceAll(str.substring(pos2, pos3 + 1).replaceAll("\\" + ch, "\\\\" + ch),
                        "" + (Double.parseDouble(str2) / Double.parseDouble(str3)));
                break;
            }
            case '+': {
                rez = str.replaceAll(str.substring(pos2, pos3 + 1).replaceAll("\\" + ch, "\\\\" + ch),
                        "" + (Double.parseDouble(str2) + Double.parseDouble(str3)));
                break;
            }
            case '-': {
                rez = str.replaceAll(str.substring(pos2, pos3 + 1).replaceAll("\\" + ch, "\\\\" + ch),
                        "" + (Double.parseDouble(str2) - Double.parseDouble(str3)));
                break;
            }
        }

        return rez;
    }


}