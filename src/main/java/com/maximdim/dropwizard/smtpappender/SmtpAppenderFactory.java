package com.maximdim.dropwizard.smtpappender;

import io.dropwizard.logging.AbstractAppenderFactory;

import javax.validation.constraints.NotNull;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.net.SMTPAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("smtp")
public class SmtpAppenderFactory extends AbstractAppenderFactory {
  @NotNull
  private String name = "smtp";
  
  @NotNull
  private String host = "localhost";
  
  @NotNull
  private int port = 25;

  @NotNull
  private String to;

  @NotNull
  private String from;

  @NotNull
  private String subject;

  @NotNull
  private boolean async = true;
  
  private String pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] %logger{36} - %msg%n";
  
  @Override
  public Appender<ILoggingEvent> build(LoggerContext context, String applicationName, Layout<ILoggingEvent> layout) {
    PatternLayout patternLayout = new PatternLayout();
    patternLayout.setPattern("%d{HH:mm:ss.SSS} %-5level [%thread] %logger{36} - %msg%n");
    patternLayout.setContext(context);
    patternLayout.start();

    SMTPAppender appender = new SMTPAppender();
    appender.setContext(context);
    appender.setLayout(patternLayout);
    appender.setName(getName());
    appender.setSMTPHost(getHost());
    appender.setSMTPPort(getPort());
    appender.addTo(getTo()); 
    appender.setFrom(getFrom());
    appender.setSubject(getSubject());
    appender.setAsynchronousSending(isAsync());
    appender.start();
    
    return appender;
  }
  
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getTo() {
    return this.to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return this.from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public boolean isAsync() {
    return this.async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }
  
  public String getPattern() {
    return this.pattern;
  }
  
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
  
}
