DROP TABLE IF EXISTS video_details;

CREATE TABLE video_details (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      title VARCHAR(250) NOT NULL,
      description VARCHAR(250) NOT NULL,
      thumbnail_url VARCHAR(250) DEFAULT NULL,
      published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
);
