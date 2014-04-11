dropwizard-smtp-appender
========================

Dropwizard SMTP AppenderFactory

Sample configuration
--------------------

    logging:
      level: INFO
  
      appenders:
      - type: smtp
        host: localhost
        port: 25
        to: a@foo.com
        from: b@foo.com
        subject: "Error %logger{20} - %m"
        async: true
    