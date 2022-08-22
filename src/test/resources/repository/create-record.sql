CREATE TABLE IF NOT EXISTS Vocabulary(
  Id INT NOT NULL,
  Word VARCHAR(100) NULL,
  Translation VARCHAR(150) NULL,
  Language VARCHAR(45) NULL,
  User VARCHAR(255) NULL,
  PRIMARY KEY (Id))
ENGINE = InnoDB;

INSERT INTO VOCABULARY(Id, Language, Translation, User, Word)
VALUES (1, 'eng', 'pies', 'Mr_Test', 'dog');