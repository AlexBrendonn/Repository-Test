package com.saa.web.entity.system;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity(name = "Email")
@Table(name = "email_setting", schema = "system")
public class Email {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Setting setting;

    @Column(name = "smtp", columnDefinition = "text")
    private String stmp;

    @Column(name = "address", length = 80)
    private String address;

    @ColumnTransformer(write = "crypt(?, 'EMAIL_PASSWORD')", read = "crypt(cert_password,'EMAIL_PASSWORD')")
    @Column(name = "password", columnDefinition = "bytea")
    private String password;

    @Column(name = "port", length = 10)
    private String port;

    @Column(name = "ssl", columnDefinition = "boolean")
    private Boolean ssl;

    @Column(name = "tls", columnDefinition = "boolean")
    private Boolean tls;

    @Column(name = "xml", columnDefinition = "boolean")
    private Boolean xml;

    @Column(name = "danfe", columnDefinition = "boolean")
    private Boolean danfe;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "body", columnDefinition = "text")
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public String getStmp() {
        return stmp;
    }

    public void setStmp(String stmp) {
        this.stmp = stmp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }

    public Boolean getTls() {
        return tls;
    }

    public void setTls(Boolean tls) {
        this.tls = tls;
    }

    public Boolean getXml() {
        return xml;
    }

    public void setXml(Boolean xml) {
        this.xml = xml;
    }

    public Boolean getDanfe() {
        return danfe;
    }

    public void setDanfe(Boolean danfe) {
        this.danfe = danfe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
