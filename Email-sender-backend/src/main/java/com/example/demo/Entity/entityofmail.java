package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


    @Document(collection = "email_logs")
    public class entityofmail{
        @Id
        private String id;
        private String to;
        private String subject;
        private String message;

        public entityofmail() {
        }

        public entityofmail(String to, String subject, String message) {
            this.to = to;
            this.subject = subject;
            this.message = message;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

