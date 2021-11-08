DROP TABLE IF EXISTS video_details;

CREATE TABLE video_details (
      id VARCHAR(250) NOT NULL PRIMARY KEY,
      title VARCHAR(250) NOT NULL,
      description VARCHAR(250) NOT NULL,
      channel VARCHAR(250) NOT NULL,
      thumbnail json DEFAULT NULL,
      published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
