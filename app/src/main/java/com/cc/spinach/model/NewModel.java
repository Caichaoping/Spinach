package com.cc.spinach.model;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 13:40
 * 邮箱：971859818@qq.com
 */
public class NewModel extends BaseModel {

    private String title; //标题
    private String digest; // 摘要
    private String imgsrc; // 图片
    private String ptime; // 上传时间
    private String docid; // 详细地址

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "NewModel{" +
                "title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", ptime='" + ptime + '\'' +
                ", docid='" + docid + '\'' +
                '}';
    }
}
