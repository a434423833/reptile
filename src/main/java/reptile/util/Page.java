package reptile.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * 页面响应封装
 *
 * @author caohao 2018-03-30
 */
public class Page implements Serializable {
    public Page() {

    }

    private byte[] content;//页面字节数组
    private String html;  //网页源码字符串
    private Document doc;//网页Dom文档
    private String charset;//字符编码
    private String url;//url路径
    private String contentType;// 内容类型
    private Integer states;//响应状态

    public Page(byte[] content, String url, String contentType, Integer states) {
        this.content = content;
        this.url = url;
        this.contentType = contentType;
        // 根据内容来猜测 字符编码
        this.charset = CharsetDetector.guessEncoding(content);
        this.states = states;
        this.html = getHtml();
        this.doc = getDoc();
    }

    public String getCharset() {
        return charset;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }

    /**
     * 返回网页的源码字符串
     *
     * @return 网页的源码字符串
     */
    public String getHtml() {
        if (html != null) {
            return html;
        }
        if (content == null) {
            return null;
        }
        if (charset == null) {
            return null;
        }
        try {
            this.html = new String(content, charset);
            return html;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 得到文档
     *
     * @return
     */
    public Document getDoc() {
        if (doc != null) {
            return doc;
        }
        try {
            this.doc = Jsoup.parse(getHtml(), url);
            return doc;
        } catch (Exception ex) {
            return null;
        }
    }

    public Integer getStates() {
        return this.states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public boolean checkPage() {
        return this.url != null && this.html != null && this.content != null && this.doc != null;
    }
}
